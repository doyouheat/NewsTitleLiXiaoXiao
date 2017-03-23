package lixiaoxiao.bwie.com.newstitlelixiaoxiao.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/15 10:17
 */
public class MyApplication  extends Application {
    {


        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        ImageLoaderConfiguration icf = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(icf);
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this).build();
    }
}
