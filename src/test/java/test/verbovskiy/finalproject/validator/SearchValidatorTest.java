package test.verbovskiy.finalproject.validator;

import com.verbovskiy.finalproject.validator.SearchValidator;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SearchValidatorTest {

    @Test
    public void validateSearchPositiveTest() {
        String line = "sdfghj";
        boolean actual = SearchValidator.validateSearch(line);
        assertTrue(actual);
    }

    @Test
    public void validateSearchNegativeTest() {
        String line = "<>";
        boolean actual = SearchValidator.validateSearch(line);
        assertFalse(actual);
    }

    @Test
    public void validatePricePositiveTest() {
        String price = "1234";
        boolean actual = SearchValidator.validatePrice(price);
        assertTrue(actual);
    }

    @Test
    public void validatePriceNegativeTest() {
        String price = "1234asdfg";
        boolean actual = SearchValidator.validatePrice(price);
        assertFalse(actual);
    }
}