package com.transport.transportation.services;

import com.transport.transportation.entity.TransitProducts;
import com.transport.transportation.entity.TransitProductName;
import com.transport.transportation.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class TransitProductsService {

    private ProductsRepository productsRepository;

    @Autowired
    public TransitProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TransitProductName transitProductName) {

        TransitProducts transitProducts = new TransitProducts();
        transitProducts.setProductid(null);
        transitProducts.setProductname(transitProductName.getProductname());

        productsRepository.save(transitProducts);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody TransitProducts transitProducts) {

        productsRepository.save(transitProducts);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<TransitProducts>> getAll() {
        HttpStatus status;

        Iterable<TransitProducts> all = productsRepository.findAll();

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

        Optional<TransitProducts> product = productsRepository.findById(productid);

        if(product.isPresent()) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
