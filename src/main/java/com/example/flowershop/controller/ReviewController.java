package com.example.flowershop.controller;

import com.example.flowershop.entity.Review;
import com.example.flowershop.service.ReviewService;
import com.example.flowershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ReviewController {
    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    @PostMapping("/review")
    public String processReview(Review review, Principal principal) {
        reviewService.createReview(review, principal);
        return "redirect:/";
    }
}
