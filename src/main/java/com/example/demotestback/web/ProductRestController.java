package com.example.demotestback.web;

import com.example.demotestback.entities.Product;
import com.example.demotestback.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@Slf4j
public class ProductRestController {
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<Page<Product>> getProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Product> product = productService.getProduct(page, size);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable(name="id") Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(name="id") Long id, @RequestBody Product product){
        product.setOrder_Id(id);
        Product updateProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable(name="id")Long id){
        Product deleteProduct = productService.deleteProduct(id);
        return deleteProduct;
    }

    @GetMapping("/searchProduct")
    public ResponseEntity<Page<Product>> searchProduct(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Page<Product> searchProduct = productService.searchProduct(keyword,page,size);
        return ResponseEntity.ok(searchProduct);
    }

    @GetMapping("/allproduct")
    public List<Product> allProduct(){
        return productService.listProduct();
    }

    @PostMapping("/products/random")
    public Product saveRandomProduct(){
        return productService.saveRandomProduct();
    }
}
