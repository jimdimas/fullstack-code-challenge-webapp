package com.app.backend.config;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@Getter
public class EmailConfiguration {
    @Value("${spring.properties.frontend_url}")
    private String frontendURL;
    @Value("${spring.mail.host}")
    @Getter(AccessLevel.PRIVATE)
    private String emailHost;
    @Value("${spring.mail.port}")
    @Getter(AccessLevel.PRIVATE)
    private Integer emailPort;
    @Value("${spring.mail.protocol}")
    @Getter(AccessLevel.PRIVATE)
    private String emailProtocol;
    @Value("${spring.mail.username}")
    private String emailAddress;
    @Value("${spring.mail.password}")
    @Getter(AccessLevel.PRIVATE)
    private String emailPassword;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(emailHost);
        javaMailSender.setPort(emailPort);
        javaMailSender.setProtocol(emailProtocol);
        javaMailSender.setUsername(emailAddress);
        javaMailSender.setPassword(emailPassword);
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.smtp.auth",true);
        javaMailProperties.put("mail.smtp.starttls.enable",true);
        javaMailProperties.put("mail.debug",true);
        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }
}
