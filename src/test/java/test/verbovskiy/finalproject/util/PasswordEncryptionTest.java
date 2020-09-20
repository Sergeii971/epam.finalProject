package test.verbovskiy.finalproject.util;

import com.verbovskiy.finalproject.exception.EncryptionException;
import com.verbovskiy.finalproject.util.encryption.PasswordEncryption;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PasswordEncryptionTest {
    PasswordEncryption encryption;

    @BeforeClass
    public void setUp() {
        encryption = new PasswordEncryption();
    }

    @Test
    public void encryptPasswordPositiveTest() throws EncryptionException {
        String actual = encryption.encryptPassword("1234");
        String expected = "D404559F602EAB6FD602AC7680DACBFAADD13630335E951F097AF3900E9DE176B6DB" +
                "28512F2E000B9D04FBA5133E8B1C6E8DF59DB3A8AB9D60BE4B97CC9E81DB";
        assertEquals(actual, expected);
    }

    @Test
    public void encryptPasswordNegativeTest() throws EncryptionException {
        String actual = encryption.encryptPassword("1234");
        String expected = "D404559F602EAB6FD602AC7680DACBFAADD13630335E951F097AF3900E9DE176B6DB";
        assertNotEquals(actual, expected);
    }
}