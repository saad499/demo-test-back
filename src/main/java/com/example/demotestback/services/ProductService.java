package com.example.demotestback.services;

import com.example.demotestback.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {
    void loadData() throws IOException;
    Product saveProduct(Product product);
    Page<Product> getProduct(int page, int size);
    Product getProductById(Long id);
    Product updateProduct(Product product);
    Page searchProduct(String keyword, int page, int size);
    Product deleteProduct(Long id);
    List<Product> listProduct();
}
