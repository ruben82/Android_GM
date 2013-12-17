package com.ruben.gm.utils;

/**
 * Created by IntelliJ IDEA.
 * User: r.deluca
 * Date: 04/07/13
 * Time: 10.42
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
   public static String username="ac161ddb6cc422e386f7f5f2a";
   public static String password="12ef0af4269287fc";

    public static String getAuthUrl(){
      return "https://developer.gm.com/api/v1/oauth/access_token";
    }
    public static String getUrl(){
       return "https://developer.gm.com/api/v1/account/vehicles";
    }
}
