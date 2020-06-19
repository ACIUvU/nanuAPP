package com.example.nanu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import com.example.nanu.R;
import com.example.nanu.model.AlbumHelper;
import com.example.nanu.model.Bimp;
import com.example.nanu.adapter.ImageGridAdapter;
import com.example.nanu.adapter.ImageGridAdapter.TextCallback;
import com.example.nanu.model.ImageItem;

public class ImageGridActivity extends AppCompatActivity {
    public static final String EXTRA_IMAGE_LIST = "imagelist";
//选择图片发布
    // ArrayList<Entity> dataList;//
    List<ImageItem> dataList;
    GridView gridView;
    ImageGridAdapter adapter;//
    AlbumHelper helper;
    Button bt;
    int LENGTH_LONG = 400;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(ImageGridActivity.this, "最多选择9张图片", Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_grid);

        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());

        System.out.println("dataList------test2");
        dataList = (List<ImageItem>) getIntent().getSerializableExtra(
                EXTRA_IMAGE_LIST);
        System.out.println("dataList------test3");
        initView();
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<String>();
                Collection<String> c = adapter.map.values();
                Iterator<String> it = c.iterator();
                for (; it.hasNext();) {
                    list.add(it.next());
                }

                if (Bimp.act_bool) {
                    Intent intent = new Intent(ImageGridActivity.this,
                            PublishActivity.class);
                    startActivity(intent);
                    Bimp.act_bool = false;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (Bimp.drr.size() < 9) {
                        Bimp.drr.add(list.get(i));
                    }
                }
                finish();
            }

        });
    }


    private void initView() {
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new ImageGridAdapter(ImageGridActivity.this, dataList,
                mHandler);
        gridView.setAdapter(adapter);
        adapter.setTextCallback(new TextCallback() {
            public void onListen(int count) {
                bt.setText("完成" + "(" + count + ")");
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                adapter.notifyDataSetChanged();
            }

        });

    }
}
