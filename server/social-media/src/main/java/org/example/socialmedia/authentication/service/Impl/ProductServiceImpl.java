package org.example.socialmedia.authentication.service.Impl;

import org.example.socialmedia.authentication.repositories.ProductRepository;
import org.example.socialmedia.authentication.service.ProductService;
import org.example.socialmedia.common.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
