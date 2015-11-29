package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.weiwangcn.betterspinner.library.BetterSpinner;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;

public class FragmentByPhone extends Fragment {
    Toolbar toolbar;
    EditText input_username,input_phone;
    String[] codeContry = {"All Code","(+60) Malaysia", "(+61) Australia", "(+62) Indonesia", "(+63) Philippines", "(+64) New Zealand", "(+65) Singapore", "(+66) Thailand"};
    BetterSpinner spinner1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends_by_phone, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SWZ721BR.ttf");
        input_username = (EditText) rootView.findViewById(R.id.input_username);
        input_phone = (EditText) rootView.findViewById(R.id.input_phone);
        spinner1  = (BetterSpinner) rootView.findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, codeContry);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner1.setTypeface(type);

        input_phone.setTypeface(type);

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
        toolbar.setTitle("Add a friend by phone no.");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

}