package com.bezkoder.springjwt.security.services;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class AESEncrypt {
    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[] { 'T', 'E', 'S', 'T' , 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', };
    public static String encrypt(String pwd) {
        String encodedPwd = "";
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(pwd.getBytes());
            encodedPwd = Base64.getEncoder().encodeToString(encVal);
    
        } catch (Exception e) {
    
            e.printStackTrace();
        }
        return encodedPwd;
    
    }

    public static String decrypt(String encryptedData) {
        String decodedPWD = "";
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
            byte[] decValue = c.doFinal(decordedValue);
            decodedPWD = new String(decValue);
    
        } catch (Exception e) {
    
        }
        return decodedPWD;
    }
    private static Key generateKey() {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
}
