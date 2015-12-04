package natuan.org.androiddesigntablayout;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;

import com.alexvasilkov.gestures.internal.GestureDebug;
import com.androidquery.AQuery;
import com.androidquery.auth.FacebookHandle;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;


import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;
import com.parse.SaveCallback;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import natuan.org.androiddesigntablayout.fragments.utils.StorageUtils;
import natuan.org.androiddesigntablayout.handler.ApiBus;
import natuan.org.androiddesigntablayout.handler.ApiHandler;
import natuan.org.androiddesigntablayout.handler.ApiHandlerVM;
import natuan.org.androiddesigntablayout.handler.ApiService;
import natuan.org.androiddesigntablayout.handler.ApiServiceVM;
import natuan.org.androiddesigntablayout.push.PushManage;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by madhur on 3/1/15.
 */
public class MainApplication extends Application {



    public static final String APP_ID = "391414774312517";
    private static FacebookHandle handle;
    public static final String IMAGE_ENDPOINT = "http://candychat.net/";
    public static final String ENDPOINTCHAT = "http://api.candychat.net:1314";

    //public static final String ENDPOINTCHAT = "https://chat.vdomax.com:1314/api";

    public static final String ENDPOINT = "http://api.candychat.net";
    private static MainApplication Instance;
    public static volatile Handler applicationHandler = null;
    private ApiHandler someApiHandler;
    private ApiHandlerVM loginApiHandler;
    public static PrefManager mPref;
    public static String USER_TOKEN;
    public static final String APP_PERMISSIONS = "email,public_profile,user_friends";
    private static OkHttpClient sHttpClient;
    private static Activity mFbHandleActivity;
    private static Context sContext = null;

    public static FacebookHandle getFacebookHandle(Activity activity) {
        mFbHandleActivity = activity;
        handle = new FacebookHandle(activity, APP_ID, APP_PERMISSIONS);
        return handle;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        Instance = this;
        applicationHandler = new Handler(getInstance().getMainLooper());
        mPref = new PrefManager(getSharedPreferences("App", MODE_PRIVATE));
        NativeLoader.initNativeLibs(MainApplication.getInstance());

        FacebookSdk.sdkInitialize(getApplicationContext());

        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.DEVELOPER_ERRORS);

        String applicationID = "j6DTfeUL6JvI9PunllRInpQbUg3dJLCVNTvaAOfY";
        String clientKey = "VLESF9CjbpiRJ97A1XVllHZuBgO0TJrRJyNA3OL8";

        Parse.initialize(this, applicationID, clientKey);
        PushService.setDefaultPushCallback(this,
                PushManage.class);
        PushService.subscribe(this, "EN", PushManage.class);

        saveInstallation(0);

        //APA91bHWnClTagZ9PD8sqM3Xf2EHb1Y14mPeeSMJV1YYcwCtpGxDQVPNBIe144KLRzIgS6LajHebBqRmnzPs4oeB2xM_feChMBPK72lf5HVCEZuC-yTAZfUgYyi7GHc4gfenwmd8x2fZ


        loginApiHandler = new ApiHandlerVM(this, buildLoginApi(),
                ApiBus.getInstance());
        loginApiHandler.registerForEvents();

        // Load helper with default custom typeface (single custom typeface)




        GestureDebug.setDebugFps(true);
        GestureDebug.setDebugAnimator(true);


        someApiHandler = new ApiHandler(this, buildApi(), ApiBus.getInstance());
        someApiHandler.registerForEvents();
    }

    public static void saveInstallation(int userId) {
        final ParseInstallation installation = ParseInstallation
                .getCurrentInstallation();

        installation.put("user_id", userId);
        installation.saveInBackground(new SaveCallback() {
            public void done(com.parse.ParseException e) {
                if (e == null) {
                    System.out.println("ok");
                    //deviceToken = installation.get("deviceToken").toString();
                    //System.out.println(deviceToken);
                } else {
                    System.out.println("not ok" + e.getLocalizedMessage());
                }
            }
        });
    }

    public static Context getAppContext() {
        return sContext;
    }

    public static OkHttpClient getHttpClient() {
        if (sHttpClient == null) {
            sHttpClient = new OkHttpClient();
            int cacheSize = 10 * 1024 * 1024;
            File cacheLocation = new File(StorageUtils.getIdealCacheDirectory(MainApplication.getAppContext()).toString());
            cacheLocation.mkdirs();
            com.squareup.okhttp.Cache cache = new com.squareup.okhttp.Cache(cacheLocation, cacheSize);
            sHttpClient.setCache(cache);
        }
        return sHttpClient;
    }


    ApiServiceVM buildLoginApi() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    private DateFormat format = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss Z");

                    @Override
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        String s = json.getAsJsonPrimitive().getAsString();
                        try {
                            return format.parse(s);
                        } catch (ParseException ignore) {
                        }

                        return null;
                    }
                })
                .create();

        Log.e("HEY!", "called after post Login");

        return new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Accept", "application/json;versions=1");
                        request.addHeader("X-Auth-Token", mPref.token().getOr(""));
                        if (!mPref.token().getOr("").equals("")) {
                            //request.addHeader("X-Auth-Token",mPref.token().getOr(""));
                        }
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .build()
                .create(ApiServiceVM.class);


    }


    ApiService buildApi() {

        Log.e("HEY!", "after post");

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINTCHAT)

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

    public static void fetchBadge(Context context) {
        String userId = MainApplication.mPref.userId().getOr("0");
        if (!userId.equals("0")) {
            String url = "http://chat.vdomax.com/noti/index.php?a=badge&user_id=" + userId;
            AQuery aq = new AQuery(context);
            HashMap<String, Object> params = new HashMap<>();

            aq.ajax(url, JSONObject.class, context,
                    "updateBadgeCb");
        }

    }

    public static void removeInstallation(int userId) {
        final ParseInstallation installation = ParseInstallation
                .getCurrentInstallation();

        installation.deleteInBackground();
    }

    public static void logout(Context context) {
        mPref.isLogin().put(false).commit();
        mPref.clear().commit();
        boolean isLogin = mPref.isLogin().getOr(false);
        PushService
                .unsubscribe(getAppContext(), "EN");
        MainApplication.removeInstallation(Integer.parseInt(mPref.userId().getOr("0")));
        if (mFbHandleActivity != null)
            getFacebookHandle(mFbHandleActivity).unauth();
//        ParsePush.unsubscribeInBackground("EN");
        Log.e("isLogin", ":::" + isLogin);
    }

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }


    public static MainApplication getInstance() {
        return Instance;
    }

    /**
     * Multiple custom typefaces support
     */


    public static String getENDPOINT() {
        return ENDPOINTCHAT;
    }

    public static Handler getApplicationHandler() {
        return applicationHandler;
    }

    public ApiHandler getSomeApiHandler() {
        return someApiHandler;
    }


    public PrefManager getPrefManager() {
        return mPref;
    }


}
