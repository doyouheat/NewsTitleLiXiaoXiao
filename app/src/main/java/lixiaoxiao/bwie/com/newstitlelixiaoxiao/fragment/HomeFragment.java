package lixiaoxiao.bwie.com.newstitlelixiaoxiao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lixiaoxiao.bwie.com.newstitlelixiaoxiao.R;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.activity.DetailsActivity;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.adapter.MyBaseAdapter;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.bean.NewsTitle;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.networkinfo.httputils.HttpUtils;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.networkinfo.httputils.Utils;

public class HomeFragment extends Fragment implements PullToRefreshListView.OnRefreshListener2<ListView>,Utils.HttpGetContentInterface{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View view;
    private PullToRefreshListView pull;
    private  int page = 0;
    private String url;
    private MyBaseAdapter adapter;
    private List<NewsTitle> beanList;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        getViews();
        Utils.getHttpRes(getActivity(),url,this);
        pull.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=     new Intent(getActivity(), DetailsActivity.class);
                NewsTitle title = beanList.get(position - 1);
                intent.putExtra("name",title.getTitle());
                intent.putExtra("url",title.getUrl());
                intent.putExtra("imageurl",title.getImgsrc());
                startActivity(intent);
            }
        });
        return  view;
    }

    private void getViews() {
        pull = (PullToRefreshListView) view.findViewById(R.id.pull);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        pull.setOnRefreshListener(this);
        url = HttpUtils.getUrl(Integer.parseInt(mParam1), page + "");

    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
       adapter.notifyDataSetChanged();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

    adapter.notifyDataSetChanged();
    }

    @Override
    public void success(String content) {

        Gson gson = new Gson();
        beanList = new ArrayList<>();
        try {
            JSONObject  resultObject = new JSONObject(content);
            Iterator<String> keys = resultObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONArray nextArray = resultObject.optJSONArray(next);
                for (int i = 0; i < nextArray.length(); i++) {
                    JSONObject object = nextArray.optJSONObject(i);
                    NewsTitle tBean = gson.fromJson(object.toString(), NewsTitle.class);
                    beanList.add(tBean);
                }
            }
            adapter = new MyBaseAdapter(beanList, getActivity());
            pull.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
