package com.testing.demo.controller.util;

public class CommandUtil {
    public static String getUserPageByRole(int accessLevel){
        String page = "";
        switch (accessLevel){
            case 1:
                page = "redirect:/testing/student";
                break;
            case 2:
                page= "redirect:/testing/admin";
                break;
        }
        return page;
    }
}
