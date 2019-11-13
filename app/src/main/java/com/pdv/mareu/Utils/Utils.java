package com.pdv.mareu.Utils;

public abstract class Utils {

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    
}
