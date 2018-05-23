package com.transport.transportation.services;

import com.transport.transportation.dto.ChangePassword;
import com.transport.transportation.dto.ForgotPassword;
import com.transport.transportation.dto.LoginUser;
import com.transport.transportation.entity.CompanyAddress;
import com.transport.transportation.entity.SignUp;
import com.transport.transportation.repository.CompanyAddressRepository;
import com.transport.transportation.repository.SignUpRepository;
import com.transport.transportation.email.ForgotPasswordSendEmail;
import com.transport.transportation.email.SignUpSendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserService {

    private CompanyAddressRepository addressRepository;

    @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    private ForgotPasswordSendEmail forgotEmail;

    @Autowired
    private SignUpSendEmail signUpEmail;

    @Autowired
    public UserService(CompanyAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /*@Transactional
    @PostMapping("/profile")
    public ResponseEntity<?> profile(@RequestBody User user) {

        User userTemp = user;
        userTemp.setUserType(user.getUserType().toUpperCase());
        userTemp.setGender(user.getGender().toUpperCase());

        HttpStatus status = HttpStatus.CREATED;
        CompanyAddress address = userTemp.getCompanyAddress();
        User userToDB = userRepository.save(userTemp);

        if (address != null && user.getUserType().equalsIgnoreCase("COMPANY")) {
            address.setUsername(userToDB.getUsername());
            addressRepository.save(address);
        }

        return new ResponseEntity<>(status);
    }*/

    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUp sign) {

        HttpStatus status = HttpStatus.CREATED;

        Optional<SignUp> verifyDB = signUpRepository.findById(sign.getEmail());

        if (verifyDB.isPresent()) {
            status = HttpStatus.CONFLICT;
        } else {

            SignUp signUp = sign;
            signUp.setUsertype(sign.getUsertype().toUpperCase());

            CompanyAddress companyAddress = new CompanyAddress();
            companyAddress.setName(sign.getCompanyname());

            Random random = new Random();
            String password = String.format("%04d", random.nextInt(10000));
            signUp.setPassword(password);

            signUpRepository.save(signUp);
            addressRepository.save(companyAddress);

            Optional<SignUp> userFromDB = signUpRepository.findById(sign.getEmail());

            SignUp userDB = userFromDB.get();

            new Thread(() -> {
                //signUpEmail.sendMail(userDB);
            }).start();
        }

        return new ResponseEntity<>(status);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser loginUser) {

        HttpStatus status = HttpStatus.OK;

        SignUp user = signUpRepository.findByEmailAndPassword(
                loginUser.getEmail(), loginUser.getPassword());

        if (user == null) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(user, status);
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassword validateUser) {

        HttpStatus status;
        SignUp user = signUpRepository.findByEmail(validateUser.getEmail());

        if (user != null) {

            new Thread(() -> {
                forgotEmail.sendMail(user.getPassword(), validateUser.getEmail());
            }).start();

            status = HttpStatus.OK;

        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }


    @PatchMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {

        HttpStatus status;
        int count = signUpRepository.updatePassword(changePassword.getNewpassword(),
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
