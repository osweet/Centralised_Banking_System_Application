package com.example.bma.util;

import com.example.bma.exception.InvalidDataException;

import java.util.regex.Pattern;

public final class UserDetailsValidationUtility {

    private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String contactNumberRegex = "[7-9][0-9]{9}";

    public static void isEmailValid(String email) {
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null || !pat.matcher(email).matches()) throw new InvalidDataException("Invalid Email: "+email);
    }

    public static void isContactNumberValid(Long contactNumber) {
        Pattern pattern = Pattern.compile(contactNumberRegex);
        if (contactNumber == null || !pattern.matcher(Long.toString(contactNumber)).matches())
            throw new InvalidDataException("Invalid Contact Number: "+contactNumber);
    }

    public static void isNameValid(String name, boolean canBeEmptyOrNull) {
        if (!canBeEmptyOrNull && (name == null || name.trim().equals(""))) {
            throw new InvalidDataException("Name Can't be Empty!");
        }
        if ((name != null && !name.trim().equals("")) && !name.chars().allMatch(Character::isLetter))
            throw new InvalidDataException("Invalid Name: "+name);
    }

    public static void isPasswordValid(String password) {
        if (password == null || password.trim().equals(""))
            throw new InvalidDataException("Password Can't be Empty");
    }
}
