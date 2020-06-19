package com.example.nanu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AttentionArticleActivity extends AppCompatActivity {
//进行测试的模块
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_article);
        TextView tv = (TextView)findViewById(R.id.tv_article);
        tv.setText("8K 从容阅不凡");
    }
}
