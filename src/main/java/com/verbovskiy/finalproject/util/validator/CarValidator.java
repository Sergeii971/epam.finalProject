package com.verbovskiy.finalproject.util.validator;

import com.verbovskiy.finalproject.controller.command.RequestParameter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarValidator {
    private static final String MODEL_PATTERN = "^[0-9a-zA-Z-_]{1,50}$";
    private static final String PRICE_PATTERN = "^[0-9]{1,15}$";
    private static final String DESCRIPTION_PATTERN = "[^*<>/{|}]+ {0,500}";
    private static final String YEAR_PATTERN = "[0-9]{4}";
    private static final int MIN_CAR_MANUFACTURE_YEAR = 2010;

    private CarValidator() {
    }

    public static Map<String, Boolean> validateCarData(String price, String description,
                                                       String model, String manufactureYear) {
        Map<String, Boolean> incorrectParameters = new HashMap<>();
        if (!validatePrice(price)) {
            incorrectParameters.put(RequestParameter.PRICE, true);
        }
        if (!validateDescription(description)) {
            incorrectParameters.put(RequestParameter.DESCRIPTION, true);
        }
        if (!validateModel(model)) {
            incorrectParameters.put(RequestParameter.MODEL, true);
        }
        if (!validateManufactureYear(manufactureYear)) {
            incorrectParameters.put(RequestParameter.MANUFACTURE_YEAR, true);
        }
        return incorrectParameters;
    }

    public static boolean validatePrice(String price) {
        boolean isPriceCorrect = false;

        if (price != null && !price.isEmpty()) {
            Pattern pattern = Pattern.compile(PRICE_PATTERN);
            Matcher matcher = pattern.matcher(price);
            isPriceCorrect = matcher.matches();
        }
        return isPriceCorrect;
    }

    public static boolean validateManufactureYear(String year) {
        boolean isYearCorrect = false;

        if (year != null && !year.isEmpty()) {
            Pattern pattern = Pattern.compile(YEAR_PATTERN);
            Matcher matcher = pattern.matcher(year);
            if (matcher.matches()) {
                int yearIntFormat = Integer.parseInt(year);
                if ((yearIntFormat >= MIN_CAR_MANUFACTURE_YEAR) && (yearIntFormat <= LocalDate.now().getYear())) {
                    isYearCorrect = true;
                }
            }
        }
        return isYearCorrect;
    }

    public static boolean validateDescription(String description) {
        boolean isDescriptionCorrect = true;

        if (description != null && !description.isEmpty()) {
            Pattern pattern = Pattern.compile(DESCRIPTION_PATTERN);
            Matcher matcher = pattern.matcher(description);
            isDescriptionCorrect = matcher.matches();
        }
        return isDescriptionCorrect;
    }

    public static boolean validateModel(String model) {
        boolean isModelCorrect = false;

        if (model != null && !model.isEmpty()) {
            Pattern pattern = Pattern.compile(MODEL_PATTERN);
            Matcher matcher = pattern.matcher(model);
            isModelCorrect = matcher.matches();
        }
        return isModelCorrect;
    }
}
