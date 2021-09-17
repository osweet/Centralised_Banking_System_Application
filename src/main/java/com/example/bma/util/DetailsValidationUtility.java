package com.example.bma.util;

import com.example.bma.exception.InvalidDataException;

import java.util.regex.Pattern;

public final class DetailsValidationUtility {

    private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String contactNumberRegex = "[7-9][0-9]{9}";

    public static boolean isEmailValid(String email) {
        Pattern pat = Pattern.compile(emailRegex);
        return (email == null || !pat.matcher(email).matches());
    }

    public static boolean isContactNumberValid(Long contactNumber) {
        Pattern pattern = Pattern.compile(contactNumberRegex);
        return  (contactNumber == null || !pattern.matcher(Long.toString(contactNumber)).matches());
    }

    public static boolean isNameValid(String name, boolean canBeEmptyOrNull) {
        if (canBeEmptyOrNull) {
            if (isTextEmpty(name)) return true;
            else return name.chars().allMatch(Character::isLetter);
        }
        else return !isTextEmpty(name) && name.chars().allMatch(Character::isLetter);
    }

    public static boolean isPasswordValid(String password) {
        return isTextEmpty(password);
    }

    public static boolean isTextEmpty(String text) {
        return text == null || text.trim().equals("");
    }
}
