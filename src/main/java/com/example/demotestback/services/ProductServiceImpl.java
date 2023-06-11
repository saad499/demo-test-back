package com.example.demotestback.services;

import com.example.demotestback.entities.Product;
import com.example.demotestback.repositories.ProductRepositories;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Product saveProduct(Product product) {
        Product saveProduct = productRepositories.save(product);
        return saveProduct;
    }

    @Override
    public Page<Product> getProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> getProduct = productRepositories.findAll(pageable);
        return getProduct;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepositories.findById(id).orElse(null);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        Product updateProducts = productRepositories.save(product);
        return updateProducts;
    }

    @Override
    public Page searchProduct(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> searchProduct = productRepositories.findProductByProduct_name(keyword, pageable);
        return searchProduct;
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product = productRepositories.findById(id).orElse(null);
        productRepositories.deleteById(id);
        return product;
    }

    @Override
    public List<Product> listProduct() {
        return productRepositories.findAll();
    }

    @Override
    public Product saveRandomProduct() {
        Product product = new Product();
        Faker faker = new Faker();

        product.setProduct_name(faker.commerce().productName());
        product.setQuantity_order(faker.number().randomNumber());
        product.setPrice_each(faker.number().randomDouble(2, 0, 1000));
        product.setOrder_date(faker.date().toString());
        product.setPurchase_address(faker.address().fullAddress());

        Product savedProduct = productRepositories.save(product);
        return savedProduct;
    }
}
