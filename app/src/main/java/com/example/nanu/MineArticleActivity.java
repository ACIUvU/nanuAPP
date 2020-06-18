package com.example.nanu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MineArticleActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_article_user);

        Button exit = (Button)findViewById(R.id.btn_exit);
        Button exit_app = (Button)findViewById(R.id.btn_exit_app);
        Button back = (Button)findViewById(R.id.btn_back);

        //退出登录（屏蔽返回键）
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MineArticleActivity.this,"退出登录",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(MineArticleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        exit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(MineArticleActivity.this, UserRegisterActivity.class);
                startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(FeedbackActivity.this,"",Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        setContentView(R.layout.activity_main);
                        finish();
                    }
                }, 500);
            }
        });
    }
}
