package com.example.flowershop.controller;

import com.example.flowershop.entity.Flower;
import com.example.flowershop.entity.User;
import com.example.flowershop.repository.FlowerRepository;
import com.example.flowershop.repository.UserRepository;
import com.example.flowershop.service.FlowerService;
import com.example.flowershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class ProfileController {
    @Autowired
    UserService userService;

    @Autowired
    FlowerService flowerService;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        Set<Flower> flowers = userService.getUserFlowers(principal.getName());
        User user = userService.getUserByPrincipalName(principal.getName());
        model.addAttribute("id", user.getId()   );
        model.addAttribute("email", user.getEmail());
        model.addAttribute("createdAt", user.getDateOfCreated());
        model.addAttribute("role", user.getRoles());
        model.addAttribute("flowers", flowers);
        return "profile";
    }

    @PostMapping("/flower/add")
    public String addFlower(Flower flower, Principal principal) {
        flowerService.createFlower(flower);
        return "redirect:/profile";
    }
}
