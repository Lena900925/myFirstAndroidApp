package com.codecool.arinyu.myfirstandroidapp.businesslogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class Calculator {
    private static int rent;
    private static LinkedHashMap<String, Integer> fixValues = new LinkedHashMap<>();

    public Calculator() {
        setFixValues();
    }

    private static void setFixValues() {
        rent = 180000;
        fixValues.put("Tom", 45000);
        fixValues.put("Lena", 45000);
        fixValues.put("Matyi", 60000);
        fixValues.put("Tomi", 30000);
    }

    public String calculateResults(double billToBePaid) {
        double expensesPerHead = (billToBePaid - rent) / fixValues.size();
        for (String name : fixValues.keySet()) {
            fixValues.put(name, fixValues.get(name) + roundNumber(expensesPerHead));
        }
        return prettify(fixValues);
    }

    private static int roundNumber(double number) {
        return new BigDecimal(number).setScale(0, RoundingMode.UP).intValue();
    }

    private static String prettify(LinkedHashMap hashmapOfResult) {
        String prettifiedString = "";
        for (Object name : hashmapOfResult.keySet()) {
            String key = name.toString();
            String value = hashmapOfResult.get(name).toString();
            prettifiedString += key + ": " + value + " HUF" + "\n";
        }

        return prettifiedString;
    }

//    public static void main(String[] args) {
//        Calculator calculator = new Calculator();
//        System.out.println(calculator.calculateResults(243908));
//    }

}
