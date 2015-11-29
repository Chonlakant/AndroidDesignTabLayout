package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;

public class FragmentByUsername extends Fragment {
    Toolbar toolbar;
    EditText input_username;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_by_username, container, false);
        input_username = (EditText) rootView.findViewById(R.id.input_username);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SWZ721BR.ttf");


        input_username.setTypeface(type);

        return rootView;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        toolbar.inflateMenu(R.menu.menu_add_friends);
        toolbar.setTitle("Add a friend by username.");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }
}