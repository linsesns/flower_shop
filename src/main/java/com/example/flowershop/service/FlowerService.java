package com.example.flowershop.service;

import com.example.flowershop.entity.Flower;
import com.example.flowershop.repository.FlowerRepository;
import com.example.flowershop.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerService {
    @Autowired
    FlowerRepository flowerRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public List<Flower> getFlowers() {
        return flowerRepository.findAll();
    }

    public Flower getFlowerById(Long id) {
        return flowerRepository.findById(id).orElse(null);
    }

    public void createFlower(Flower flower) {
        flowerRepository.save(flower);
    }
}
