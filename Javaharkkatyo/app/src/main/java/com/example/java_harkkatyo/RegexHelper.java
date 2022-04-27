/*
 *Course: CT60A2411 Olio-ohjelmointi
 *Date: 29.4.2022
 *Group: Matti Lankinen, Valtteri Lausala, Jan-Peter Kauppinen
 */

package com.example.java_harkkatyo;

public class RegexHelper {

    /***
     * Checks that password:
     *      1. Contains numbers
     *      2. Contains lower- and uppercase letters
     *      3. Contains special symbols
     * @param password this is the password checked
     * @return boolean this is the result of the password check
     */
    public static Boolean IsPasswordStrong(String password) {
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{12,}$")) {
            return true;
        } else {
            return false;
        }
    }

}
