package com.eleandy.admin.login.vo;

public class StaticAdminInfoPojo {
    private static String adminName;
    public static void setAdminName(String name){
        adminName = name;
    }
    public static String getAdminName(){
        return adminName;
    }
}
