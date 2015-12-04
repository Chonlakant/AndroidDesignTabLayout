package bentaang.chonlakant.com.drawer;

import android.content.SharedPreferences;

import com.tale.prettysharedpreferences.BooleanEditor;
import com.tale.prettysharedpreferences.IntegerEditor;
import com.tale.prettysharedpreferences.PrettySharedPreferences;
import com.tale.prettysharedpreferences.StringEditor;

public class PrefManager extends PrettySharedPreferences<PrefManager> {

    public PrefManager(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }


    public BooleanEditor<PrefManager> isCheck() {
        return getBooleanEditor("isCheck");
    }

    public StringEditor<PrefManager> font() {
        return getStringEditor("font");
    }

    public IntegerEditor<PrefManager> intColor() {
        return getIntegerEditor("intColor");
    }

    public IntegerEditor<PrefManager> numTh() {
        return getIntegerEditor("numTh");
    }

    public IntegerEditor<PrefManager> numEn() {
        return getIntegerEditor("numEn");
    }
}

