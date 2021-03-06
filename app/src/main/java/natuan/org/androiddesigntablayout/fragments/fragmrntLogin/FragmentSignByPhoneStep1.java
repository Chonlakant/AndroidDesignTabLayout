package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.weiwangcn.betterspinner.library.BetterSpinner;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;

public class FragmentSignByPhoneStep1 extends Fragment {

    Toolbar toolbar;
    // @InjectView(R.id.input_email)
    BetterSpinner spinner1;
    // @InjectView(R.id.input_number_phone)
    EditText dtInputPhone;
    // @InjectView(R.id.btn_next)
    Button btnNext;
    String code;
    // @InjectView(R.id.txt_skip)
    String[] codeContry = {"All Code","(+60) Malaysia", "(+61) Australia", "(+62) Indonesia", "(+63) Philippines", "(+64) New Zealand", "(+65) Singapore", "(+66) Thailand"};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up_by_phone_number_step_1, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SWZ721BR.ttf");
        dtInputPhone = (EditText) rootView.findViewById(R.id.input_number_phone);
        btnNext = (Button) rootView.findViewById(R.id.btn_next);
        btnNext.setTypeface(type);
        dtInputPhone.setTypeface(type);

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



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editNumberPhone = dtInputPhone.getText().toString();

                String numberPhone = code + editNumberPhone;
                Log.e("Selected item : ", numberPhone);
                Bundle i = new Bundle();
                FragmentSignByPhoneStep2 oneFragment = new FragmentSignByPhoneStep2();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, oneFragment);
                i.putString("numberPhone", numberPhone);
                oneFragment.setArguments(i);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


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
        toolbar.setTitle("Sign up");
        // toolbar.inflateMenu(R.menu.menu_main_recent_chat);
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_add:
//                Toast.makeText(getActivity(), "Add", Toast.LENGTH_SHORT).show();
//                FragmentAddFriends fragment = new FragmentAddFriends();
//                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.add(R.id.flContainer, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//                return true;


        }
        return super.onOptionsItemSelected(item);
    }


}