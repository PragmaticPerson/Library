package edu.donstu.servlet.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.donstu.service.models.Book;
import edu.donstu.service.models.Comment;
import edu.donstu.service.services.AuthorService;
import edu.donstu.service.services.BookService;
import edu.donstu.service.services.CommentService;
import edu.donstu.service.services.GanreService;
import edu.donstu.service.services.security.UserService;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private GanreService ganreService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @GetMapping()
    public String findAllPage(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/all";
    }

    @GetMapping(params = "bookName")
    public String findAllByConditionpage(@RequestParam String bookName, Model model) {
        model.addAttribute("books", bookService.findAllByName(bookName));
        return "books/all";
    }

    @GetMapping("/add")
    public String addPageGet(Model model) {
        addAllCustomAttributes(model);
        model.addAttribute("book", new Book());
        return "books/add";
    }

    @PostMapping("/add")
    public String addPagePost(@ModelAttribute @Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            model.addAttribute("book", book);
            addAllCustomAttributes(model);
            return "books/add";
        }        
        bookService.add(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String editPageGet(@PathVariable int id, Model model) {
        Book book = bookService.getOne(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            model.addAttribute("book", book);
            addAllCustomAttributes(model);
        }
        model.addAttribute("book", book);
        addAllCustomAttributes(model);
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", commentService.findByBookId(book.getId()));

        return "/books/edit";
    }

    @PostMapping("/{id}")
    public String editPagePost(@ModelAttribute @Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            model.addAttribute("book", book);
            addAllCustomAttributes(model);
            return "books/edit";
        }
        bookService.update(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/comments")
    public String addCommentToBookPost(@ModelAttribute("comment") @Valid Comment comment, BindingResult bindingResult,
            @PathVariable int id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            model.addAttribute("comment", comment);
            addAllCustomAttributes(model);
            return "redirect:/books/" + id;
        }

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        comment.setUser(userService.findByUsername(username));
        comment.setBook(new Book(id));
        comment.setId(0);
        commentService.save(comment);
        return "redirect:/books/" + id;
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    private void addAllCustomAttributes(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("ganres", ganreService.findAll());

    }
}
