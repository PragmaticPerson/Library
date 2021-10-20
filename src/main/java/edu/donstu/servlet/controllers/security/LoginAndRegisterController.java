package edu.donstu.servlet.controllers.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.donstu.service.models.security.User;
import edu.donstu.service.services.security.UserService;

@Controller
public class LoginAndRegisterController {
    private UserService userService;

    @Autowired
    LoginAndRegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "security/login";
    }

    @GetMapping("/registration")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "security/registration";
    }

    @PostMapping("/registration")
    public String postRegisterPage(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            model.addAttribute("user", user);
            return "security/registration";
        }
        userService.save(user);
        return "redirect:/login";
    }
}
