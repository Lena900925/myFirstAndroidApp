package com.codecool.arinyu.myfirstandroidapp.businesslogic;

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

    public LinkedHashMap calculateResults(Integer bill) {
        int expensesPerHead = (bill - rent) / fixValues.size();
        for (String name : fixValues.keySet()) {
            fixValues.put(name, fixValues.get(name) + expensesPerHead);
        }
        System.out.println(fixValues);
        return fixValues;
    }


    }
