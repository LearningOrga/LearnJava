package com.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoderGenerator {

    public static void main(String args[]) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        //Generate passwords here before starting app to run user script otherwise login will not work
        //String password = "";
        String password = "test@123";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);

        //passwordEncoder.
        String input = "password";
        //SecretKey key = EncryptionUtility.generateKey(128);
        SecretKey key =EncryptionUtility.getKeyFromPassword("mypass","12345");
        System.out.println(key.toString());
        IvParameterSpec ivParameterSpec = EncryptionUtility.generateIv();
        String encrypted = EncryptionUtility.encrypt(input, key, ivParameterSpec);
        System.out.println(encrypted);
        String decrypted = EncryptionUtility.decrypt(encrypted, key, ivParameterSpec);
        System.out.println(decrypted);

    }
}
