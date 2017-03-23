package lixiaoxiao.bwie.com.newstitlelixiaoxiao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.R;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.bean.VideoBean;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/16 19:30
 */
public class MyVideoBaseAdapter extends BaseAdapter {
    List<VideoBean> list;
    private Context  context;

    public MyVideoBaseAdapter(List<VideoBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return   list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  v;
        if (convertView==null)
        {
            v=new ViewHolder();
            convertView=View.inflate(context, R.layout.video_iteam_layout,null);
           v.jiecao= (JCVideoPlayer) convertView.findViewById(R.id.jiecao);
            v.name= (TextView) convertView.findViewById(R.id.name);
            v.play_count= (TextView) convertView.findViewById(R.id.play_count);
            v.iv= (ImageView) convertView.findViewById(R.id.iv);
;            v.count= (TextView) convertView.findViewById(R.id.count);
            convertView.setTag(v);
        }else {
            v= (ViewHolder) convertView.getTag();
        }
     v.jiecao.setUp(list.get(position).getMp4_url(),list.get(position).getTitle());
        ImageView view = v.jiecao.ivThumb;
        ImageLoader.getInstance().displayImage(list.get(position).getCover(),view);
        v.name.setText(list.get(position).getTopicName());
        v.play_count.setText(list.get(position).getPtime());
        v.iv.setImageResource(R.mipmap.tab_comment);
        v.count.setText(list.get(position).getLength());
        return   convertView;
    }

    static   class   ViewHolder{
           JCVideoPlayer   jiecao;
           TextView  name,play_count,count;
        ImageView  iv;
    }
}
