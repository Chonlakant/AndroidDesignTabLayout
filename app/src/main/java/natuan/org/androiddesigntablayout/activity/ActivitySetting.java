package natuan.org.androiddesigntablayout.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import natuan.org.androiddesigntablayout.R;

/**
 * Created by root1 on 11/2/15.
 */
public class ActivitySetting extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager
                .beginTransaction();
        PrefsFragment mPrefsFragment = new PrefsFragment();
        mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.commit();

    }

    public static class PrefsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}