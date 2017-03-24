package lixiaoxiao.bwie.com.newstitlelixiaoxiao.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;

import java.util.Map;

import lixiaoxiao.bwie.com.newstitlelixiaoxiao.R;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.database.dao.Dao;

public class DetailsActivity extends AppCompatActivity {
    private WebView webview;
    private ProgressBar bar;
    private TextView tv;
    private String url;
    private String name;
    private Dao dao;
    private String imageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_details);
        webview=(WebView) findViewById(R.id.webview);
        bar=(ProgressBar) findViewById(R.id.bar);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        name = intent.getStringExtra("name");
        imageurl = intent.getStringExtra("imageurl");
        dao = new Dao(this);
        tv=(TextView) findViewById(R.id.tv);
        webview.getSettings().setDefaultTextEncodingName("gbk");
        webview.loadUrl(url);
        //设置安卓可以调用js的方法
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("javascript:want()");

        webview.setWebViewClient(new  WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                bar.setVisibility(View.GONE);
            }
        });
        webview.setWebChromeClient(new  WebChromeClient(){

            @Override
            public void onReceivedTitle(WebView view, String title) {
                // TODO Auto-generated method stub
                super.onReceivedTitle(view, title);
                tv.setText(title);
            }
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                super.onProgressChanged(view, newProgress);
                bar.setProgress(newProgress);
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.moremenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                boolean b = dao.addNews(name, url,imageurl);
                if (b){
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "收藏失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.share:
                UMImage image = new UMImage(DetailsActivity.this, imageurl);//网络图片
                UMWeb web = new UMWeb(url);
                web.setTitle(name);//标题
                web.setDescription("Do you heat");//描述

                new ShareAction(DetailsActivity.this)
                        .withText("hello")
                        .withMedia(image)
                        .withMedia(web)
                        .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener)
                        .open();
                break;
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    //分享监听
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Toast.makeText(DetailsActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(DetailsActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(DetailsActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
