package com.georgij.pw3Task2.controller;

import com.georgij.pw3Task2.model.Post;
import com.georgij.pw3Task2.model.User;
import com.georgij.pw3Task2.service.PostService;
import com.georgij.pw3Task2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/posts")
    public String listPosts(Model model) {
       List<Post> posts = postService.getAllPosts();
        model.addAttribute("post", posts);
        return "post/list"; // Повертаємо назву HTML-шаблону для відображення списку постів
    }

    // Метод для відображення окремого поста за його id
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "post/view"; // Повертаємо назву HTML-шаблону для відображення окремого поста
        } else {
            // Обробка випадку, коли пост не знайдено
            return "error"; // Повертаємо сторінку помилки
        }
    }

    // Метод для відображення форми додавання поста
    @GetMapping("/posts/add")
    public String showAddPostForm(Model model) {
        List<User> users = userService.getAllUsers(); // Отримайте список користувачів з бази даних
        model.addAttribute("users", users); // Передайте список користувачів в модель
        model.addAttribute("post", new Post());
        return "post/add";
    }

    // Метод для обробки додавання нового поста
    @PostMapping("/posts/add")
    public String addPost(@ModelAttribute Post post) {
        postService.createPost(post);
        return "/posts"; // Перенаправляємо користувача на сторінку зі списком постів після додавання поста
    }

    // Метод для відображення форми оновлення поста за його id
    @GetMapping("/posts/edit/{id}")
    public String showUpdatePostForm(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "post/edit"; // Повертаємо назву HTML-шаблону для відображення форми оновлення поста
        } else {
            // Обробка випадку, коли пост не знайдено
            return "error"; // Повертаємо сторінку помилки
        }
    }

    // Метод для обробки оновлення поста
    @PostMapping("/posts/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post updatedPost) {
        Optional<Post> existingPost = postService.updatePost(id, updatedPost);
        if (existingPost.isPresent()) {
            return "redirect:/posts"; // Перенаправляємо користувача на сторінку зі списком постів після оновлення поста
        } else {
            // Обробка випадку, коли пост не знайдено
            return "error"; // Повертаємо сторінку помилки
        }
    }

    // Метод для видалення поста за його id
    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts"; // Перенаправляємо користувача на сторінку зі списком постів після видалення поста
    }
}

