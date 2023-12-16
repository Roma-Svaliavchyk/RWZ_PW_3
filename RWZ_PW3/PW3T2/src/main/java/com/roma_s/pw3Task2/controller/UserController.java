package com.georgij.pw3Task2.controller;

import com.georgij.pw3Task2.model.User;
import com.georgij.pw3Task2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // Метод для відображення списку користувачів
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list"; // Повертаємо назву HTML-шаблону для відображення списку користувачів
    }

    // Метод для відображення окремого користувача за його id
    @GetMapping("/users/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user/view"; // Повертаємо назву HTML-шаблону для відображення окремого користувача
        } else {
            // Обробка випадку, коли користувача не знайдено
            return "error"; // Повертаємо сторінку помилки
        }
    }

    // Метод для відображення форми додавання користувача
    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add"; // Повертаємо назву HTML-шаблону для відображення форми додавання користувача
    }

    // Метод для обробки додавання нового користувача
    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users"; // Перенаправляємо користувача на сторінку зі списком користувачів після додавання користувача
    }

    // Метод для відображення форми оновлення користувача за його id
    @GetMapping("/users/edit/{id}")
    public String showUpdateUserForm(@PathVariable Long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user/edit"; // Повертаємо назву HTML-шаблону для відображення форми оновлення користувача
        } else {
            // Обробка випадку, коли користувача не знайдено
            return "error"; // Повертаємо сторінку помилки
        }
    }

    @PostMapping("/users/edit/{id}") // Визначте URL для редагування користувача і метод POST
    public String editUser(@ModelAttribute User user) {
        userService.updateUser(user.getId(), user);
        return "redirect:/users"; // Повернення на сторінку зі списком користувачів, наприклад
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users"; // Перенаправляємо користувача на сторінку зі списком користувачів після видалення користувача
    }
}
