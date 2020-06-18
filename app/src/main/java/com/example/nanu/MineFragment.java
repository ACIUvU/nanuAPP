package com.example.nanu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


//”我的“一栏
public class MineFragment extends Fragment {


    EditText name;
    EditText sign;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MineFragment() {
        // Required empty public constructor
    }


    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mine, container,false);
        //TextView txt_mine = (TextView) view.findViewById(R.id.txt_mine);
        //txt_mine.setText("我的");

        //touxiang = (ImageButton)view.findViewById(R.id.ib_head);

        //按钮事件
        //final Button btn1 = (Button)view.findViewById(R.id.rb_mine_article);
        Button btn1 = (Button)view.findViewById(R.id.rb_mine_article);
        Button btn2 = (Button)view.findViewById(R.id.rb_mine_commendation);
        Button btn3 = (Button)view.findViewById(R.id.rb_mine_attention);
        Button btn4 = (Button)view.findViewById(R.id.rb_mine_feedback);
        Button btn5 = (Button)view.findViewById(R.id.rb_mine_protocol);
        ImageButton ibtn = (ImageButton)view.findViewById(R.id.ib_head);

        final EditText name = (EditText)view.findViewById(R.id.et_mine_info_name);
        final EditText sign = (EditText)view.findViewById(R.id.et_mine_info_sign);

        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click imagebutton");
                Toast.makeText(getActivity(),"个人资料",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), HeadActivity.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click button 1");
                Toast.makeText(getActivity(),"1",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), MineArticleActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click button 2");
                Toast.makeText(getActivity(),"发现",Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(getActivity(),MineAttention.class);
                //startActivity(intent);
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.ly_content, new DesignFragment()).commit();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click button 3");
                Toast.makeText(getActivity(),"关注",Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(getActivity(),MineAttention.class);
                //startActivity(intent);
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.ly_content, new AttentionFragment()).commit();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click button 4");
                Toast.makeText(getActivity(),"意见反馈",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click button 5");
                Toast.makeText(getActivity(),"用户协议",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), MineProtocolActivity.class);
                startActivity(intent);
            }
        });
        //initView();
        return view;
    }




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
