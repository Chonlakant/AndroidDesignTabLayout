package bentaang.chonlakant.com.drawer.constant;

import android.content.Context;

import com.norbsoft.typefacehelper.TypefaceCollection;

import java.util.HashMap;

import bentaang.chonlakant.com.drawer.MainApplication;
import bentaang.chonlakant.com.drawer.constant.Constant;

/**
 * Created by root1 on 11/17/15.
 */
public class HashMapTypeface {
    Context context;

    public HashMap<String, TypefaceCollection> getTypeFace(Context context) {
        this.context = context;
        MainApplication myApp = (MainApplication) context;
        HashMap<String, TypefaceCollection> mTypefaceMap = new HashMap<String, TypefaceCollection>();

        mTypefaceMap.put(Constant.TYPEFACE_Cartoon, myApp.getEnCartoonTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Comica, myApp.getEnComicaTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_DaVia, myApp.getEnDaViaTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Facile, myApp.getEnFacileTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Goudosb, myApp.getEnGoudosbTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Koren, myApp.getEnKorenTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Mari, myApp.getEnMariTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Noodle, myApp.getEnNoodleTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_OneMore, myApp.getEnOneMoreTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_SanFrancisco, myApp.getEnSanFranciscoTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_SfSppend, myApp.getEnSfSppendTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_TheMillion, myApp.getEnTheMillionTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Vaground, myApp.getEnVagroundTypeface());
        mTypefaceMap.put(Constant.TYPEFACE_Weiss, myApp.getEnWeissTypeface());



        return mTypefaceMap;
    }
}
