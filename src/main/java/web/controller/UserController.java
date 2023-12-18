package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsersList(Model model) {
        List<User> list = userService.usersList();
        model.addAttribute("usersList", list);
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("newUser", new User());
        return "new_user";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        User user = userService.get(id);
        model.addAttribute("user", user);
        return "update_user";
    }


    @PostMapping("/edit")
    public String edit(@RequestParam("id") int id, @ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }


    @PostMapping("/add")
    public String addUser(@ModelAttribute("newUser") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/remove")
    public String deleteUser(@RequestParam("id") int id) {
        User user = userService.delete(id);
        return "redirect:/users";
    }

}
