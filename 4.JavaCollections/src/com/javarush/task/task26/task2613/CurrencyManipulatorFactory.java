package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<String, CurrencyManipulator>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        CurrencyManipulator currencyManipulator = null;
        if (map.containsKey(currencyCode.toLowerCase())) {
            currencyManipulator = map.get(currencyCode.toLowerCase());
        } else {
            currencyManipulator = new CurrencyManipulator(currencyCode);
            map.put(currencyCode.toLowerCase(), currencyManipulator);
        }
        return currencyManipulator;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}
