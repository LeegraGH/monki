package com.example.monki.serviceTest;

import com.example.monki.services.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    private EmailService emailService;

    public EmailServiceTest() {
        MockitoAnnotations.openMocks(this);
        emailService = new EmailService(mailSender);
    }

    @Test
    void sendMessage() {
        // given
        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Message";

        // when
        emailService.sendMessage(to, subject, text);

        // then
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}