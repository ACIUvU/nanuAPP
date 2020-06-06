package com.example.nanu.data_structure;

public class UserArticle {
    public static final String TABLE_NAME = "user_article";     // 数据表名
    public static final String ID = "id";                       // 文章id
    public static final String USER_ID = "user_id";             // 用户id
    public static final String TITLE = "title";                 // 文章标题
    public static final String CONTENT = "content";             // 文章内容
    public static final String COMMENDATION = "commendation";   // 文章点赞数
    public static final String IMAGE = "image";                 // 文章简图

    private int id;
    private int user_id;
    private String title;
    private String content;
    private int commendation;
    private int image;

    // 建立数据表

    public static final String CREATE_TABLE =
            "CREATE TABLE" + TABLE_NAME + "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + USER_ID + " INTEGER,"
                    + TITLE + " TEXT,"
                    + CONTENT + " TEXT,"
                    + COMMENDATION + " INTEGER,"
                    + IMAGE + " INTEGER)";



    public UserArticle(){}

    // 设置
    public void setId(int id){ this.id = id;}
    public void setUserId(int user_id){ this.user_id = user_id;}
    public void setTitle(String title){ this.title  =title;}
    public void setContent(String content){ this.content = content;}
    public void setCommendation(int commendation){ this.commendation = commendation;}
    public void setImage(int image){ this.image = image;}

    // 获取
    public int getId(){ return id;}
    public int getUserId(){ return user_id;}
    public String getTitle(){ return title;}
    public String getContent(){ return content;}
    public int getCommendation(){ return commendation;}
    public int getImage(){ return image;}

    // toString
    public String toString(){
        return "UserArticle [id=" + id
                + ",user_id="+user_id
                + ",title="+title
                + ",content="+content
                + ",image="+image
                + ",commendation="+commendation+"]";
    }
}
