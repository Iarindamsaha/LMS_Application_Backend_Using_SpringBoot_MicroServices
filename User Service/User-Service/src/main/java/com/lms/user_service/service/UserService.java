package com.lms.user_service.service;

import com.lms.user_service.dto.*;
import com.lms.user_service.models.UserEntity;
import com.lms.user_service.repository.UserRepo;
import com.lms.user_service.responses.CandidateResponse;
import com.lms.user_service.responses.Response;
import com.lms.user_service.responses.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    Response response;
    @Autowired
    UserResponse userResponse;
    @Value("${rabbit.queue.name}")
    String topicExchange;
    @Value("${rabbit.queue.name}")
    String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public UserService (RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public Response registerUser(UserDto userDto) {

        UserEntity checkUser = userRepo.findByEmail(userDto.getEmail());

        if(checkUser != null){
            response.setMessage("Email Id Already Exists");
            //throw new UserException("Email Id Already Registered To One User", HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }

        else {
            UserEntity newUser = modelMapper.map(userDto,UserEntity.class);
            String randomUserId = UUID.randomUUID().toString();
            newUser.setId(randomUserId);
            userRepo.save(newUser);
            response.setMessage("User Added Successfully");
            response.setObject(userDto);

            String subject = "User Registered Successfully";
            String body = "Welcome To Our LMS Portal";
            String emailId = newUser.getEmail();

            EmailSendingDTO emailSendingDTO = new EmailSendingDTO(emailId,subject,body);
            rabbitTemplate.convertAndSend(routingKey,topicExchange,emailSendingDTO);
            return response;

        }

    }

    @Override
    public UserResponse getUserByEmailAndPassword(LoginDto loginDto) {

        UserEntity checkUser = userRepo.findByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword());
        if(checkUser == null){
            response.setMessage("User Not Available");
            return null;
        }
        else {

            return modelMapper.map(checkUser,UserResponse.class);
        }

    }

    @Override
    public UserResponse getUserByEmail(String email) {

        UserEntity checkUser = userRepo.findByEmail(email);
        if(checkUser == null){
            return null;
        }
        else{
            return modelMapper.map(checkUser,UserResponse.class);
        }
    }

    @Override
    public Response setOtp(OtpDto otp) {

        UserEntity checkUser = userRepo.findByEmail(otp.getEmail());
        checkUser.setOtp(otp.getOtp());
        userRepo.save(checkUser);
        response.setMessage("Otp Saved");
        return response;

    }

    @Override
    public UserResponse getByOtp(OtpDto otpDto) {
        UserEntity entity = userRepo.findByEmailAndOtp(otpDto.getEmail(),otpDto.getOtp());
        if(entity == null){
            return null;
        }
        return modelMapper.map(entity,UserResponse.class);
    }

    @Override
    public Response createNewPassword(NewPasswordDto newPasswordDto) {

        UserEntity checkUser = userRepo.findByEmail(newPasswordDto.getEmail());
        checkUser.setPassword(newPasswordDto.getPassword());
        checkUser.setOtp(0);
        userRepo.save(checkUser);
        response.setMessage("Password Changed Successfully");
        return response;

    }

    @Override
    public CandidateResponse getCandidateDetailsByEmail(String email) {

        UserEntity checkUser = userRepo.findByEmail(email);
        if(checkUser == null){
            return null;
        }
        CandidateResponse candidateResponse = modelMapper.map(checkUser, CandidateResponse.class);
        if(candidateResponse.getCandidateStatus() == null){
            candidateResponse.setCandidateStatus("Not Available");
        }
        return candidateResponse;

    }

    @Override
    public List<UserDetailsDTO> getAllUsers() {

        List<UserEntity> allUsers = userRepo.findAll();
        List<UserDetailsDTO> userDetailsDTOS = new ArrayList<>();

        for (UserEntity user : allUsers){
            UserDetailsDTO userDetailsDTO = modelMapper.map(user,UserDetailsDTO.class);
            userDetailsDTOS.add(userDetailsDTO);
        }
        return userDetailsDTOS;
    }

    @Override
    public Response setCandidateStatus(CandidateStatusDTO candidateStatusDTO) {

        UserEntity checkUser = userRepo.findByEmail(candidateStatusDTO.getEmail());
        checkUser.setCandidateStatus(candidateStatusDTO.getCandidateStatus());
        userRepo.save(checkUser);
        response.setMessage("User Details Updated");
        UserDetailsDTO userDetailsDTO = modelMapper.map(checkUser,UserDetailsDTO.class);
        response.setObject(userDetailsDTO);
        return response;

    }

}
