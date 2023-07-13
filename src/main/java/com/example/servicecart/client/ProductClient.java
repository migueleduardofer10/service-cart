package com.example.servicecart.client;

import com.example.servicecart.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "service-product", url = "http://localhost:9090")
public interface ProductClient {
    @PostMapping(value = {"/products"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product product,
                                 @RequestPart("imageFile") MultipartFile[] file);
    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber,
                                        @RequestParam(defaultValue = "") String searchKey);
    @GetMapping("/products/{productId}")
    public Product getProductDetailsById(@PathVariable("productId") Integer productId);

    @DeleteMapping("/products/{productId}")
    public void deleteProductDetails(@PathVariable("productId") Integer productId);

    @GetMapping("/products/{isSingleProductCheckout}/{productId}")
    public List<Product> getProductDetails(@PathVariable(name = "isSingleProductCheckout" ) boolean isSingleProductCheckout,
                                           @PathVariable(name = "productId")  Integer productId);
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable("productId") Integer productId);
}
