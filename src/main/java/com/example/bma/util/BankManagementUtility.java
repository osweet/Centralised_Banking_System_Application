package com.example.bma.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public final class BankManagementUtility {

    public static String generateNumberIdNumberByDigit(int numberLength) {
        StringBuilder idNumberString = new StringBuilder();

        Random objGenerator = new Random();
        for (int iCount = 0; iCount< numberLength; iCount++){
            idNumberString.append(objGenerator.nextInt(9));
        }

        return idNumberString.toString();
    }

    public static Date getCurrentDateTime() {
        return Calendar.getInstance().getTime();
    }
}
