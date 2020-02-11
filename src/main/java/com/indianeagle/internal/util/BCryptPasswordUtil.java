package com.indianeagle.internal.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordUtil {
    public static String encode(String password){
        return "{bcrypt}"+new BCryptPasswordEncoder().encode(password);
    }
}
