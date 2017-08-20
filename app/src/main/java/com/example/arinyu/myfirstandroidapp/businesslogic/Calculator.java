package com.example.arinyu.myfirstandroidapp.businesslogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Calculator {
    private static int rent;
    private static LinkedHashMap<String, Integer> fixValues = new LinkedHashMap<>();
    private static List<String>roommates = Arrays.asList("Lena", "Tomi", "Matyi", "Tom");
    private static List<Integer> costs = new ArrayList<>();


    public Calculator() {
    }

    private static void setFixValues() {
        rent = 180000;
        fixValues.put("Tom", 45000);
        fixValues.put("Lena", 45000);
        fixValues.put("Matyi", 60000);
        fixValues.put("Tomi", 30000);
    }

    private static List calculateResults(Integer bill) {
        int expensesPerHead = (bill - rent) / fixValues.size();
        for (String name : fixValues.keySet()) {
            System.out.println(name);
        }
        for (Integer cost : fixValues.values()) {
            costs.add(expensesPerHead+cost);
        }

        return costs;
    }

    public static void printResult(){
        for (Integer result : costs) {
            System.out.println(result);
        }
    }
    public static void main(String[] args) {
        setFixValues();
        calculateResults(243907);
        printResult();
    }
}
