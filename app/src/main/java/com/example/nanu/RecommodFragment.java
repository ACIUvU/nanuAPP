package com.example.nanu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nanu.adapter.ListAdapter;
import com.example.nanu.model.RecommodData;
import com.example.nanu.data_structure.SQLiteDB;
import com.example.nanu.data_structure.UserArticle;
import com.example.nanu.data_structure.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecommodFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView list_recommod;
    private ListAdapter ma;
    private List<RecommodData> datas = new ArrayList<RecommodData>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<UserArticle> list;

    private OnFragmentInteractionListener mListener;

    public RecommodFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecommodFragment.
     */
    public static RecommodFragment newInstance(String param1, String param2) {
        RecommodFragment fragment = new RecommodFragment();
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
        View view = inflater.inflate(R.layout.fragment_recommod, container, false);

        list_recommod = (ListView)view.findViewById(R.id.list_recommod);
        ma = new ListAdapter(datas, getActivity());
        list_recommod.setAdapter(ma);
        //初始化
        initUser();
        initReconnodContent();

//        initReconnodContent();
        list =  SQLiteDB.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext()).loadUserArticle();

        for(UserArticle i:list){
            System.out.println(i.toString());
            System.out.println(SQLiteDB.getInstance(getActivity().getApplicationContext()).queryUserInfo(i.getUserId()).toString());
            RecommodData data = new RecommodData(i.getTitle(),SQLiteDB.getInstance(getActivity().getApplicationContext()).queryUserInfo(i.getUserId()).getName(),i.getContent(),R.mipmap.ic_launcher);
            datas.add(data);
        }

        list_recommod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 跳转显示详细设计内容页面

                Intent intent = new Intent(getActivity(), RecommodContentActivity.class);
                System.out.println("position="+position);
                intent.putExtra("article_id",String.valueOf(position));
                startActivity(intent);
            }

        });

        return view;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void initReconnodContent(){
        UserArticle userArticle1 = new UserArticle();
        userArticle1.setId(1);
        userArticle1.setUserId(1);
        userArticle1.setTitle("六月份即将举办的智源大会");
        userArticle1.setContent("本场大会届时将有 6 位图灵奖得主、百余位著名学者受邀出席，同开发者探讨未来十年人工智能将如何影响整个社会，以及寻找下一步的发展方向...");
        userArticle1.setCommendation(100);
        if(SQLiteDB.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext()).saveUserArticle(userArticle1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        UserArticle userArticle2 = new UserArticle();
        userArticle2.setId(2);
        userArticle2.setUserId(2);
        userArticle2.setTitle("一款颇为实用的开源工具：Penrose");
        userArticle2.setContent("你只需输入数学公式，便可快速生成极具美感的数学图表...");
        userArticle2.setCommendation(16);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserArticle(userArticle2)) System.out.println("插入文章成功!");
        else System.out.println("插入文章失败!");

        UserArticle userArticle3 = new UserArticle();
        userArticle3.setId(3);
        userArticle3.setUserId(3);
        userArticle3.setTitle("童话“小红帽”的变迁");
        userArticle3.setContent("从社会生产方失变迁解读不同时代的童话故事...");
        userArticle3.setCommendation(20);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserArticle(userArticle3)) System.out.println("插入文章成功!");
        else System.out.println("插入文章失败!");

        UserArticle userArticle4 = new UserArticle();
        userArticle4.setId(4);
        userArticle4.setUserId(5);
        userArticle4.setTitle("美国人民只提种族矛盾？");
        userArticle4.setContent("美国种族冲突层出不穷，背后其实是阶级问题。不解决阶级矛盾，就没法真正解决种族矛盾...");
        userArticle4.setCommendation(100);
        if(SQLiteDB.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext()).saveUserArticle(userArticle4)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        UserArticle userArticle5 = new UserArticle();
        userArticle5.setId(5);
        userArticle5.setUserId(4);
        userArticle5.setTitle("谷歌总部Googleplex有一个恐龙模型");
        userArticle5.setContent("谷歌打不开的时候还有一个小恐龙游戏，名为\"Stan\"。目的是提醒谷歌人，虽然最强，但要不断前进，不断创新，才能不过时或绝灭...");
        userArticle5.setCommendation(100);
        if(SQLiteDB.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext()).saveUserArticle(userArticle5)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        UserArticle userArticle6 = new UserArticle();
        userArticle6.setId(6);
        userArticle6.setUserId(3);
        userArticle6.setTitle("宠物眼中的你");
        userArticle6.setContent("如果你的宠物在你身边时，会发出特定的声音来引你注意，那就是它们给你起的名字。...");
        userArticle6.setCommendation(100);
        if(SQLiteDB.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext()).saveUserArticle(userArticle6)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        UserArticle userArticle7 = new UserArticle();
        userArticle7.setId(7);
        userArticle7.setUserId(3);
        userArticle7.setTitle("好片推荐");
        userArticle7.setContent("推荐一部真人秀节目《隐姓亿万富翁》，又名“富豪谷底求翻身”，豆瓣9.1。身价亿...");
        userArticle7.setCommendation(100);
        if(SQLiteDB.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext()).saveUserArticle(userArticle7)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");
    }


    public void initUser(){
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setId(1);
        userInfo1.setName("@GitHubDaily");
        userInfo1.setIntroduction("每日分享GitHub优质项目");
        userInfo1.setImage("https://api.androidhive.info/json/movies/2.jpg");
        //System.out.println("-------------1-----------"+userInfo1.toString());
        if(SQLiteDB.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext()).saveUserInfo(userInfo1)) System.out.println("插入用户信息成功!");
        else System.out.println("插入用户信息失败!");

        userInfo1.setId(2);
        userInfo1.setName("@开源工坊");
        userInfo1.setIntroduction("知名互联网分享博主");
        userInfo1.setImage("https://api.androidhive.info/json/movies/2.jpg");
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserInfo(userInfo1)) System.out.println("插入用户信息成功!");
        else System.out.println("插入用户信息失败!");

        userInfo1.setId(3);
        userInfo1.setName("@暗戳戳的偷偷吃瓜群众");
        userInfo1.setIntroduction("吃瓜群众");
        userInfo1.setImage("https://api.androidhive.info/json/movies/2.jpg");
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserInfo(userInfo1)) System.out.println("插入用户信息成功!");
        else System.out.println("插入用户信息失败!");

        userInfo1.setId(4);
        userInfo1.setName("@Google中国");
        userInfo1.setIntroduction("Google官方帐号");
        userInfo1.setImage("https://api.androidhive.info/json/movies/2.jpg");
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserInfo(userInfo1)) System.out.println("插入用户信息成功!");
        else System.out.println("插入用户信息失败!");

        userInfo1.setId(5);
        userInfo1.setName("@观视频工作室");
        userInfo1.setIntroduction("理性观世界");
        userInfo1.setImage("https://api.androidhive.info/json/movies/2.jpg");
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserInfo(userInfo1)) System.out.println("插入用户信息成功!");
        else System.out.println("插入用户信息失败!");
    }

}
