package ru.umka.manager_app.service;

import ru.umka.manager_app.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();
}
