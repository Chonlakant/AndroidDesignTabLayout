package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import natuan.org.androiddesigntablayout.R;

public class FragmentCamera extends Fragment {


    public static FragmentCamera getInstance(String message) {
        FragmentCamera mainFragment = new FragmentCamera();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);

        return rootView;
    }

}