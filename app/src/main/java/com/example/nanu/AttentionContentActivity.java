package com.example.nanu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nanu.R;

public class AttentionContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_content);//整个页面都是TextView
        TextView tv = (TextView)findViewById(R.id.tv_attention);
        tv.setText("关注者的具体页面");
    }
}
