package com.example.nanu;
//注册界面
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nanu.data_structure.DatabaseHelper;
import com.example.nanu.data_structure.SQLiteDB;
import com.example.nanu.model.UserLogin;

public class UserRegisterActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        final Button registerUserRegister = (Button)findViewById(R.id.registerUserRegister);
        final EditText numberUserRegister = (EditText)findViewById(R.id.numberUserRegister);
        final EditText passwordUserRegister = (EditText)findViewById(R.id.passwordUserRegister);

        // 注册
        registerUserRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isEmpty()==true){System.out.println("没有完整填写信息");}
                else{System.out.println("完整填写信息");
                    String number = numberUserRegister.getText().toString();
                    String password = passwordUserRegister.getText().toString();

                    UserLogin userLogin = new UserLogin();
                    userLogin.setNumber(number);
                    userLogin.setPassword(password);

                    //待注册信息输入数据库进行核验
                    int resUserRegister = SQLiteDB.getInstance(getApplicationContext()).saveUserLogin(userLogin);
                    if( resUserRegister== 1){
                        // 注册成功
                        Toast.makeText(UserRegisterActivity.this,"注册成功请登录",Toast.LENGTH_LONG).show();
                    }else if(resUserRegister == -1){
                        // 登录账号已存在
                        Toast.makeText(UserRegisterActivity.this,"账号已存在，请登录",Toast.LENGTH_LONG).show();
                    }else{
                        // 注册失败
                    }
                    Intent intent = new Intent(UserRegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean isEmpty(){
        EditText number = findViewById(R.id.numberUserRegister);
        EditText password = findViewById(R.id.passwordUserRegister);
        if(TextUtils.isEmpty(number.getText()) || TextUtils.isEmpty(password.getText())){
            Toast.makeText(UserRegisterActivity.this,"请完整填写注册信息",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
