package natuan.org.androiddesigntablayout;

import android.content.SharedPreferences;

import com.tale.prettysharedpreferences.BooleanEditor;
import com.tale.prettysharedpreferences.DoubleEditor;
import com.tale.prettysharedpreferences.FloatEditor;
import com.tale.prettysharedpreferences.IntegerEditor;
import com.tale.prettysharedpreferences.LongEditor;
import com.tale.prettysharedpreferences.PrettySharedPreferences;
import com.tale.prettysharedpreferences.StringEditor;

public class PrefManager extends PrettySharedPreferences<PrefManager> {

    public PrefManager(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }


    public BooleanEditor<PrefManager> isCheck() {
        return getBooleanEditor("isCheck");
    }

}

