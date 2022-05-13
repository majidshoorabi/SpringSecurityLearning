package com.github.majidshoorabi.security.user;

import com.github.majidshoorabi.security.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;

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
    @PreAuthorize("hasAuthority('OP_ACCESS_USER')")
    public String userPage() {
        return "user";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('OP_ACCESS_ADMIN')")
    public String adminPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("/user/get/{id}")
    @PostAuthorize("returnObject.email == authentication.name")
    public @ResponseBody
    User getUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }


    @GetMapping(value = "/admin/register")
    @PreAuthorize("hasAuthority('OP_NEW_USER')")
    public String edit(Model model) {
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @GetMapping(value = "/admin/edit/{id}")
    @PreAuthorize("hasAuthority('OP_EDIT_USER')")
    public String edit(Model model, @PathVariable("id") Long id) {
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


    @GetMapping("/error")
    public String error() {
        return "error";
    }


    @GetMapping("/getCookie")
    public String getCookie(HttpServletRequest request) {
        System.out.println("-----------------");
        for (Cookie cookie : request.getCookies()) {
            System.out.println(cookie.getName() + " : " + cookie.getValue());
        }
        System.out.println("-----------------");
        return "index";
    }

    @GetMapping("/setCookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("user","Majid");
        cookie.setMaxAge(60);
        response.addCookie(cookie);
        return "index";
    }

    @GetMapping("/getSession")
    public String getSession(HttpSession session) {
        System.out.println("-----------------");
        System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));
        System.out.println("-----------------");
        return "index";
    }

    @GetMapping("/setSession")
    public String setSession(HttpSession session) {
        session.setAttribute("user","Majid");
        return "index";
    }
}
