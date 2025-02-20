package ru.umka.manager_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.umka.manager_app.controller.payload.UpdateProductPayload;
import ru.umka.manager_app.entity.Product;
import ru.umka.manager_app.service.ProductService;

@Controller
@RequestMapping("/catalog/products/{productId}")
@RequiredArgsConstructor
public class ProductConroller {

    private final ProductService productService;


    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        return this.productService.findProduct(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Product with this ID not found."));
    }

    @GetMapping
    public String getProduct() {
        return "catalog/products/product";
    }

    @GetMapping("edit")
    public String getProductEditPage() {
        return "catalog/products/edit";
    }

    @PostMapping("edit")
    public String updateProduct(@ModelAttribute("product") Product product, UpdateProductPayload payload) {
        this.productService.updateProduct(product.getId(), payload.title(), payload.details());
        return "redirect:/catalog/products/%d".formatted(product.getId());
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        productService.deleteProduct(product.getId());
        return "redirect:/catalog/products/list";
    }

    @ExceptionHandler(ResponseStatusException.class)
    public String handleResponseStatusException(ResponseStatusException exception, Model model) {
        model.addAttribute("errorMessage", exception.getReason());
        return "errors/404";
    }
}