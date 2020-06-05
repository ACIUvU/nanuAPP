package com.example.nanu.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nanu.R;
import com.example.nanu.adapter.BaseRecycleAdapter;
import com.example.nanu.adapter.SeachRecordAdapter;
import com.example.nanu.data_structure.SQLiteDB;


//首页的搜索模块
public class ListSearchActivity extends AppCompatActivity {
    private Button mbtn_serarch;
    private EditText met_search;
    private RecyclerView mRecyclerView;
    private TextView mtv_deleteAll;
    private SeachRecordAdapter mAdapter;

    private SQLiteDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_search);
        initViews();
    }

    private void initViews() {
        db =new SQLiteDB(this);
        mbtn_serarch = (Button) findViewById(R.id.btn_serarch);
        met_search = (EditText) findViewById(R.id.et_search);
        mtv_deleteAll = (TextView) findViewById(R.id.tv_deleteAll);
        mtv_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteData();
                mAdapter.updata(db.queryData(""));
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter =new SeachRecordAdapter(db.queryData(""),this);
        mAdapter.setRvItemOnclickListener(new BaseRecycleAdapter.RvItemOnclickListener() {
            @Override
            public void RvItemOnclick(int position) {
                db.delete(db.queryData("").get(position));

                mAdapter.updata(db.queryData(""));
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        //事件监听
        mbtn_serarch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (met_search.getText().toString().trim().length() != 0){
                    boolean hasData = db.hasData(met_search.getText().toString().trim());
                    if (!hasData){
                        db.insertData(met_search.getText().toString().trim());
                    }else {
                        Toast.makeText(ListSearchActivity.this, "该内容已在历史记录中", Toast.LENGTH_SHORT).show();
                    }

                    mAdapter.updata(db.queryData(""));

                }else {
                    Toast.makeText(ListSearchActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}


