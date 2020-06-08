package com.example.nanu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nanu.adapter.AttentionPeopleAdapter;
import com.example.nanu.model.PeopleData;

import java.util.ArrayList;
import java.util.List;


public class AttentionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private ArrayList<PeopleData> datas = null;
    private FragmentManager fManager = null;

    private OnFragmentInteractionListener mListener;

    public AttentionFragment() {
        // Required empty public constructor
    }


    public static AttentionFragment newInstance(String param1, String param2) {
        AttentionFragment fragment = new AttentionFragment();
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
        View view = inflater.inflate(R.layout.fragment_attention, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        final List<PeopleData> datas = new ArrayList<>();
        ListView list_attention = (ListView)getView().findViewById(R.id.list_attention);
        AttentionPeopleAdapter ma = new AttentionPeopleAdapter(datas, getActivity());
        list_attention.setAdapter(ma);
        PeopleData data = new PeopleData("GitHubDaily", "每日分享GitHub优质项目",R.drawable.github);
        datas.add(data);
        data = new PeopleData("开源工坊", "知名互联网分享博主",R.drawable.qt);
        datas.add(data);
        data = new PeopleData("暗戳戳的偷偷吃瓜群众", "我只是个简简单单的吃瓜群众~",R.drawable.mayun);
        datas.add(data);
        data = new PeopleData("Google中国", "Google中国官方帐号",R.drawable.google);
        datas.add(data);
        data = new PeopleData("观视频工作室", "理性观世界",R.drawable.guan);
        datas.add(data);

        list_attention.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PeopleData data1 = datas.get(i);
                Toast.makeText(getActivity(),data1.getName(),Toast.LENGTH_SHORT).show();
                Intent intent  =new Intent(getActivity(),MainActivity.class);
                intent.putExtra("obj", (CharSequence) data1);
                Bundle bundle1 = new Bundle();
                bundle1.putString("arg1","关注的人详情");
                bundle1.putString("name",data1.getName());
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
