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
    /**
     * Multiple custom typefaces support
     */
    private TypefaceCollection mArchRivalTypeface;
    /**
     * Multiple custom typefaces support
     */
    private TypefaceCollection mActionManTypeface;
    /**
     * Multiple custom typefaces support
     */
    private TypefaceCollection mSystemDefaultTypeface;
    /**
     * Multiple custom typefaces support
     */
    private TypefaceCollection mUbuntuTypeface;

    private TypefaceCollection zoodHaritTypeface;

    private TypefaceCollection sUperMarketTypeface;

    private TypefaceCollection thSarabunNewTypeface;


    private TypefaceCollection enCartoonTypeface;
    private TypefaceCollection enComicaTypeface;
    private TypefaceCollection enDaViaTypeface;
    private TypefaceCollection enFacileTypeface;
    private TypefaceCollection enGoudosbTypeface;
    private TypefaceCollection enKorenTypeface;
    private TypefaceCollection enMariTypeface;
    private TypefaceCollection enNoodleTypeface;
    private TypefaceCollection enOneMoreTypeface;
    private TypefaceCollection enSanFranciscoTypeface;
    private TypefaceCollection enSfSppendTypeface;
    private TypefaceCollection enTheMillionTypeface;
    private TypefaceCollection enVagroundTypeface;
    private TypefaceCollection enWeissTypeface;

    //TH
    private TypefaceCollection thBangnaTypeface;
    private TypefaceCollection thCookiesTypeface;
    private TypefaceCollection thDominoTypeface;
    private TypefaceCollection thDrjoyfukTypeface;
    private TypefaceCollection thPaaymaayTypeface;
    private TypefaceCollection thParggarTypeface;
    private TypefaceCollection thPlediteTypeface;
    private TypefaceCollection thPrachachonTypeface;
    private TypefaceCollection thRtemehuaTypeface;
    private TypefaceCollection thWrTishTypeface;


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


        enCartoonTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/Cartoon.ttf"))
                .create());
        enComicaTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/Comica.ttf"))
                .create());
        enDaViaTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/david.ttf"))
                .create());
        enFacileTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/Facile_Sans.ttf"))
                .create());
        enGoudosbTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/GOUDOSB.ttf"))
                .create());
        enKorenTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/Korean.ttf"))
                .create());
        enMariTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/MARI_DAVID.ttf"))
                .create());
        enNoodleTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/noodle.ttf"))
                .create());
        enOneMoreTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/OneMoreNight.ttf"))
                .create());
        enSanFranciscoTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/San_Francisco.ttf"))
                .create());
        enSfSppendTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/SF_Speedwaystar_Shaded.ttf"))
                .create());
        enTheMillionTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/The_Million_Mile_Man.ttf"))
                .create());
        enVagroundTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/VAGRounded_Bold.ttf"))
                .create());
        enWeissTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/Weissxb.ttf"))
                .create());


        //TH
        thBangnaTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/th/bangna_new.ttf"))
                .create());
        thCookiesTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/cookies_lite.ttf"))
                .create());
        thDominoTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/DOMINO.ttf"))
                .create());
        thDrjoyfukTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/DRjoyful.ttf"))
                .create());
        thPaaymaayTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/paaymaay_regular.ttf"))
                .create());
        thParggarTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/Parggar_font.ttf"))
                .create());
        thPlediteTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/PL_EDIT1_02.ttf"))
                .create());
        thPrachachonTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/Prachachon.ttf"))
                .create());
        thRtemehuaTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/rtemehua.ttf"))
                .create());
        thWrTishTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/eng/WR_Tish_Kid.ttf"))
                .create());


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
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/Memory/memory.ttf"))
                .create());

        sUperMarketTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/supermarket/supermarket.ttf"))
                .create());

        thSarabunNewTypeface = (new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/THSarabunNew/THSarabunNew.ttf"))
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


    public static MainApplication getInstance() {
        return Instance;
    }

    /**
     * Multiple custom typefaces support
     */
    public TypefaceCollection getJuiceTypeface() {
        return mJuiceTypeface;
    }

    /**
     * Multiple custom typefaces support
     */
    public TypefaceCollection getArchRivalTypeface() {
        return mArchRivalTypeface;
    }

    /**
     * Multiple custom typefaces support
     */
    public TypefaceCollection getActionManTypeface() {
        return mActionManTypeface;
    }

    /**
     * Multiple custom typefaces support
     */
    public TypefaceCollection getSystemDefaultTypeface() {
        return mSystemDefaultTypeface;
    }

    /**
     * Multiple custom typefaces support
     */
    public TypefaceCollection getUbuntuTypeface() {
        return mUbuntuTypeface;
    }

    public TypefaceCollection getZoodHaritTypeface() {
        return zoodHaritTypeface;
    }

    public TypefaceCollection getsUperMarketTypeface() {
        return sUperMarketTypeface;
    }

    public TypefaceCollection getThSarabunNewTypeface() {
        return thSarabunNewTypeface;
    }

    public TypefaceCollection getmJuiceTypeface() {
        return mJuiceTypeface;
    }

    public TypefaceCollection getmArchRivalTypeface() {
        return mArchRivalTypeface;
    }

    public TypefaceCollection getmActionManTypeface() {
        return mActionManTypeface;
    }

    public TypefaceCollection getmSystemDefaultTypeface() {
        return mSystemDefaultTypeface;
    }

    public TypefaceCollection getmUbuntuTypeface() {
        return mUbuntuTypeface;
    }

    public TypefaceCollection getEnCartoonTypeface() {
        return enCartoonTypeface;
    }

    public TypefaceCollection getEnComicaTypeface() {
        return enComicaTypeface;
    }

    public TypefaceCollection getEnDaViaTypeface() {
        return enDaViaTypeface;
    }

    public TypefaceCollection getEnFacileTypeface() {
        return enFacileTypeface;
    }

    public TypefaceCollection getEnGoudosbTypeface() {
        return enGoudosbTypeface;
    }

    public TypefaceCollection getEnKorenTypeface() {
        return enKorenTypeface;
    }

    public TypefaceCollection getEnMariTypeface() {
        return enMariTypeface;
    }

    public TypefaceCollection getEnNoodleTypeface() {
        return enNoodleTypeface;
    }

    public TypefaceCollection getEnOneMoreTypeface() {
        return enOneMoreTypeface;
    }

    public TypefaceCollection getEnSanFranciscoTypeface() {
        return enSanFranciscoTypeface;
    }

    public TypefaceCollection getEnSfSppendTypeface() {
        return enSfSppendTypeface;
    }

    public TypefaceCollection getEnTheMillionTypeface() {
        return enTheMillionTypeface;
    }

    public TypefaceCollection getEnVagroundTypeface() {
        return enVagroundTypeface;
    }

    public TypefaceCollection getEnWeissTypeface() {
        return enWeissTypeface;
    }

    public TypefaceCollection getThBangnaTypeface() {
        return thBangnaTypeface;
    }

    public TypefaceCollection getThCookiesTypeface() {
        return thCookiesTypeface;
    }

    public TypefaceCollection getThDominoTypeface() {
        return thDominoTypeface;
    }

    public TypefaceCollection getThDrjoyfukTypeface() {
        return thDrjoyfukTypeface;
    }

    public TypefaceCollection getThPaaymaayTypeface() {
        return thPaaymaayTypeface;
    }

    public TypefaceCollection getThParggarTypeface() {
        return thParggarTypeface;
    }

    public TypefaceCollection getThPlediteTypeface() {
        return thPlediteTypeface;
    }

    public TypefaceCollection getThPrachachonTypeface() {
        return thPrachachonTypeface;
    }

    public TypefaceCollection getThRtemehuaTypeface() {
        return thRtemehuaTypeface;
    }

    public TypefaceCollection getThWrTishTypeface() {
        return thWrTishTypeface;
    }

    public static String getENDPOINT() {
        return ENDPOINT;
    }

    public static Handler getApplicationHandler() {
        return applicationHandler;
    }

    public ApiHandler getSomeApiHandler() {
        return someApiHandler;
    }

    public static PrefManager getmPref() {
        return mPref;
    }
}
