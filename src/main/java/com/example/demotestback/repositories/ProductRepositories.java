package com.example.demotestback.repositories;

import com.example.demotestback.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositories extends JpaRepository<Product,Long> {

    @Query("select p from Product p where p.product_name like %:product_name%")
    Page<Product> findProductByProduct_name(@Param("product_name") String keyword, Pageable pageable);
}
