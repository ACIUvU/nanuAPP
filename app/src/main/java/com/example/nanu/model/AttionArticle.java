package com.example.nanu.model;

public class AttionArticle {

    private String author;
    private String title;
    private String info;
    private int image;

    // 建立数据表

    public AttionArticle(String title, String info, String author, int imge){
        this.title = title;
        this.info = info;
        this.author=author;
        this.image=imge;
    }

    // 获取
    public String getTitle(){
        return title;
    }
    public String getAuthor(){ return author;}
    public String getInfo(){
        return info;
    }
    public int getImage(){
        return image;
    }

    public void setImageg(int image){
        this.image = image;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.title = author;
    }
    public void setInfo(String info){
        this.info = info;
    }
}
