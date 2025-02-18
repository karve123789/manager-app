package ru.umka.manager_app.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umka.manager_app.entity.Product;
import ru.umka.manager_app.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    DefaultProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts(){
        return this.productRepository.findAll();
    }
}
