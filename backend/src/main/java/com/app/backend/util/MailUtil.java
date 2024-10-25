package com.app.backend.util;

import com.app.backend.config.EmailConfiguration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailUtil {
    private static void sendEmail(
            JavaMailSender javaMailSender,
            String mailAddress,
            String recipient,
            String subject,
            String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
        mimeMessageHelper.setFrom(mailAddress);
        mimeMessageHelper.setTo(recipient);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content,true);

        javaMailSender.send(mimeMessage);
    }

    public static void sendEmailVerificationMail(
            EmailConfiguration emailConfiguration,
            String recipient,
            String token
    ) throws MessagingException {
        String url=emailConfiguration.getFrontendURL()+"/verify?token="+token;
        String content = "<h2>Hello , this mail is from the Code Challenge App.</h2><br/><br/>"+
                "<p>Click <a href='"+url+"'>here</a> to verify your account";
        String subject = "Code Challenge Email Verification";
        sendEmail(
                emailConfiguration.javaMailSender(),
                emailConfiguration.getEmailAddress(),
                recipient,
                subject,
                content
        );
    }
}
