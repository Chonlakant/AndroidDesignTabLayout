package natuan.org.androiddesigntablayout;

import android.app.Application;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;

import com.alexvasilkov.events.Events;
import com.alexvasilkov.gestures.internal.GestureDebug;
import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;

import natuan.org.androiddesigntablayout.handler.ApiBus;
import natuan.org.androiddesigntablayout.handler.ApiHandler;
import natuan.org.androiddesigntablayout.handler.ApiService;
import natuan.org.androiddesigntablayout.logic.FlickrApi;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by madhur on 3/1/15.
 */
public class MainApplication extends Application {
    private static PrefManager prefManager;
    private TypefaceCollection mJuiceTypeface;
    /** Multiple custom typefaces support */
    private TypefaceCollection mArchRivalTypeface;
    /** Multiple custom typefaces support */
    private TypefaceCollection mActionManTypeface;
    /** Multiple custom typefaces support */
    private TypefaceCollection mSystemDefaultTypeface;
    /** Multiple custom typefaces support */
    private TypefaceCollection mUbuntuTypeface;

    private TypefaceCollection zoodHaritTypeface;

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

        NativeLoader.initNativeLibs(MainApplication.getInstance());

        prefManager = new PrefManager(getSharedPreferences("App", MODE_PRIVATE));
        // Load helper with default custom typeface (single custom typeface)
        TypefaceHelper.init(new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-R.ttf"))
                .set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-B.ttf"))
                .set(Typeface.ITALIC, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-RI.ttf"))
                .set(Typeface.BOLD_ITALIC, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-BI.ttf"))
                .create());

        // Multiple custom typefaces support
        mJuiceTypeface = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/Juice/JUICE_Regular.ttf"))
                .set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/Juice/JUICE_Bold.ttf"))
                .set(Typeface.ITALIC, Typeface.createFromAsset(getAssets(), "fonts/Juice/JUICE_Italic.ttf"))
                .set(Typeface.BOLD_ITALIC, Typeface.createFromAsset(getAssets(), "fonts/Juice/JUICE_Bold_Italic.ttf"))
                .create();

        // Multiple custom typefaces support
        mArchRivalTypeface = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/arch_rival/SF_Arch_Rival.ttf"))
                .set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/arch_rival/SF_Arch_Rival_Bold.ttf"))
                .set(Typeface.ITALIC, Typeface.createFromAsset(getAssets(), "fonts/arch_rival/SF_Arch_Rival_Italic.ttf"))
                .set(Typeface.BOLD_ITALIC, Typeface.createFromAsset(getAssets(), "fonts/arch_rival/SF_Arch_Rival_Bold_Italic.ttf"))
                .create();

        // Multiple custom typefaces support
        mActionManTypeface = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/Action-Man/Action_Man.ttf"))
                .set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/Action-Man/Action_Man_Bold.ttf"))
                .set(Typeface.ITALIC, Typeface.createFromAsset(getAssets(), "fonts/Action-Man/Action_Man_Italic.ttf"))
                .set(Typeface.BOLD_ITALIC, Typeface.createFromAsset(getAssets(), "fonts/Action-Man/Action_Man_Bold_Italic.ttf"))
                .create();

        // Multiple custom typefaces support
        mUbuntuTypeface = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-R.ttf"))
                .set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-B.ttf"))
                .set(Typeface.ITALIC, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-RI.ttf"))
                .set(Typeface.BOLD_ITALIC, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-BI.ttf"))
                .create();

        zoodHaritTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/ZoodHarit-thai/ZoodHarit-thai.ttf"))
                .create());

        // Multiple custom typefaces support
        mSystemDefaultTypeface = TypefaceCollection.createSystemDefault();


        Events.init(this);
        Events.register(FlickrApi.class);

        GestureDebug.setDebugFps(true);
        GestureDebug.setDebugAnimator(true);

        mPref = new PrefManager(getSharedPreferences("App", MODE_PRIVATE));

        someApiHandler = new ApiHandler(this, buildApi(), ApiBus.getInstance());
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
    public static PrefManager getPrefManager() {
        return prefManager;
    }



    public static MainApplication getInstance()
    {
        return Instance;
    }

    /** Multiple custom typefaces support */
    public TypefaceCollection getJuiceTypeface() {
        return mJuiceTypeface;
    }

    /** Multiple custom typefaces support */
    public TypefaceCollection getArchRivalTypeface() {
        return mArchRivalTypeface;
    }

    /** Multiple custom typefaces support */
    public TypefaceCollection getActionManTypeface() {
        return mActionManTypeface;
    }

    /** Multiple custom typefaces support */
    public TypefaceCollection getSystemDefaultTypeface() {
        return mSystemDefaultTypeface;
    }

    /** Multiple custom typefaces support */
    public TypefaceCollection getUbuntuTypeface() {
        return mUbuntuTypeface;
    }

    public TypefaceCollection getZoodHaritTypeface() {
        return zoodHaritTypeface;
    }
}
