package natuan.org.androiddesigntablayout;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import natuan.org.androiddesigntablayout.handler.ApiBus;
import natuan.org.androiddesigntablayout.handler.ApiHandler;
import natuan.org.androiddesigntablayout.handler.ApiService;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by madhur on 3/1/15.
 */
public class MainApplication extends Application {
    public static final String ENDPOINT = "http://ihdmovie.xyz/root";
    private static MainApplication Instance;
    public static volatile Handler applicationHandler = null;
    private ApiHandler someApiHandler;
    public static PrefManager mPref;
    @Override
    public void onCreate() {
        super.onCreate();

        Instance = this;

        applicationHandler = new Handler(getInstance().getMainLooper());
        mPref = new PrefManager(getSharedPreferences("App", MODE_PRIVATE));
        NativeLoader.initNativeLibs(MainApplication.getInstance());
        someApiHandler = new ApiHandler(this, buildApi(),
                ApiBus.getInstance());
        someApiHandler.registerForEvents();
    }


    ApiService buildApi() {

        Log.e("HEY!", "after post");

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)

                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        //request.addQueryParam("p1", "var1");
                        //request.addQueryParam("p2", "");
                    }
                })

                .build()
                .create(ApiService.class);
    }

    public static MainApplication getInstance() {
        return Instance;
    }
    public PrefManager getPrefManager() {
        return mPref;
    }

}
