package com.verbovskiy.finalproject.model.writer;

import com.verbovskiy.finalproject.model.service.UserService;
import com.verbovskiy.finalproject.util.PasswordEncryption;

import java.io.FileWriter;
import java.io.IOException;

public class KeyWriter {
    public void writeAdminSecretKey(PasswordEncryption cryptographer) throws IOException {
        FileWriter writer = new FileWriter(UserService.NAME_OF_FILE_WITH_ENCRYPT_KEY);
        writer.write(cryptographer.getSecretKey());
        writer.close();
        //Files.write(Paths.get(UserService.NAME_OF_FILE_WITH_ENCRYPT_KEY), cryptographer.getSecretKey());
    }
}
