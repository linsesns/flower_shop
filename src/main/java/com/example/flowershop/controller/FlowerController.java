package com.example.flowershop.controller;

import com.example.flowershop.entity.Flower;
import com.example.flowershop.entity.Review;
import com.example.flowershop.entity.User;
import com.example.flowershop.service.FlowerService;
import com.example.flowershop.service.ReviewService;
import com.example.flowershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;


@Controller
public class FlowerController {
    @Autowired
    FlowerService flowerService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Flower> flowers = flowerService.getFlowers();
        List<Review> reviews = reviewService.getReviews();
        model.addAttribute("flowers", flowers);
        model.addAttribute("reviews", reviews);
        return "index";
    }

    @GetMapping("/flower/{id}")
    public String flowerInfo(@PathVariable Long id, Model model) {
        Flower flower = flowerService.getFlowerById(id);
        model.addAttribute("flower", flower);
        return "flower";
    }

    @GetMapping("/flower/{id}/buy")
    public String processFlower(@PathVariable Long id, Principal principal) {
        Flower flower = flowerService.getFlowerById(id);
        User user = userService.getUserByPrincipalName(principal.getName());
        userService.buyFlower(user, flower);
        return "redirect:/profile";
    }
}
