package lixiaoxiao.bwie.com.newstitlelixiaoxiao.fragment;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.R;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.adapter.MyVideoBaseAdapter;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.bean.NewsTitle;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.bean.VideoBean;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.networkinfo.httputils.HttpUtils;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.networkinfo.httputils.Utils;

public class VideoFragment extends Fragment implements  PullToRefreshListView.OnRefreshListener2<ListView>,Utils.HttpGetContentInterface{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private View view;
    private PullToRefreshListView pull;
   static  int   page=0;
    private MyVideoBaseAdapter adapter;

    public VideoFragment() {
        // Required empty public constructor
    }

    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
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
        view = inflater.inflate(R.layout.fragment_video, container, false);
        getViews();
        String videoUrl = HttpUtils.getVideoUrl(Integer.parseInt(mParam1), page+ "");
        Utils.getHttpRes(getActivity(),videoUrl,this);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
       // JCVideoPlayer.releaseAllVideos();
    }

    private void getViews() {
        pull = (PullToRefreshListView) view.findViewById(R.id.pull);
        pull = (PullToRefreshListView) view.findViewById(R.id.pull);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        pull.setOnRefreshListener(this);
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        page++;
        String videoUrl = HttpUtils.getVideoUrl(Integer.parseInt(mParam1), page+ "");
        Utils.getHttpRes(getActivity(),videoUrl,this);

    }

    @Override
    public void success(String content) {
        Gson gson = new Gson();
        List<VideoBean> beanList=new ArrayList<>();

        JSONObject resultObject = null;
        try {
            resultObject = new JSONObject(content);
            Iterator<String> keys = resultObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONArray nextArray = resultObject.optJSONArray(next);
                for (int i = 0; i < nextArray.length(); i++) {
                    JSONObject object = nextArray.optJSONObject(i);
                    VideoBean tBean = gson.fromJson(object.toString(), VideoBean.class);
                    beanList.add(tBean);
                }
            }
            Toast.makeText(getActivity(), ""+beanList.toString(), Toast.LENGTH_SHORT).show();
            Log.i("beanList",beanList.toString());
           // adapter = new MyVideoBaseAdapter(beanList, getActivity());

           // pull.setAdapter(adapter);
           // pull.onRefreshComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
