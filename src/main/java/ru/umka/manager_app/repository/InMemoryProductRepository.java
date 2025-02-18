package ru.umka.manager_app.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.umka.manager_app.entity.Product;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@Data
@Repository
public class InMemoryProductRepository implements ProductRepository{


    private final List<Product> products = Collections.synchronizedList(new LinkedList<>());
    public InMemoryProductRepository() {

        IntStream.range(1, 4)
                .forEach(i -> this.products.add(new Product(Integer.valueOf(i), "Товар №%d".formatted(i),
                        "Описание товара №%d".formatted(i))));
    }

    @Override
    public List<Product> findAll(){
        return Collections.unmodifiableList(this.products);
    }
}
