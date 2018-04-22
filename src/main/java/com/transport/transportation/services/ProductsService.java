package com.transport.transportation.services;

import com.transport.transportation.entity.Product;
import com.transport.transportation.entity.ProductName;
import com.transport.transportation.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductsService {

    private ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProductName productName) {

        Product product = new Product();
        product.setProductid(null);
        product.setProductname(productName.getProductname());

        productsRepository.save(product);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody Product product) {

        productsRepository.save(product);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAll() {
        HttpStatus status;

        Iterable<Product> all = productsRepository.findAll();

        if (all.spliterator().getExactSizeIfKnown() > 0) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(all, status);
    }


    @DeleteMapping("/{productid}")
    public ResponseEntity<?> delete(@PathVariable Integer productid) {

        productsRepository.deleteById(productid);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{productid}")
    public ResponseEntity<?> getById(@PathVariable Integer productid) {

        Optional<Product> product = productsRepository.findById(productid);

        if(product.isPresent()) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
