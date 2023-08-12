package com.lms.forget_password.service;

import com.lms.forget_password.dto.EmailDto;
import com.lms.forget_password.dto.EmailSendingDTO;
import com.lms.forget_password.dto.NewPasswordDto;
import com.lms.forget_password.dto.OtpDto;
import com.lms.forget_password.exceptions.UserException;
import com.lms.forget_password.external_service.UserService;
import com.lms.forget_password.responses.Response;
import com.lms.forget_password.responses.UserResponse;
import com.lms.forget_password.utility.JwtUtility;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class ForgetPasswordService {

    @Autowired
    Response response;
    @Autowired
    UserService userService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JwtUtility jwtUtility;

    @Value("${rabbit.queue.name}")
    String topicExchange;
    @Value("${rabbit.queue.name}")
    String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public ForgetPasswordService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Response forgetPassword(EmailDto emailDto){

        UserResponse responseFromUserService = userService.getUserByEmail(emailDto.getEmail());
        if(responseFromUserService == null){
            throw new UserException("User Not Available In The Database", HttpStatus.BAD_REQUEST,null);
        }
        else {

            Random random = new Random();
            int generateOtp = random.nextInt(90000);
            System.out.println(generateOtp);

            OtpDto otpDto = new OtpDto();
            otpDto.setEmail(emailDto.getEmail());
            otpDto.setOtp(generateOtp);

            Response responseResponseEntity  = restTemplate.postForObject("http://USER-SERVICE/setOtp",otpDto,Response.class);
            System.out.println(responseResponseEntity);

            String subject = "OTP To Recover Password";
            String body = "Your otp for setting New Password is : " + generateOtp;
            String email = emailDto.getEmail();

            EmailSendingDTO emailSendingDTO = new EmailSendingDTO(email,subject,body);

            rabbitTemplate.convertAndSend(routingKey,topicExchange,emailSendingDTO);

            response.setMessage("Otp Sent To You Email Please Check Email");
            return response;


        }



    }

    public Response verifyOtp (OtpDto otpDto){

        UserResponse responseFromUser =
                restTemplate.postForObject("http://USER-SERVICE/getByOtp",otpDto,UserResponse.class);

        if(responseFromUser == null){
            throw new UserException("Email Or Otp Is Incorrect",HttpStatus.BAD_REQUEST,null);
        }
        else {
            String token = jwtUtility.generateTokenUsingOtp(otpDto);
            response.setMessage("Click On link To Create New Password");
            response.setObject("Token : " + " " + token);
            return response;
        }

    }

    public Response createNewPassword(NewPasswordDto newPasswordDto, String token){

        OtpDto otpDto = jwtUtility.decodeToken(token);
        if(otpDto == null){
            throw new UserException("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
        else {
            newPasswordDto.setEmail(otpDto.getEmail());
            Response responseFromUser =
                    restTemplate.postForObject("http://USER-SERVICE/createNewPassword",newPasswordDto,Response.class);
            return responseFromUser;
        }

    }



}
