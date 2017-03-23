package lixiaoxiao.bwie.com.newstitlelixiaoxiao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lixiaoxiao.bwie.com.newstitlelixiaoxiao.R;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.bean.NewsTitle;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/14 19:10
 */
public class MyBaseAdapter extends BaseAdapter {

    private final  int  TYPE1=0;
    private final int  TYPE2=1;
    private List<NewsTitle> list;
    private Context  context;

    public MyBaseAdapter(List<NewsTitle>  list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getItemViewType(int position) {

        List<NewsTitle.ImgextraBean> imgextra = list.get(position).getImgextra();
        if (imgextra==null)
        {
           return  TYPE1;
        }else if (imgextra.size()==2)
        {
            return  TYPE2;
        }
        return  TYPE1;
    }
    @Override
    public int getViewTypeCount() {
        return 2;
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
        ViewHolder1  v1=null;
        ViewHolder2  v2=null;
        int  type=getItemViewType(position);
        if (convertView==null){
       switch (type){
           case  TYPE1:
               v1=new ViewHolder1();
               convertView=View.inflate(context, R.layout.homeiteam_type1_layout,null);
               v1.tit_name= (TextView) convertView.findViewById(R.id.tit_name);
               v1.comment= (TextView) convertView.findViewById(R.id.comment);
               v1.source= (TextView) convertView.findViewById(R.id.source);
               v1.iv= (ImageView) convertView.findViewById(R.id.iv);
               v1.iv_x= (ImageView) convertView.findViewById(R.id.iv_x);
               convertView.setTag(v1);
               break;
           case  TYPE2:
               v2=new ViewHolder2();
               convertView=View.inflate(context, R.layout.homeiteam_type2_layout,null);
               v2.tit_name= (TextView) convertView.findViewById(R.id.tit_name);
               v2.comment= (TextView) convertView.findViewById(R.id.comment);
               v2.source= (TextView) convertView.findViewById(R.id.source);
               v2.begin_time= (TextView) convertView.findViewById(R.id.begin_time);
               v2.iv1= (ImageView) convertView.findViewById(R.id.iv1);
               v2.iv2= (ImageView) convertView.findViewById(R.id.iv2);
               v2.iv3= (ImageView) convertView.findViewById(R.id.iv3);
               v2.iv_x= (ImageView) convertView.findViewById(R.id.iv_x);
               convertView.setTag(v2);
               break;
       }
        }else {
            switch (type){
                case  TYPE1:
                   v1= (ViewHolder1) convertView.getTag();
                    break;
                case  TYPE2:
                   v2= (ViewHolder2) convertView.getTag();
                    break;
            }
        }
        switch (type)
        {
            case  TYPE1:
               v1.tit_name.setText(list.get(position).getTitle());
                v1.source.setText(list.get(position).getSource());
                v1.comment.setText("浏览量："+list.get(position).getVotecount());
                v1.iv_x.setImageResource(R.mipmap.add_textpage_pressed);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(list.get(position).getImgsrc(),v1.iv);
                break;
            case  TYPE2:
                v2.tit_name.setText(list.get(position).getTitle());
                v2.comment.setText("浏览量："+list.get(position).getVotecount());
                v2.source.setText(list.get(position).getSource());
                v2.begin_time.setText(list.get(position).getPtime());
                v2.iv_x.setImageResource(R.mipmap.add_textpage_pressed);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(list.get(position).getImgextra().get(0).getImgsrc(),v2.iv1);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(list.get(position).getImgextra().get(1).getImgsrc(),v2.iv2);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(list.get(position).getImgsrc(),v2.iv3);
                break;
        }
        return   convertView;
    }
    static   class    ViewHolder1{
        TextView  tit_name,comment,source;
        ImageView iv_x,iv;
     }
    static   class    ViewHolder2{
        TextView  tit_name,comment,source,begin_time;
        ImageView iv_x,iv1,iv2,iv3;
    }
}
