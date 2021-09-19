package com.example.bma.util;

import java.util.regex.Pattern;

public final class DetailsValidationUtility {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String CONTACT_NUMBER_REGEX = "[7-9][0-9]{9}";
    private static final String ZIP_CODE_REGEX = "^[1-9]{1}[0-9]{2}[0-9]{3}$";

    public static boolean isEmailValid(String email) {
        Pattern pat = Pattern.compile(EMAIL_REGEX);
        return (!isTextEmpty(email) && pat.matcher(email).matches());
    }

    public static boolean isContactNumberValid(Long contactNumber) {
        Pattern pattern = Pattern.compile(CONTACT_NUMBER_REGEX);
        return  (!isTextEmpty(Long.toString(contactNumber)) && pattern.matcher(Long.toString(contactNumber)).matches());
    }

    public static boolean isZipCodeValid(String zipcode) {
        if (isTextEmpty(zipcode)) return false;
        Pattern p = Pattern.compile(ZIP_CODE_REGEX);
        return p.matcher(zipcode).matches();
    }

    public static boolean isNameValid(String name, boolean canBeEmptyOrNull) {
        if (canBeEmptyOrNull) {
            if (isTextEmpty(name)) return true;
            else return name.chars().allMatch(Character::isLetter);
        }
        else return !isTextEmpty(name) && isTextContainsOnlyLettersAndSpace(name);
    }

    public static boolean isPasswordValid(String password) {
        return !isTextEmpty(password);
    }

    public static boolean isTextEmpty(String text) {
        return text == null || text.trim().equals("");
    }

    public static boolean isTextContainsOnlyLettersAndSpace(String text) {
        for (char c : text.toCharArray()) {
            if (!(c==32 || (c>=65 && c<=90) || (c>=97 && c<=122)))
                return false;
        }
        return true;
    }
}
