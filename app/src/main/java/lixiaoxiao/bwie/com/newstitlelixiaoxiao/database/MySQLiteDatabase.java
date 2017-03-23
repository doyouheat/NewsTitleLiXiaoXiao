package lixiaoxiao.bwie.com.newstitlelixiaoxiao.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/23 08:27
 */
public class MySQLiteDatabase extends SQLiteOpenHelper {

    public MySQLiteDatabase(Context context) {
        super(context,"news.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create  table news(id Integer primary key autoincrement,name varchar(15),url varchar(20),imageurl varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
