package bentaang.chonlakant.com.drawer.constant;

import android.content.Context;

import com.norbsoft.typefacehelper.TypefaceCollection;

import java.util.HashMap;

import bentaang.chonlakant.com.drawer.MainApplication;

/**
 * Created by root1 on 11/17/15.
 */
public class HashMapTypefaceTh {
    Context context;

    public HashMap<String, TypefaceCollection> getTypeFace(Context context) {
        this.context = context;
        MainApplication myApp = (MainApplication) context;
        HashMap<String, TypefaceCollection> mTypefaceMap = new HashMap<String, TypefaceCollection>();

        mTypefaceMap.put(Constant.TYPEFACE_Bangna, myApp.getThBangnaTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Cookies, myApp.getThCookiesTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Domino, myApp.getThDominoTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_DEFAULT, myApp.getsUperMarketTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Drjoyfuk, myApp.getThDrjoyfukTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Paaymaay, myApp.getThPaaymaayTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Parggar, myApp.getThParggarTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Pledite, myApp.getThPlediteTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Prachachon, myApp.getThPrachachonTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Rtemehua, myApp.getThRtemehuaTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_WrTish, myApp.getThWrTishTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_SUPERMARKET, myApp.getsUperMarketTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_THSARABUN, myApp.getThSarabunNewTypeface());


        return mTypefaceMap;
    }
}
