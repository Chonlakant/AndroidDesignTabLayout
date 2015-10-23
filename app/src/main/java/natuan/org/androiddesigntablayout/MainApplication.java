package natuan.org.androiddesigntablayout;

import android.app.Application;
import android.content.Context;
import android.util.Log;


/**
 * Created by Mac on 3/2/15.
 */
public class MainApplication extends Application {


    private static PrefManager prefManager;

    @Override public void onCreate() {
        super.onCreate();
        prefManager = new PrefManager(getSharedPreferences("App", MODE_PRIVATE));

    }


    public static PrefManager getPrefManager() {
        return prefManager;
    }


    public static void logout(Context context) {
        prefManager.isLogin().put(false).commit();
        prefManager.clear().commit();
        boolean isLogin = prefManager.isLogin().getOr(false);

//        ParsePush.unsubscribeInBackground("EN");
        Log.e("isLogin",":::"+isLogin);
    }

}
