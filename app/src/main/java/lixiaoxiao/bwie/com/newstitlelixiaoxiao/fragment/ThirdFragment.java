package lixiaoxiao.bwie.com.newstitlelixiaoxiao.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.R;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.activity.DetailsActivity;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.adapter.MyThiredAdapter;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.bean.News;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.bean.NewsTitle;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.database.dao.Dao;

import static com.jeremyfeinstein.slidingmenu.lib.R.attr.title;

public class ThirdFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1,mParam2;
    private View view;
    private SpringView spring;
    private ListView lv;
    private Dao dao;
    private MyThiredAdapter adapter;
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
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
        view = inflater.inflate(R.layout.fragment_third, container, false);
        infoView();
        setAdapter();

        return  view;
    }
    private void setAdapter() {
        final ArrayList<News> list = dao.selectNews();
        adapter = new MyThiredAdapter(list, getActivity());
        springListener();
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=     new Intent(getActivity(), DetailsActivity.class);
                News  news = (News) adapter.getItem(position);
                intent.putExtra("name",news.getName());
                intent.putExtra("url",news.getUrl());
                intent.putExtra("imageurl",news.getImageurl());
                startActivity(intent);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new Builder(getActivity());
                final News news = (News) adapter.getItem(position);
                builder.setTitle("删除" + news.getName());
                builder.setMessage("确认删除");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        int id1 = news.getId();
                        Boolean aBoolean = dao.daleteNews(id1);
                        if (aBoolean){
                            list.remove(position);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return  true;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        setAdapter();
    }

    private void infoView() {
        dao = new Dao(getActivity());
        spring = (SpringView) view.findViewById(R.id.spring);
        lv = (ListView) view.findViewById(R.id.lv);
        spring.setType(SpringView.Type.FOLLOW);
        spring.setHeader(new DefaultHeader(getActivity()));
        spring.setFooter(new DefaultFooter(getActivity()));
    }



  public  void  springListener(){
      spring.setListener(new SpringView.OnFreshListener() {
          @Override
          public void onRefresh() {
              new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      setAdapter();
                      spring.onFinishFreshAndLoad();
                  }
              }, 2000);
          }

          @Override
          public void onLoadmore() {

              new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      setAdapter();
                      spring.onFinishFreshAndLoad();
                  }
              }, 2000);
          }
      });
  }

}
