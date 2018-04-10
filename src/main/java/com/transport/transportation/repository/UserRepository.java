package com.transport.transportation.repository;

import com.transport.transportation.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, String> {

    User findByUsernameAndPassword(String username, String password);

    @Modifying
    @Query("update User u set u.password = ?1  where u.username = ?3 and u.password = ?2")
    @Transactional
    int updatePassword(String newpassword, String oldpassword, String username);
}
