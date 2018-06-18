package com.transport.transportation.services;

import com.transport.transportation.entity.Ecommerce;
import com.transport.transportation.entity.EcommerceName;
import com.transport.transportation.repository.EcommerceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ecommerce")
public class EcommerceProductService {
    private EcommerceRepository ecommerceRepository;

    @Autowired
    public EcommerceProductService(EcommerceRepository ecommerceRepository) {
        this.ecommerceRepository = ecommerceRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody EcommerceName ecommerceName) {

        Ecommerce ecommerce = new Ecommerce();

        ecommerce.setProductname(ecommerceName.getProductname());
        ecommerce.setImage(ecommerceName.getImage());
        ecommerce.setDiscription(ecommerceName.getDiscription());
        ecommerce.setPrice(ecommerceName.getPrice());

        ecommerceRepository.save(ecommerce);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{productid}")
    public ResponseEntity<?> getById(@PathVariable Integer productid) {

        Optional<Ecommerce> product = ecommerceRepository.findById(productid);

        if (product.isPresent()) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody Ecommerce transitProducts) {

        ecommerceRepository.save(transitProducts);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<Ecommerce>> getAll() {
        HttpStatus status;

        Iterable<Ecommerce> all = ecommerceRepository.findAll();

        if (all.spliterator().getExactSizeIfKnown() > 0) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(all, status);
    }


    @DeleteMapping("/{productid}")
    public ResponseEntity<?> delete(@PathVariable Integer productid) {

        ecommerceRepository.deleteById(productid);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
