package lixiaoxiao.bwie.com.newstitlelixiaoxiao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;

import java.util.ArrayList;
import java.util.Map;

import lixiaoxiao.bwie.com.newstitlelixiaoxiao.R;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.fragment.FirstFragment;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.fragment.FourthFragment;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.fragment.SecondFragment;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.fragment.ThirdFragment;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.networkinfo.networkinfo.NetWorkInfo;
public  class MainActivity extends FragmentActivity implements View.OnClickListener {
    static int num = 0;
    int[] image = new int[]{R.mipmap.new_home_image, R.mipmap.b_newvideo_tabbar_press, R.mipmap.b_newcare_tabbar_press, R.mipmap.b_newnologin_tabbar_press};
    int[] noimage = new int[]{R.mipmap.b_newhome_tabbar, R.mipmap.b_newvideo_tabbar, R.mipmap.b_newcare_tabbar, R.mipmap.b_newnologin_tabbar};
    private ArrayList<TextView> tv_list = new ArrayList<>();
    private ArrayList<ImageView> iv_list = new ArrayList<>();
    private TextView tv_first, tv_second, tv_third, tv_fourth;
    private ImageView iv_first, iv_second, iv_third, iv_fourth;
    private RelativeLayout rl_first, rl_second, rl_third, rl_fourth;
    private FirstFragment first;
    private SecondFragment second;
    private ThirdFragment third;
    private FourthFragment fourth;
    private Fragment fragment;
    int   theme = R.style.AppTheme;
    private RelativeLayout night;
    private TextView nick;
    private UMShareAPI shareAPI;
    private ImageView iv_head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //恢复数据 做判空
        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt("theme");
            //设置主题 此方法必须在setContentView()之前调用
            setTheme(theme);
        }
        setContentView(R.layout.activity_main);
        SlidingMenu slidingMenu= new SlidingMenu(this);
        setSlidingMenu(slidingMenu);
        judgement();
        getViews();
        setColor(num);
        if (first== null) {
            first= new FirstFragment();
        }
        setFragment(first);
    }
    //侧拉页面
    private void setSlidingMenu(SlidingMenu slidingMenu) {
        //设置侧滑方向
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置侧滑宽度
        slidingMenu.setBehindOffset(200);
        //让侧滑依附到activity上
        slidingMenu.attachToActivity(MainActivity.this, SlidingMenu.SLIDING_CONTENT);
        //设置侧滑布局
       slidingMenu.setMenu(R.layout.side_pull_layout);
        night = (RelativeLayout) findViewById(R.id.night);
        nick = (TextView) findViewById(R.id.nick);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切换日夜间模式
                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                //重新创建
                recreate();
            }
        });
        shareAPI = UMShareAPI.get(MainActivity.this);
        nick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               shareAPI.doOauthVerify(MainActivity.this,SHARE_MEDIA.QQ, umAuthListener);
            }
        });
    }

    //保存数据
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("theme", theme);
    }

    //恢复数据
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }

    private void judgement() {
        boolean info = NetWorkInfo.getNetWorkInfo(this);
        if (info)
        {
            Toast.makeText(this, "网络已连接", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "连接网络更精彩", Toast.LENGTH_SHORT).show();

        }
    }

    private void getViews() {
        rl_first = (RelativeLayout) findViewById(R.id.rl_first);
        rl_second = (RelativeLayout) findViewById(R.id.rl_second);
        rl_third = (RelativeLayout) findViewById(R.id.rl_third);
        rl_fourth = (RelativeLayout) findViewById(R.id.rl_fourth);

        rl_first.setOnClickListener(this);
        rl_second.setOnClickListener(this);
        rl_third.setOnClickListener(this);
        rl_fourth.setOnClickListener(this);

        tv_first = (TextView) findViewById(R.id.tv_first);
        tv_second = (TextView) findViewById(R.id.tv_second);
        tv_third = (TextView) findViewById(R.id.tv_third);
        tv_fourth = (TextView) findViewById(R.id.tv_fourth);

        tv_list.add(tv_first);
        tv_list.add(tv_second);
        tv_list.add(tv_third);
        tv_list.add(tv_fourth);

        iv_first = (ImageView) findViewById(R.id.iv_first);
        iv_second = (ImageView) findViewById(R.id.iv_second);
        iv_third = (ImageView) findViewById(R.id.iv_third);
        iv_fourth = (ImageView) findViewById(R.id.iv_fourth);

        iv_list.add(iv_first);
        iv_list.add(iv_second);
        iv_list.add(iv_third);
        iv_list.add(iv_fourth);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rl_first:
                if (first== null) {
                    first = new FirstFragment();
                }
                setFragment(first);
                setColor(0);
                break;
            case R.id.rl_second:
                if (second== null) {
                    second = new SecondFragment();
                }
                setFragment(second);
                setColor(1);
                break;
            case R.id.rl_third:
                if (third == null) {
                    third = new ThirdFragment();
                }
                setFragment(third);
                setColor(2);
                break;
            case R.id.rl_fourth:
                if (fourth == null) {
                    fourth = new FourthFragment();
                }
                setFragment(fourth);
                setColor(3);
                break;

        }
    }

    private   void setFragment(Fragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment != null) {
            transaction.hide(fragment);
        }
        if (!f.isAdded()) {
            transaction.add(R.id.frame, f);
        }
        transaction.show(f);
        transaction.commit();
        fragment = f;
    }

    public void setColor(int index) {
        for (int i = 0; i < iv_list.size(); i++) {
            if (index == i) {
                iv_list.get(i).setImageResource(image[i]);
                tv_list.get(i).setTextColor(Color.RED);
            } else {
                iv_list.get(i).setImageResource(noimage[i]);
                tv_list.get(i).setTextColor(Color.BLACK);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        shareAPI.get(this).onActivityResult(requestCode,resultCode,data);
       Log.i("xxx", data.toString());
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            shareAPI .getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ,new MyUMListener());
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
    public  UMAuthListener umListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Log.i("-----", data.toString());
            Toast.makeText(MainActivity.this, ""+data.toString(), Toast.LENGTH_SHORT).show();
            String iconurL = data.get("iconurl");
            String name = data.get("name");
            ImageLoader.getInstance().displayImage(iconurL,iv_head);
            nick.setText(name);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

 public  class  MyUMListener implements UMAuthListener{


     @Override
     public void onStart(SHARE_MEDIA share_media) {

     }

     @Override
     public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
         Log.i("-----", map.toString());
         Toast.makeText(MainActivity.this, ""+map.toString(), Toast.LENGTH_SHORT).show();
         String iconurL = map.get("iconurl");
         String name = map.get("name");
         ImageLoader.getInstance().displayImage(iconurL,iv_head);
         nick.setText(name);
     }

     @Override
     public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

     }

     @Override
     public void onCancel(SHARE_MEDIA share_media, int i) {

     }

 }

}
