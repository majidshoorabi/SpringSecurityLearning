package com.github.majidshoorabi.security.user;

import com.github.majidshoorabi.security.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author majid.shoorabi
 * @created 2022-05-May
 * @project peysaz
 */


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }


    @GetMapping("/user/get/{id}")
    public @ResponseBody User getUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }


    @GetMapping(value = "/admin/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public String registerPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "registerUser";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(userService.findById(id));
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/register")
    public String register(@ModelAttribute(name = "user") User user) {
        userService.registerUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


}
