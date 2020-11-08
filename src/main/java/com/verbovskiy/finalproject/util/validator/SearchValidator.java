package com.verbovskiy.finalproject.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchValidator {
    private static final String SEARCH_LINE_PATTERN = "[0-9a-zA-Z!@#$%^&*]{0,500}";
    private static final String PRICE_PATTERN = "^[0-9]{1,15}$";

    private SearchValidator() {
    }

    public static boolean validateSearch(String line) {
        boolean isSearchCorrect = true;

        if (line != null && !line.isEmpty()) {
            Pattern pattern = Pattern.compile(SEARCH_LINE_PATTERN);
            Matcher matcher = pattern.matcher(line);
            isSearchCorrect = matcher.matches();
        }
        return isSearchCorrect;
    }

    public static boolean validatePrice(String price) {
        boolean isPriceCorrect = true;

        if (price != null && !price.isEmpty()) {
            Pattern pattern = Pattern.compile(PRICE_PATTERN);
            Matcher matcher = pattern.matcher(price);
            isPriceCorrect = matcher.matches();
        }
        return isPriceCorrect;
    }
}
