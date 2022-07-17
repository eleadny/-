package com.eleandy.customer.info.vo;

public class StaticAdminInfoPojo {

    private static String adminName;
    private static String tokens;


    public static void setAdminName(String name){
        adminName = name;
    }
    public static String getAdminName(){
        return adminName;
    }

    public static void setToken(String token){
        tokens = token;
    }
    public static String getTokens(){
        return tokens;
    }
}
