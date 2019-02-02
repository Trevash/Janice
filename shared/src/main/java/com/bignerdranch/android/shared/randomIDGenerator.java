package com.bignerdranch.android.shared;

import java.security.SecureRandom;

public class randomIDGenerator {
    private static randomIDGenerator rig = null;

    public static randomIDGenerator getInstance() {
        if (rig == null){
            rig = new randomIDGenerator();
        }
        return rig;
    }

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public String getRandomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
}
