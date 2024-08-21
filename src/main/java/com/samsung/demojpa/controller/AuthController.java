package com.samsung.demojpa.controller;

import com.samsung.demojpa.entity.Users;
import com.samsung.demojpa.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersService userService;

    @GetMapping("/login")
    public String login(HttpSession session) {
        // Lưu giá trị vào session với key là msg
        session.setAttribute("msg", "Login message");
        return "Auth/login";
    }

    @GetMapping("/register")
    public String register(final Model model, HttpSession session) {
        model.addAttribute("newuser", new Users());
        String message = (String) session.getAttribute("msg");
        model.addAttribute("msg", message);
        return "Auth/register";
    }

    @PostMapping("/register")
    public String createAccount(@ModelAttribute Users user, final Model model) {
        Users tmpUser = userService.getUserByUserName(user.getUsername());
        if (tmpUser == null) {
            if (user.getPassword().equals(user.getConfirmpassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.createUser(user);
                return "redirect:/login";
            } else {
                model.addAttribute("error", "confirm_password_not_matched");
            }
        } else {
            model.addAttribute("error", "username_is_in_used");
        }

        model.addAttribute("newuser", user);
        return "Auth/register";
    }

    @GetMapping("/welcome")
    public String welcome(Principal principal, final Model model) {
        // Kiểm tra nếu principal là null (người dùng chưa đăng nhập)
        if (principal != null) {
            String name = principal.getName();
            Users user = userService.getUserByUserName(name);
            model.addAttribute("authenticatedUser", user);
        } else {
            model.addAttribute("authenticatedUser", null);
            model.addAttribute("msg", "Bạn cần đăng nhập để truy cập trang này.");
            return "redirect:/login"; // Hoặc bạn có thể chuyển hướng đến trang khác nếu người dùng chưa đăng nhập
        }
        return "Auth/welcome";
    }
}
