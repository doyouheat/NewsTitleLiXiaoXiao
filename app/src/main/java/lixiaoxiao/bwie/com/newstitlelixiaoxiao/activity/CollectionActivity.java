package lixiaoxiao.bwie.com.newstitlelixiaoxiao.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;

import lixiaoxiao.bwie.com.newstitlelixiaoxiao.R;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.adapter.MyThiredAdapter;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.bean.News;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.database.dao.Dao;

public class CollectionActivity extends AppCompatActivity {

    private Dao dao;
    private SpringView spr;
    private ListView lv;
    private MyThiredAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_collection);
        dao = new Dao(this);
        spr = (SpringView)findViewById(R.id.spr);
        lv = (ListView)findViewById(R.id.lv_collection);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        spr.setType(SpringView.Type.FOLLOW);
        spr.setHeader(new DefaultHeader(this));
        spr.setFooter(new DefaultFooter(this));
        setAdapter();
    }
    private void setAdapter() {
        final ArrayList<News> list = dao.selectNews();
        adapter = new MyThiredAdapter(list, this);
        springListener();
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=     new Intent(CollectionActivity.this, DetailsActivity.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(CollectionActivity.this);
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
                            Toast.makeText(CollectionActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
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
    public  void  springListener(){
        spr.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter();
                        spr.onFinishFreshAndLoad();
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter();
                        spr.onFinishFreshAndLoad();
                    }
                }, 2000);
            }
        });
    }

}
