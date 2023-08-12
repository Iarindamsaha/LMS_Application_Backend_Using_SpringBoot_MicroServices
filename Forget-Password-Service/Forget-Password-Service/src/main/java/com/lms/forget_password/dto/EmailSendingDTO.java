package com.lms.forget_password.dto;

public record EmailSendingDTO(String email,
                              String subject,
                              String body) {
}
