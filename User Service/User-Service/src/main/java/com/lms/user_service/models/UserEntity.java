package com.lms.user_service.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class UserEntity {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String candidateStatus;
    private String email;
    private String password;
    private int otp;

}
