package com.lms.user_service.service;

import com.lms.user_service.dto.*;
import com.lms.user_service.responses.CandidateResponse;
import com.lms.user_service.responses.Response;
import com.lms.user_service.responses.UserResponse;

import java.util.List;

public interface IUserService {

    Response registerUser(UserDto userDto);
    UserResponse getUserByEmailAndPassword(LoginDto loginDto);
    UserResponse getUserByEmail(String email);
    Response setOtp (OtpDto otp);
    UserResponse getByOtp (OtpDto otpDto);
    Response createNewPassword (NewPasswordDto newPasswordDto);
    CandidateResponse getCandidateDetailsByEmail (String email);
    List<UserDetailsDTO> getAllUsers();
    Response setCandidateStatus (CandidateStatusDTO candidateStatusDTO);

}
