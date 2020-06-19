package com.example.nanu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nanu.AttentionContentActivity;
import com.example.nanu.R;
import com.example.nanu.model.AttionArticle;

import java.util.List;

//import androidx.fragment.app.FragmentActivity;

public class AttionArticleAdapter extends BaseAdapter {
    private List<AttionArticle> mData;
    private Context mContext;
    public AttionArticleAdapter(List<AttionArticle> mData, Context mContext){
        this.mData = mData;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_recommod,null);
            viewHolder.image_article = (ImageView) view.findViewById(R.id.image);
            viewHolder.info_article = (TextView)view.findViewById(R.id.info);
            viewHolder.author_article= (TextView) view.findViewById(R.id.writer);
            viewHolder.title_article= (TextView) view.findViewById(R.id.title);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext, AttentionContentActivity.class);
                mContext.startActivity(intent);

            }
        });
        viewHolder.image_article.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(mContext,"点击头像",Toast.LENGTH_SHORT).show();
            }
        });

        AttionArticle data = mData.get(i);
        viewHolder.author_article.setText(data.getAuthor());
        viewHolder.info_article.setText(data.getInfo());
        viewHolder.title_article.setText(data.getTitle());
        viewHolder.image_article.setImageResource(data.getImage());



//        viewHolder.txt_item_title.setText(mData.get(i).getTitle());
        return view;
    }
    private class ViewHolder{
        public ImageView image_article;
        public TextView title_article;
        public TextView author_article;
        public TextView info_article;
    }

}
