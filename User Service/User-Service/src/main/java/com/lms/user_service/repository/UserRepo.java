package com.lms.user_service.repository;

import com.lms.user_service.dto.LoginDto;
import com.lms.user_service.models.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity,String> {

    UserEntity findByEmail (String email);
    UserEntity findByEmailAndPassword (String email, String password);
    UserEntity findByEmailAndOtp (String email, int otp);

}
