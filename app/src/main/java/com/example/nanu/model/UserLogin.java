package com.example.nanu.model;


import java.io.Serializable;

public class UserLogin implements Serializable {
    public static final String TABLE_NAME = "user_login";       // 数据表名
    public static final String COLUMN_ID = "id";                // 用户登录id
    public static final String COLUMN_NUMBER = "number";        // 用户登录账号
    public static final String COLUMN_PASSWORD= "password";     // 用户登录密码

    public int id;
    public String number;
    public String password;

    public UserLogin(){ }



    // 获取登录信息
    public long getId(){ return id; }
    public String getNumber(){ return number; }
    public String getPassword(){ return password; }

    // 设置登录信息
    public void setId(int id){ this.id = id;}
    public void setNumber(String number){ this.number = number;}
    public void setPassword(String password){ this.password = password; }

    @Override
    public String toString(){
        return "UserLogin [id=" + id
                + ",number="+number
                + ",password="+password
                + ",password="+password+"]";
    }
}
