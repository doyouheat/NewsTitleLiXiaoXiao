package lixiaoxiao.bwie.com.newstitlelixiaoxiao.networkinfo.httputils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/14 18:49
 */
public class Utils {

    public  static   void getHttpRes(Context context,String  url,final  HttpGetContentInterface  contentInterface){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest  request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                 contentInterface.success(s);
            }
         }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);
    }

    public  interface   HttpGetContentInterface{
        public  void  success(String content);
    }
}
