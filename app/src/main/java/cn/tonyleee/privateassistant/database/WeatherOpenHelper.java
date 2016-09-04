package cn.tonyleee.privateassistant.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 李坤阳 on 2016/7/30.
 */
public class WeatherOpenHelper extends SQLiteOpenHelper {

    /** 省存储表 建表语句 */
    public static final String CREATE_PROVINCE = "create table Province(" +
            "id integer primary key autoincrement," +
            "province_name text," +
            "province_code text)";

    /** 市存储表  建表语句*/
    public static final String CREATE_CTIY = "create table City(" +
            "id integer primary key autoincrement," +
            "city_name text," +
            "city_code text" +
            "province_id integer)";

    /** 县存储表 建表语句 */
    public static final String CREATE_COUNTY = "create table County("+
            "id integer primary key autoincrement," +
            "county_name text," +
            "county_code text" +
            "city_id integer)";

    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CTIY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

