//package org.dreamteam.onlineshop.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.dreamteam.onlineshop.model.User;
//import org.dreamteam.onlineshop.model.enums.UserRole;
//import org.dreamteam.onlineshop.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@Controller
//@RequestMapping("/users")
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public String getAllUsers(Model model) {
//        List<User> users = userService.getUsers();
//        model.addAttribute("users", users);
//        return "user-list";
//    }
//
//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable Long id, Model model) {
//        User user = userService.getUser(id);
//        model.addAttribute("user", user);
//        return "user-detail";
//    }
//
//    @GetMapping("/add")
//    public String showAddUserForm(Model model) {
//        model.addAttribute("user", new User());
//        return "user-form";
//    }
//
//    @PostMapping("/add")
//    public String addUser(
//            @ModelAttribute("user") User user,
//            @RequestParam UserRole userRole) {
//
//        user.setUserRole(userRole);
//        userService.addUser(user);
//        return "redirect:/users";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User updateUser) {
//        userService.updateUser(id, updateUser);
//        return "redirect:/users";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return "redirect:/users";
//    }
//}
