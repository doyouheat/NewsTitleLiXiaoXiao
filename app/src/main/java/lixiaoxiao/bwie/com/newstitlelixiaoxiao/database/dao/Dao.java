package lixiaoxiao.bwie.com.newstitlelixiaoxiao.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import lixiaoxiao.bwie.com.newstitlelixiaoxiao.bean.News;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.database.MySQLiteDatabase;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/23 08:34
 */
public class Dao {

    private  SQLiteDatabase db;

    public Dao(Context context) {
        MySQLiteDatabase database = new MySQLiteDatabase(context);
        db = database.getWritableDatabase();
    }

    //数据库添加信息
    public boolean  addNews(String  name,String  url,String  imageurl){
        ContentValues  contents=new ContentValues();
        contents.put("name",name);
        contents.put("url",url);
        contents.put("imageurl",imageurl);
        long count = db.insert("news", null, contents);
        if (count>0)

            return  true;
          else
        return  false;
    }

    //数据库查询
    public ArrayList<News>  selectNews(){
        ArrayList<News>   list=new ArrayList<>();
        Cursor cursor = db.query("news", null, null, null, null, null, null);
        while (cursor.moveToNext())
        {
            int  id=cursor.getInt(cursor.getColumnIndex("id"));
            String  name=cursor.getString(cursor.getColumnIndex("name"));
            String  url=cursor.getString(cursor.getColumnIndex("url"));
            String  imageurl=cursor.getString(cursor.getColumnIndex("imageurl"));
            list.add(new News(id,name,url,imageurl));
        }
        return  list;
    }

    public  Boolean  daleteNews(int  id){

        int i = db.delete("news","id=?",new  String[]{id+""});
        if (i>0)
            return  true;
        else
        return  false;
    }
}
