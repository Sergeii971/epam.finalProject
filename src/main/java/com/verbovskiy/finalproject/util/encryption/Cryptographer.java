package com.verbovskiy.finalproject.util.encryption;

import com.verbovskiy.finalproject.exception.EncryptionException;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptographer {
    private final String ALGORITHM = "SHA-512";

    public String encryptPassword(String password) throws EncryptionException {
        try {
            MessageDigest digester = MessageDigest.getInstance(ALGORITHM);
            byte[] input = password.getBytes(StandardCharsets.UTF_8);
            byte[] digest = digester.digest(input);
            String encryptedPassword = DatatypeConverter.printHexBinary(digest);
            return encryptedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptionException(e);
        }
    }
}
