package com.transport.transportation.services;

import com.transport.transportation.dto.ChangePassword;
import com.transport.transportation.dto.ForgotPassword;
import com.transport.transportation.dto.LoginUser;
import com.transport.transportation.entity.CompanyAddress;
import com.transport.transportation.entity.User;
import com.transport.transportation.repository.CompanyAddressRepository;
import com.transport.transportation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserService {

    private UserRepository userRepository;
    private CompanyAddressRepository addressRepository;

    @Autowired
    public UserService(UserRepository userRepository, CompanyAddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {

        User userTemp = user;
        userTemp.setUserType(user.getUserType().toUpperCase());
        userTemp.setGender(user.getGender().toUpperCase());

        HttpStatus status = HttpStatus.CREATED;
        CompanyAddress address = userTemp.getCompanyAddress();
        User userDB = userRepository.save(userTemp);

        if (address != null && user.getUserType().equalsIgnoreCase("COMPANY")) {
            address.setUsername(userDB.getUsername());
            addressRepository.save(address);
        }

        return new ResponseEntity<>(status);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser loginUser) {

        User user;
        HttpStatus status = HttpStatus.OK;
        CompanyAddress address;

        user = userRepository.findByUsernameAndPassword(
                loginUser.getUsername(), loginUser.getPassword());

        if (user == null) {
            status = HttpStatus.NOT_FOUND;
        } else if (user.getUserType().equalsIgnoreCase("COMPANY")) {
            address = addressRepository.findByUsername(user.getUsername());
            user.setCompanyAddress(address);
        }

        return new ResponseEntity<>(user, status);
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassword validateUser) {

        HttpStatus status;
        Optional<User> value = userRepository.findById(validateUser.getUsername());

        if (value.isPresent()) {

            User user = value.get();

            boolean valid = true;

            if ((user.getDob().compareTo(validateUser.getDob()) != 0) ||
                    (!user.getFirstName().equalsIgnoreCase(validateUser.getFirstName())) ||
                    (!user.getLastName().equalsIgnoreCase(validateUser.getLastName())) ||
                    (!user.getEmail().equalsIgnoreCase(validateUser.getEmail())) ||
                    (!user.getGender().equalsIgnoreCase(validateUser.getGender())) ||
                    (!user.getMobile().equalsIgnoreCase(validateUser.getMobile()))) {
                valid = false;
            }

            if (valid) {
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.BAD_REQUEST;
            }


        } else {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(status);
    }


    @PatchMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {

        HttpStatus status;
        int count = userRepository.updatePassword(changePassword.getNewpassword(),
                changePassword.getPassword(), changePassword.getUsername());

        if (count > 0) {
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }



    /*@GetMapping("/login/{username}/{password}")
    public ResponseEntity<?> login(@PathVariable String username,
                                   @PathVariable String password) {

        User user;
        HttpStatus status = HttpStatus.OK;
        user = userRepository.findByUsernameAndPassword(username, password);

        if (user == null) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(user, status);
    }*/

}
