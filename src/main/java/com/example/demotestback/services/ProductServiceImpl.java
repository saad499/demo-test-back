package com.example.demotestback.services;

import com.example.demotestback.entities.Product;
import com.example.demotestback.repositories.ProductRepositories;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private ProductRepositories productRepositories;
    @Override
    public void loadData() throws IOException {
        URI uri = new ClassPathResource("Sales_April2019.csv").getURI();
        Path path = Paths.get(uri);
        List<String> lines = Files.readAllLines(path);
        for (int i = 1; i< lines.size(); i++){
            String[] line = lines.get(i).split(",");
            Product product = Product.builder()
                    .order_Id(Long.parseLong(line[0]))
                    .product_name(line[1])
                    .quantity_order(Long.parseLong(line[2]))
                    .price_each(Double.parseDouble(line[3]))
                    .order_date(line[4])
                    .purchase_address(line[5])
                    .build();
            productRepositories.save(product);
        }
    }
}
