package com.app.backend.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class SecurityUtils {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String ALPHANUMERICS=UPPERCASE+LOWERCASE+NUMBERS;
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String createVerificationToken(){
        StringBuilder verificationToken = new StringBuilder();
        for (int i=0; i<64; i++){
            verificationToken.append(ALPHANUMERICS.charAt(secureRandom.nextInt(ALPHANUMERICS.length())));
        }
        return verificationToken.toString();
    }
}
