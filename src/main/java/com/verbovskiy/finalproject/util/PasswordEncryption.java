package com.verbovskiy.finalproject.util;

import com.verbovskiy.finalproject.exception.EncryptionException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncryption {
    private SecretKey secretKey;
    private static final String ENCRYPTION_TYPE = "AES";
    private final String ENCODING_TYPE = "ISO8859_1";
    private final String ENCRYPT_KEY = "jOpqGrSBBRZfrz1+cC1A8bIb36Ow+PIwj7opHZs4O6E=";


    public PasswordEncryption() {
        byte[] decodedKey = Base64.getDecoder().decode(ENCRYPT_KEY);
        secretKey =new SecretKeySpec(decodedKey, 0, decodedKey.length,
                PasswordEncryption.ENCRYPTION_TYPE);
    }

    public String encryptPassword(String password) throws EncryptionException {
        try {
            byte[] passwordByteFormat = password.getBytes(ENCODING_TYPE);
            byte[] encryptedPassword = makeEncryption(passwordByteFormat, Cipher.ENCRYPT_MODE);
            return new String(encryptedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException
                | UnsupportedEncodingException | IllegalBlockSizeException e) {
           throw new EncryptionException(e);
        }
    }

    private byte[] makeEncryption(byte[] rawMessage, int cipherMode) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ENCRYPTION_TYPE);
        cipher.init(cipherMode, this.secretKey);
        byte [] output = cipher.doFinal(rawMessage);
        return output;
    }
}
