package lixiaoxiao.bwie.com.newstitlelixiaoxiao.adapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import lixiaoxiao.bwie.com.newstitlelixiaoxiao.R;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.bean.News;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/23 09:46
 */
public class MyThiredAdapter extends BaseAdapter {
    private List<News> list;
    private Context context;

    public MyThiredAdapter(List<News> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return  list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder1 v1=null;
        if (convertView==null){
                    v1=new  ViewHolder1();
              convertView=View.inflate(context, R.layout.thired_iteam_show,null);
            v1.iv_th = (ImageView) convertView.findViewById(R.id.iv_th);
            v1.th_name = (TextView) convertView.findViewById(R.id.th_name);
            v1.url = (TextView) convertView.findViewById(R.id.url);
            convertView.setTag(v1);
        }else {
                    v1= (ViewHolder1) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(position).getImageurl(),v1.iv_th);
        v1.th_name.setText(list.get(position).getName());
        v1.url.setText(list.get(position).getUrl());
        return   convertView;
    }
    static   class    ViewHolder1{
        TextView  th_name,url;
        ImageView iv_th;
}

}
