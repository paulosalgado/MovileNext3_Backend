package tech.paulosalgado.ifoodorder.infrastructure.web;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.paulosalgado.ifoodorder.application.product.ProductDTO;
import tech.paulosalgado.ifoodorder.application.product.ProductFactory;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductCreationException;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductNotFoundException;
import tech.paulosalgado.ifoodorder.domain.product.Product;
import tech.paulosalgado.ifoodorder.domain.product.ProductService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<ProductDTO> getProducts() {

        List<Product> products = service.findAll();

        return products.stream()
                .map(product -> ProductFactory.getDTO(product))
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{productId}")
    public ProductDTO getProduct(@PathVariable("productId") UUID productId) throws ProductNotFoundException {

        Product product = service.findById(productId);

        return ProductFactory.getDTO(product);
    }

    @PostMapping("/products")
    public ProductDTO postProduct(@RequestBody ProductDTO productDTO) throws ProductCreationException {

        Product product = ProductFactory.getProduct(productDTO);
        product = service.save(product);

        return ProductFactory.getDTO(product);
    }

}
