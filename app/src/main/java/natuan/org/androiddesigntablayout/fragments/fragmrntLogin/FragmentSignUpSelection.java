package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.activity.MainActivityTap;

public class FragmentSignUpSelection extends Fragment {

    Toolbar toolbar;

    //@InjectView(R.id.txt_sign_phone)
    TextView txtSignPhone;

    //@InjectView(R.id.txt_sign_email)
    TextView txtSignUpEmail;

    // @InjectView(R.id.txt_sign_up_facebook)
    TextView txtSignUpFacebook;

    // @InjectView(R.id.txt_login_here)
    TextView txtSignUpHere;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up_selection, container, false);
        txtSignPhone = (TextView) rootView.findViewById(R.id.txt_sign_phone);
        txtSignUpEmail = (TextView) rootView.findViewById(R.id.txt_sign_email);
        txtSignUpFacebook = (TextView) rootView.findViewById(R.id.txt_sign_up_facebook);
        txtSignUpHere = (TextView) rootView.findViewById(R.id.txt_login_here);

        txtSignPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentSignByPhoneStep1 oneFragment = new FragmentSignByPhoneStep1();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, oneFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        txtSignUpEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), MainActivityLoginStepPhone.class);
//                startActivity(i);

                FragmentSignByEmailStep1 oneFragment = new FragmentSignByEmailStep1();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, oneFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        txtSignUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentLogin fragment = new FragmentLogin();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, fragment);
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