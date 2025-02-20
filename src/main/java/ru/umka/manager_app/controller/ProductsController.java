package ru.umka.manager_app.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.umka.manager_app.controller.payload.NewProductPayload;
import ru.umka.manager_app.entity.Product;
import ru.umka.manager_app.service.ProductService;
import org.springframework.stereotype.Controller;

@Controller
//@RequiredArgsConstructor
@RequestMapping("catalog/products")
public class ProductsController {

    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("list")           //ТОЛЬКО НАПИСАЛ
    public String getProductsList(Model model){
        model.addAttribute("products", this.productService.findAllProducts());
        return  "catalog/products/list";
    }

    @GetMapping("create")
    public String getProductsPage(){
        return  "catalog/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(@Validated NewProductPayload payload, BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getObjectName)
                    .toList());
            return  "catalog/products/new_product";
        } else {
            Product product = this.productService.createProduct(payload.title(), payload.details());
            return "redirect:/catalog/products/" + product.getId();
        }
    }
}

