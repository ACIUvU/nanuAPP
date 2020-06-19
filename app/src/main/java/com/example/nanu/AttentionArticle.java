package com.example.nanu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nanu.adapter.AttionArticleAdapter;
import com.example.nanu.model.AttionArticle;

import java.util.ArrayList;
import java.util.List;


public class AttentionArticle extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private ArrayList<AttentionArticle> datas = null;
    private FragmentManager fManager = null;

    private OnFragmentInteractionListener mListener;

    public AttentionArticle() {
        // Required empty public constructor
    }


    public static AttentionArticle newInstance(String param1, String param2) {
        AttentionArticle fragment = new AttentionArticle();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        final List<AttionArticle> datas = new ArrayList<>();
        ListView list_attention = (ListView)getView().findViewById(R.id.list_article);
        AttionArticleAdapter ma = new AttionArticleAdapter(datas, getActivity());
        list_attention.setAdapter(ma);
        AttionArticle data = new AttionArticle("GitHubDaily", "每日分享GitHub优质项目","123", R.drawable.github);
        datas.add(data);

        list_attention.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AttionArticle data1 = datas.get(i);
                Toast.makeText(getActivity(),data1.getAuthor(),Toast.LENGTH_SHORT).show();
                Intent intent  =new Intent(getActivity(), MainActivity.class);
                intent.putExtra("obj", (CharSequence) data1);
                Bundle bundle1 = new Bundle();
                bundle1.putString("arg1","关注的人详情");
                bundle1.putString("name",data1.getAuthor());
                intent.putExtra("bundle",bundle1);
                startActivity(intent);
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
