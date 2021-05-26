package com.testing.demo.controller.util;

import java.util.ResourceBundle;

public class AttributesResourceManager {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("attributes");

    public static String getProperty(String key) {
        return BUNDLE.getString(key);
    }
}
