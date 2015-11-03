package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import natuan.org.androiddesigntablayout.R;

/**
 * Created by root1 on 11/2/15.
 */
public class FragmentSetting extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
