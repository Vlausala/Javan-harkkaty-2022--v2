package com.example.java_harkkatyo;

public class RegexHelper {

    public static Boolean IsPasswordStrong(String password) {
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            return true;
        } else {
            return false;
        }
    }


}
