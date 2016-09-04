package cn.tonyleee.privateassistant.database.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.tonyleee.privateassistant.database.WeatherOpenHelper;
import cn.tonyleee.privateassistant.plantform.LApplication;

/**
 * Created by 李坤阳 on 2016/7/30.
 */
public enum WeatherDb {
    INSTANCE(LApplication.getContext());


    public static final String DB_NAME = "weather";

    public static final int VERSION = 1;

    private static WeatherDb weatherDb;

    private SQLiteDatabase db;

    private WeatherDb(Context context){
        WeatherOpenHelper dbHelper = new WeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public void saveProvince(Province province){
        if(null != province){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }

    public List<Province>loadProvince(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor =db.query("Province",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while(cursor.moveToNext());
        }
        if(null != cursor){
            cursor.close();
        }
        return list;
    }

    public void saveCity(City city){
        if(null != city){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }

    public List<City>loadCity(int provinceId){
        List<City> list = new ArrayList<City>();
        Cursor cursor =db.query("City",null,"province_id = ?",new String[]{ String.valueOf(provinceId) },null,null,null);
        if(cursor.moveToFirst()){
            do{
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while(cursor.moveToNext());
        }
        if(null != cursor){
            cursor.close();
        }
        return list;
    }

    public void saveCounty(County county){
        if(null != county){
            ContentValues values = new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            values.put("city_id",county.getCityId());
            db.insert("County",null,values);
        }
    }

    public List<County>loadCounty(int cityId){
        List<County> list = new ArrayList<County>();
        Cursor cursor =db.query("County",null,"city_id = ?",new String[]{ String.valueOf(cityId) },null,null,null);
        if(cursor.moveToFirst()){
            do{
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            }while(cursor.moveToNext());
        }
        if(null != cursor){
            cursor.close();
        }
        return list;
    }






}
