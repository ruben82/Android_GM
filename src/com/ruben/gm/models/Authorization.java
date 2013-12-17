package com.ruben.gm.models;

/**
 * Created by IntelliJ IDEA.
 * User: r.deluca
 * Date: 04/07/13
 * Time: 10.53
 * To change this template use File | Settings | File Templates.
 */
public class Authorization {

    public String token;
    public String expireIn;
    public String tokenType;

    public Authorization(String token, String expireIn, String tokenType) {
        this.token = token;
        this.expireIn = expireIn;
        this.tokenType = tokenType;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "token='" + token + '\'' +
                ", expireIn='" + expireIn + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }
}
