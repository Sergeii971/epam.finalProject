package com.verbovskiy.finalproject.model.reader;

import com.verbovskiy.finalproject.exception.EncryptionException;
import com.verbovskiy.finalproject.model.service.UserService;
import com.verbovskiy.finalproject.util.PasswordEncryption;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class KeyReader {
    private static Logger logger = LogManager.getLogger(KeyReader.class);

    public PasswordEncryption readEncryptKey() throws EncryptionException, IOException {
        try {
            PasswordEncryption cryptographer = new PasswordEncryption();
            byte[] encryptedKey = Files.readAllBytes(Paths.get(UserService.NAME_OF_FILE_WITH_ENCRYPT_KEY));
            byte[] decodedKey = Base64.getDecoder().decode(new String(encryptedKey));
            cryptographer.setSecretKey(new SecretKeySpec(decodedKey, 0, decodedKey.length,
                    PasswordEncryption.ENCRYPTION_TYPE));
            logger.log(Level.INFO,"file was successfully read!");
            return cryptographer;
        } catch (IOException e) {
            throw new EncryptionException("Error while reading file!", e);
        }
    }
}
