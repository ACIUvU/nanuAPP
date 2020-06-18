package com.example.nanu;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.nanu.R;

import java.io.File;
import java.io.IOException;

public class HeadActivity extends BaseActivity {
    protected static final int CHOOSE_PICTURE = 0;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA };
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private ImageView ImageView01;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_info);


        Button sub = (Button)findViewById(R.id.btn_info_sub);
        Button back = (Button)findViewById(R.id.btn_back);
        Button exit = (Button)findViewById(R.id.btn_exit);
        Button head = (Button)findViewById(R.id.button3);

        final EditText name = (EditText)findViewById(R.id.et_mine_info_name);
        final EditText sign = (EditText)findViewById(R.id.et_mine_info_sign);

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String s1 = name.getText().toString();
                String s2 = sign.getText().toString();

                String input = getResources().getString(R.string.user_name);
                //String output = String.format(input,s1);

                //String.format(input, String.format(input, s1));

                /*
                //String number = tv_mine_info_name.getText().toString();
                //String password = passwordUserRegister.getText().toString();
                UserLogin userLogin = new UserLogin();
                userLogin.setNumber(name);
                userLogin.setPassword(sign);

                int resUserRegister = SQLiteDB.getInstance(getApplicationContext()).saveUserLogin(userLogin);
                Intent intent = new Intent(HeadActivity.this,MainActivity.class);
                startActivity(intent);
                * */

                Toast.makeText(HeadActivity.this,"已申请修改！",Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        setContentView(R.layout.activity_main);
                        finish();
                    }
                }, 2000);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        setContentView(R.layout.activity_main);
                        finish();
                    }
                }, 500);
            }
        });

        //退出登录（屏蔽返回键）
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HeadActivity.this,"退出登录",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(HeadActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        /*
        @Override
        public boolean onKeyDown(int keyCode,KeyEvent event){
            if(keyCode==KeyEvent.KEYCODE_BACK)
                return true;
            return super.onKeyDown(keyCode, event);
        }//屏蔽返回键
        */
    }

    private void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, 100);
                        break;
                    case TAKE_PICTURE: // 拍照
                        if (verifyPermissions(HeadActivity.this, PERMISSIONS_STORAGE[2]) == 0) {
                            ActivityCompat.requestPermissions(HeadActivity.this, PERMISSIONS_STORAGE, 3);
                        }else{
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  //跳转到 ACTION_IMAGE_CAPTURE
                            //判断内存卡是否可用，可用的话就进行存储
                            //putExtra：取值，Uri.fromFile：传一个拍照所得到的文件，fileImg.jpg：文件名

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(HeadActivity.this.getExternalFilesDir(null), "zy.jpg")));
                            startActivityForResult(intent,101);
                        }
                }
            }
        });
        builder.create().show();
    }
    private File tempFile = null;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != RESULT_CANCELED){    //RESULT_CANCELED = 0(也可以直接写“if (requestCode != 0 )”)
            //读取返回码
            switch (requestCode){
                case 100:   //相册返回的数据（相册的返回码）
                    Uri uri01 = data.getData();
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri01));
                        ImageView01.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case 101:  //相机返回的数据（相机的返回码）
                    try {
                        tempFile = new File(HeadActivity.this.getExternalFilesDir(null),"fileImg.jpg");  //相机取图片数据文件
                        Uri uri02 = Uri.fromFile(tempFile);   //图片文件
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri02));
                        ImageView01.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
