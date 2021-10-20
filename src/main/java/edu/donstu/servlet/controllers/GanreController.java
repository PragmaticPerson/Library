package edu.donstu.servlet.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.donstu.service.models.Ganre;
import edu.donstu.service.services.GanreService;

@Controller
@RequestMapping("/ganres")
public class GanreController {
    @Autowired
    private GanreService ganreService;

    @GetMapping
    public String findAllPage(Model model) {
        model.addAttribute("ganres", ganreService.findAll());
        return "ganres/all";
    }

    @GetMapping("/add")
    public String addPageGet(Model model) {
        model.addAttribute("ganre", new Ganre());
        return "ganres/add";
    }

    @PostMapping("/add")
    public String addPagePost(@ModelAttribute("ganre") @Valid Ganre ganre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            model.addAttribute("ganre", ganre);
            return "ganres/add";
        }
        ganreService.add(ganre);
        return "redirect:/ganres";
    }

    @GetMapping("/{id}")
    public String editPageGet(@PathVariable int id, Model model) {
        model.addAttribute("ganre", ganreService.getOne(id));
        return "/ganres/edit";
    }

    @PostMapping("/{id}")
    public String editPagePost(@ModelAttribute("ganre") @Valid Ganre ganre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            model.addAttribute("ganres", ganre);
            return "ganres/edit";
        }
        ganreService.update(ganre);
        return "redirect:/ganres";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("id") int id) {
        ganreService.delete(id);
        return "redirect:/ganres";
    }

}
