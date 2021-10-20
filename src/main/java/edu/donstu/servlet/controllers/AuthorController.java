package edu.donstu.servlet.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.donstu.service.models.Author;
import edu.donstu.service.services.AuthorService;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping
    public String findAllPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/all";
    }

    @GetMapping("/add")
    public String addPageGet(Model model) {
        model.addAttribute("author", new Author());
        return "authors/add";
    }

    @PostMapping("/add")
    public String addPagePost(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            model.addAttribute("author", author);
            return "authors/add";
        }
        authorService.add(author);
        return "redirect:/authors";
    }

    @GetMapping("/{id}")
    public String editPageGet(@PathVariable int id, Model model) {
        model.addAttribute("author", authorService.getOne(id));
        return "/authors/edit";
    }

    @PostMapping("/{id}")
    public String editPagePost(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            model.addAttribute("author", author);
            return "authors/edit";
        }
        authorService.update(author);
        return "redirect:/authors";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("id") int id) {
        authorService.delete(id);
        return "redirect:/authors";
    }

}
