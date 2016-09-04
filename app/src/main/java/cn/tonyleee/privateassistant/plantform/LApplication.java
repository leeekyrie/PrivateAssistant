package cn.tonyleee.privateassistant.plantform;

import android.app.Application;
import android.content.Context;

/**
 * Created by 李坤阳 on 2016/8/26.
 */
public class LApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
