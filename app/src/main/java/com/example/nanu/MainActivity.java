package com.example.nanu;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.nanu.model.UserLogin;
import com.example.nanu.data_structure.SQLiteDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener,
        DesignFragment.OnFragmentInteractionListener, com.example.nanu.MineFragment.OnFragmentInteractionListener,RecommodFragment.OnFragmentInteractionListener,AttentionFragment.OnFragmentInteractionListener{

    private RadioGroup rg_tab_bar;
    private RadioButton rb_design;
    private ListFragment listFragment;
    private DesignFragment designFragment;
    private MineFragment mineFragment;
    private FragmentManager fManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 显示登录界面
        setContentView(R.layout.user_login);

        Button loginUserLogin = findViewById(R.id.loginUserLogin);
        Button registerUserLogin = findViewById(R.id.registerUserLogin);
        final EditText numberUserLogin = findViewById(R.id.numberUserLogin);
        final EditText passwordUserLogin = findViewById(R.id.passwordUserLogin);

        // 登录监听
        loginUserLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isEmpty()==true){System.out.println("请先填写登录信息");}
                else{
                    System.out.println("信息填写完成");

                    String number = numberUserLogin.getText().toString().trim();
                    String password = passwordUserLogin.getText().toString().trim();
                    Log.i("TAG",number+"_"+password);

                    UserLogin userLogin = new UserLogin();
                    userLogin.setNumber(number);
                    userLogin.setPassword(password);

                    //数据库核查登录信息
                    int resUserLogin = SQLiteDB.getInstance(getApplicationContext()).queryUserLogin(number,password);
                    if(resUserLogin == 1){
                        // 登录成功后跳转到主页面
                        Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                        setContentView(R.layout.activity_main);

                        // 这里写Fragment
                        fManager=getSupportFragmentManager();
                        rg_tab_bar = (RadioGroup)findViewById(R.id.rg_tab_bar);

                        //底部按钮监听
                        rg_tab_bar.setOnCheckedChangeListener(new TabOnCheckedChangeListener());

                        //默认主页
                        rb_design = (RadioButton) findViewById(R.id.rb_design);
                        rb_design.setChecked(true);

                    }else if(resUserLogin == 0){
                        // 用户名不存在
                        Toast.makeText(MainActivity.this,"用户名不存在，请重新登录或注册",Toast.LENGTH_LONG).show();
                    }else{
                        // 密码错误
                        Toast.makeText(MainActivity.this,"密码错误，请重新登录",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // 注册按钮
        registerUserLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 跳转至注册界面
                Intent intent = new Intent(MainActivity.this,UserRegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    // 判断登录账号与登录密码是否为空
    public boolean isEmpty(){
        EditText number = findViewById(R.id.numberUserLogin);
        EditText password = findViewById(R.id.passwordUserLogin);
        if(TextUtils.isEmpty(number.getText()) && TextUtils.isEmpty(password.getText())){
            Toast.makeText(MainActivity.this,"登录账号为空，登录密码为空",Toast.LENGTH_LONG).show();;
            return true;
        }
        if(TextUtils.isEmpty(number.getText())){
            Toast.makeText(MainActivity.this,"登录账号为空",Toast.LENGTH_LONG).show();
            return true;
        }
        if(TextUtils.isEmpty(password.getText())){
            Toast.makeText(MainActivity.this,"登录密码为空",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
    }

    //底部按钮监听的内部类
    private class TabOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            FragmentTransaction fTransaction = fManager.beginTransaction();
            //隐藏所有的fragment
            hideAllFragment(fTransaction);
            switch (checkedId) {
                case R.id.rb_home:

                    if (listFragment == null) {
                        listFragment = new ListFragment();
                        fTransaction.add(R.id.ly_content, listFragment);
                    } else {
                        fTransaction.show(listFragment);
                    }
                    break;
                case R.id.rb_design:

                    if (designFragment == null) {
                        designFragment = new DesignFragment();
                        fTransaction.add(R.id.ly_content, designFragment);
                    } else {
                        fTransaction.show(designFragment);
                    }
                    break;
                case R.id.rb_mine:

                    if (mineFragment == null) {
                        mineFragment = new MineFragment();
                        fTransaction.add(R.id.ly_content, mineFragment);
                    } else {
                        fTransaction.show(mineFragment);
                    }
                    break;
            }
            //提交事务
            fTransaction.commit();
        }
    }
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(listFragment!=null)fragmentTransaction.hide(listFragment);
        if(designFragment!=null)fragmentTransaction.hide(designFragment);
        if(mineFragment!=null)fragmentTransaction.hide(mineFragment);
    }
}
