package com.transport.transportation.services;

import com.transport.transportation.entity.TransitProducts;
import com.transport.transportation.entity.TransitProductName;
import com.transport.transportation.repository.TransitProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class TransitProductsService {

    private TransitProductsRepository transitProductsRepository;

    @Autowired
    public TransitProductsService(TransitProductsRepository transitProductsRepository) {
        this.transitProductsRepository = transitProductsRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TransitProductName transitProductName) {

        TransitProducts transitProducts = new TransitProducts();
        transitProducts.setProductid(null);
        transitProducts.setProductname(transitProductName.getProductname());

        transitProductsRepository.save(transitProducts);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody TransitProducts transitProducts) {

        transitProductsRepository.save(transitProducts);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<TransitProducts>> getAll() {
        HttpStatus status;

        Iterable<TransitProducts> all = transitProductsRepository.findAll();

        if (all.spliterator().getExactSizeIfKnown() > 0) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(all, status);
    }


    @DeleteMapping("/{productid}")
    public ResponseEntity<?> delete(@PathVariable Integer productid) {

        transitProductsRepository.deleteById(productid);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{productid}")
    public ResponseEntity<?> getById(@PathVariable Integer productid) {

        Optional<TransitProducts> product = transitProductsRepository.findById(productid);

        if(product.isPresent()) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
