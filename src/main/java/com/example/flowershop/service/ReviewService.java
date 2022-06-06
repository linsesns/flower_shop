package com.example.flowershop.service;

import com.example.flowershop.entity.Review;
import com.example.flowershop.entity.User;
import com.example.flowershop.enums.Role;
import com.example.flowershop.repository.ReviewRepository;
import com.example.flowershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public void createReview(Review review, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        review.setUser(user);
        user.getReviews().add(review);
        reviewRepository.save(review);
        userRepository.save(user);
    }
}
