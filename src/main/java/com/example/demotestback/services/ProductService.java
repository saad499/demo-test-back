package com.example.demotestback.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ProductService {
    void loadData() throws IOException;
}
