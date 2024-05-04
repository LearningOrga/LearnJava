package com.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

 public static void main(String args[]) {

        //Generate passwords here before starting app to run user script otherwise login will not work
       //String password = "";
      String password = "test@123";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

       System.out.println(hashedPassword);

        //passwordEncoder.
     //System.out.println(EncryptionUtility.encrypt("password","IPL@2020"));
     //System.out.println(EncryptionUtility.decrypt("password","IPL@2020"));

    }
}
