package com.example.nanu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.nanu.adapter.AppController;
import com.example.nanu.data_structure.SQLiteDB;
import com.example.nanu.data_structure.UserDesign;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesignContentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private int[] designImage1 = { R.drawable.jiating1, R.drawable.jiating2,
            R.drawable.jiating3,R.drawable.jiating4, R.drawable.jiating5};
    private String[] designText1 = {"人物特写 ","古风模特 ","微距景色 ","小白微单 ","旅拍计划 "};

    private int[] designHeadImage = { R.drawable.dianzi1, R.drawable.dianzi2,
            R.drawable.dianzi3,R.drawable.dianzi4,
            R.drawable.dianzi5,R.drawable.dianzi6};
    private String[] designHeadText ={"8K 从容阅不凡","无线降噪运动耳机","随身音响","Sony降噪豆","SurfaceHeadPhones2","VR眼镜"};
    private SimpleAdapter simpleAdapter;


    private int design_id;
    private static int typeFlag=0;
    private List<UserDesign> userDesignsList = new ArrayList<UserDesign>();
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_content);

        TextView name = (TextView)findViewById(R.id.name);
        TextView type = (TextView)findViewById(R.id.type);
        TextView introduction = (TextView)findViewById(R.id.introduction);
        TextView commendation = (TextView)findViewById(R.id.commendation);
        final NetworkImageView image = (NetworkImageView) findViewById(R.id.image);
        Intent intent = getIntent();
        String getDesignId = (String) intent.getSerializableExtra("design_id");
        design_id = Integer.valueOf(getDesignId) + 1;

        // 获取设计专题信息
        userDesignsList = SQLiteDB.getInstance(getApplicationContext()).loadUserDesign(design_id);
        for( UserDesign ud : userDesignsList){
//            System.out.println(ud.toString());
            name.setText(ud.getName());
            image.setImageUrl(ud.getImage(),imageLoader);
            if(ud.getType()==0){ type.setText("已加入小组"); typeFlag=0;}
            else{type.setText("未加入小组"); typeFlag=1;}
            introduction.setText(ud.getIntroduction());
//            commendation.setText(String.valueOf(ud.getCommendation()));
        }


        // 显示壁纸or头像\
        gridView = (GridView) findViewById(R.id.showGridView);
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        if(typeFlag==1){
            simpleAdapter = new SimpleAdapter(this, dataList, R.layout.item,
                    new String[] { "image", "text" }, new int[] { R.id.designImage, R.id.designText });
        }else{
            simpleAdapter = new SimpleAdapter(this, dataList, R.layout.item_head,
                    new String[] { "image", "text" }, new int[] { R.id.designImage, R.id.designText });
        }
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);


    }



    public void getData() {
        int length=0;
        if(typeFlag==1){length = designImage1.length;}
        else{length = designHeadImage.length;}
        for (int i = 0; i < length; i ++) {
            if(typeFlag==1){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("image" ,designImage1[i]);
                map.put("text", designText1[i]);
                dataList.add(map);
            }
            else{
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("image" ,designHeadImage[i]);
                map.put("text", designHeadText[i]);
                dataList.add(map);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//        Toast.makeText(this,"第"+position+"个",Toast.LENGTH_SHORT).show();
        Bitmap bitmap;
        if(typeFlag==1) {
            bitmap = (Bitmap) BitmapFactory.decodeResource(getResources(),designImage1[position]);
        } else{bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.design_content_2);}
        saveImageToGallery(getApplicationContext(),bitmap);
    }

    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
            Toast.makeText(context,"成功加入小组",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));

    }

}
