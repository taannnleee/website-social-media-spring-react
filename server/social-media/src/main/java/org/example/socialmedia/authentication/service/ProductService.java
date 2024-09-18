package org.example.socialmedia.authentication.service;

import org.example.socialmedia.common.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProduct();

    Product getProductById(String id);
}
