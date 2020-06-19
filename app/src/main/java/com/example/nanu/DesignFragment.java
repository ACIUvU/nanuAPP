package com.example.nanu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.nanu.adapter.CustomListAdapter;
import com.example.nanu.adapter.AppController;
import com.example.nanu.data_structure.SQLiteDB;
import com.example.nanu.data_structure.UserDesign;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class DesignFragment extends Fragment{
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String url = "https://api.androidhive.info/json/movies.json";
    private ProgressDialog pDialog;
    private List<UserDesign> userDesignsList = new ArrayList<UserDesign>();
    private ListView listView;
    private CustomListAdapter adapter;

    private String name;
    private String image;
    private int type;
    private int commendation;
    private String introduction;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DesignFragment() { }

    // TODO: Rename and change types and number of parameters
    public static DesignFragment newInstance(String param1, String param2) {
        DesignFragment fragment = new DesignFragment();
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
        View view = inflater.inflate(R.layout.fragment_design, container, false);

//        TextView textView = (TextView)view.findViewById(R.id.design_tab_menu_search);

        // 初始化设计专题内容
        initUserDesign();

//        this.getActivity().setContentView(R.layout.activity_design);
//        this.getActivity().getWindow().setStatusBarColor(0xffffcc66);
//        listView = (ListView) this.getActivity().findViewById(R.id.list);

        listView = (ListView)view.findViewById(R.id.list);
        adapter = new CustomListAdapter(getActivity(), userDesignsList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("正在努力加载中...");
        pDialog.show();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFCC66")));

        // 创建Volley响应对象
        JsonArrayRequest movieReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                hidePDialog();

                InputStreamReader inputStreamReader;
                try {
                    inputStreamReader = new InputStreamReader(getActivity().getAssets().open("test.json"),"UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    int i=0,index=0;
                    while ((line = bufferedReader.readLine()) != null){
                        if(i==1){ name=line.substring(17,line.length()-2); }
                        if(i==2){ image=line.substring(18,line.length()-2); }
                        if(i==3){
                            String demo=line.substring(16,line.length()-1);
                            type=Integer.valueOf(demo.toString());
                        }
                        if(i==4){
                            String demo=line.substring(24,line.length()-1);
                            commendation=Integer.valueOf(demo.toString());
                        }
                        if(i==5){
                            introduction = line.substring(25,line.length()-1);
                        }
                        if(i==6){
                            UserDesign userDesign = new UserDesign();
                            userDesign.setId(index);
                            userDesign.setName(name);
                            userDesign.setImage(image);
                            userDesign.setType(type);
                            userDesign.setCommendation(commendation);
                            userDesign.setIntroduction(introduction);
//                            System.out.println(userDesign.toString());
                            userDesignsList.add(userDesign);
                        }
                        if(i==7){ i=0;index++; }
                        stringBuilder.append(line);
                        i++;


                    }
                    inputStreamReader.close();
                    bufferedReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // 通知有关数据更改以便能显示更新后的数据
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // 加入队列
        AppController.getInstance().addToRequestQueue(movieReq);


        // 给listView添加点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(),"第"+position+"个item",Toast.LENGTH_SHORT).show();
                // 显示设计专题内容
//                List<UserDesign> test_list = new ArrayList<UserDesign>();
//                test_list = SQLiteDB.getInstance((getActivity().getApplicationContext())).loadUserDesign(position+1);
//                for( UserDesign ud : test_list){
//                    System.out.println(ud.toString());
//                }

                // 跳转显示详细设计内容页面
                Intent intent = new Intent(getActivity(), DesignContentActivity.class);
                intent.putExtra("design_id",String.valueOf(position));
                startActivity(intent);
            }

        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }

    }

    @Override
    public void onAttach(Context context) throws RuntimeException {
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    // 初始化设计专题内容
    public void initUserDesign(){

        UserDesign userDesign1 = new UserDesign();
        userDesign1.setName("电子发烧友？快来看看吧~");
        userDesign1.setType(0);
        userDesign1.setIntroduction("索尼大法新品发布会！");
        userDesign1.setCommendation(20);
        userDesign1.setImage("https://www.sony.com.cn/content/dam/sonyportal/aboutsony/images/electronics.jpg");
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        userDesign1.setName("摄影爱好者upup");
        userDesign1.setImage("R.drawable.dianzi1");
        userDesign1.setType(1);
        userDesign1.setIntroduction("佳能？尼康？还是大法？");
        userDesign1.setCommendation(100);
        userDesign1.setImage("https://www.sony.com.cn/content/dam/sonyportal/aboutsony/images/professional.jpg");
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        userDesign1.setName("we are 伐木累~");
        userDesign1.setType(2);
        userDesign1.setIntroduction("优秀奶爸聚集地~");
        userDesign1.setCommendation(50);
        userDesign1.setImage("https://www.sony.com.cn/content/dam/sonyportal/aboutsony/images/financial.jpg");
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");


        userDesign1.setName("灵感 到处都是~");
        userDesign1.setImage("https://www.sony.com.cn/content/dam/sonyportal/aboutsony/images/design.jpg");
        userDesign1.setType(3);
        userDesign1.setIntroduction("甲方爸爸要的五彩斑斓的黑就在这里！");
        userDesign1.setCommendation(35);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        userDesign1.setName("听你想听");
        userDesign1.setImage("https://www.sony.com.cn/content/dam/sonyportal/aboutsony/images/music.jpg");
        userDesign1.setType(4);
        userDesign1.setIntroduction("新锐歌手！小众歌单！这里有你喜欢的一切！");
        userDesign1.setCommendation(56);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        userDesign1.setName("轻奢路线与众不同");
        userDesign1.setImage("https://www.chanel.com/i18n/zh_CN/slides/1920_Inside_chanel_CN_0420_.jpg");
        userDesign1.setType(5);
        userDesign1.setIntroduction("那些战场上的飒爽英姿");
        userDesign1.setCommendation(20);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("世界优秀工匠的卓越作品");

        userDesign1.setName("今天你编程了么？~");
        userDesign1.setImage("https://www.python.org/static/img/python-logo.png");
        userDesign1.setType(6);
        userDesign1.setIntroduction("最好的语言是？Python？Cpp？还是PHP？");
        userDesign1.setCommendation(35);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        userDesign1.setName("漫威——漫画世界");
        userDesign1.setImage("https://api.androidhive.info/json/movies/7.jpg");
        userDesign1.setType(6);
        userDesign1.setIntroduction("谁还不是个拯救世界的英雄呢？~");
        userDesign1.setCommendation(98);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");


    }

}
