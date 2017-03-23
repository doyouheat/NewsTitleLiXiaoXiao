package lixiaoxiao.bwie.com.newstitlelixiaoxiao.networkinfo.networkinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/9 20:28
 */
public class NetWorkInfo {

    public  static  boolean  getNetWorkInfo(Context  context){
        ConnectivityManager    manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info==null)
        {
            return   false;
        }else {
            return true;
        }
    }
}
