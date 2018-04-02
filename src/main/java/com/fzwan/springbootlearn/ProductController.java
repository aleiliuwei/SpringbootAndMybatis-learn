package com.fzwan.springbootlearn;
import com.fzwan.springbootlearn.dao.mapper.ProductMapper;
import com.fzwan.springbootlearn.dao.pojo.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/{id}")
    public Product getProductInfo(
            @PathVariable("id")
                    Long productId) {
                        
        return productMapper.select(productId);
     
    }
    @PutMapping("/{id}")
    public Product updateProductInfo(
            @PathVariable("id")
                    Long productId,
            @RequestBody
                    Product newProduct) {
        Product product = productMapper.select(productId);
        if (product == null) {
            new ProductNotFoundException(productId);
        }
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        productMapper.update(product);
        return product;
    }
}