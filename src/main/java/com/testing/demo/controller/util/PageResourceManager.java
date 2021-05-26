package com.testing.demo.controller.util;

import java.util.ResourceBundle;

public class PageResourceManager {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("pages");

    public static String getProperty(String key) {
        return BUNDLE.getString(key);
    }
}
