package com.transport.transportation.repository;

import com.transport.transportation.entity.SignUp;
import com.transport.transportation.entity.TransportRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface SignUpRepository extends CrudRepository<SignUp, String> {

    SignUp findByEmailAndPassword(String email, String password);

    @Query("select u from SignUp u  where u.email = ?1")
    SignUp findByEmail(String email);

    @Modifying
    @Query("update SignUp u set u.password = ?1  where u.email = ?3 and u.password = ?2")
    @Transactional
    int updatePassword(String newpassword, String oldpassword, String username);

    List<SignUp> findByUsertype(String usertype);

    @Query("SELECT DISTINCT a.companyname FROM SignUp a where a.companyname IS NOT NULL")
    List<String> getCompanies();
}
