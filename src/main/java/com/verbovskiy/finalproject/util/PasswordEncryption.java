package com.verbovskiy.finalproject.util;

import com.verbovskiy.finalproject.exception.EncryptionException;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncryption {
    private SecretKey secretKey;
    public static final String ENCRYPTION_TYPE = "AES";

    public PasswordEncryption() throws EncryptionException {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION_TYPE);
            keyGenerator.init(256);
            this.secretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptionException(e);
        }
    }

    public byte[] makeEncryption(byte[] rawMessage, int cipherMode) throws EncryptionException {
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPTION_TYPE);
            cipher.init(cipherMode, this.secretKey);
            byte [] output = cipher.doFinal(rawMessage);
            return output;
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException  | BadPaddingException
                | IllegalBlockSizeException e) {
            throw new EncryptionException(e);
        }
    }

    public void generateNewKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION_TYPE);
        keyGenerator.init(256);
        this.setSecretKey(keyGenerator.generateKey());
    }

    public String getSecretKey() {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

}
