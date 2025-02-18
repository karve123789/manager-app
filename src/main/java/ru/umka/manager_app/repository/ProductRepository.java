package ru.umka.manager_app.repository;

import ru.umka.manager_app.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
}
