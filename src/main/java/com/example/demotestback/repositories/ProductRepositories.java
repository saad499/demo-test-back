package com.example.demotestback.repositories;

import com.example.demotestback.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositories extends JpaRepository<Product,Long> {
}
