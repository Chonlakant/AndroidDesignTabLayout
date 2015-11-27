package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import natuan.org.androiddesigntablayout.acivityLogin.MainLogin;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.activity.MainActivityTap;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.acivityLogin.MainActivityDisplayFragmentLogin;

public class FragmentLogin extends Fragment {
    Boolean isLogin = false;
    Toolbar toolbar;
    // @InjectView(R.id.txt_sign_up_now)
    TextView txtSignNow;
    //@InjectView(R.id.txt_sign_up_facebook)
    TextView txtSignUpFacebook;
    //@InjectView(R.id.btn_login)
    Button txtLogin;
    TextView txt_sign_up_now;

    EditText input_email;
    EditText input_password;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SWZ721BR.ttf");
        txtSignNow = (TextView) rootView.findViewById(R.id.txt_sign_up_now);
        txtSignUpFacebook = (TextView) rootView.findViewById(R.id.txt_sign_up_facebook);
        txt_sign_up_now = (TextView) rootView.findViewById(R.id.txt_sign_up_now);
        txtLogin = (Button) rootView.findViewById(R.id.txt_login);
        input_email = (EditText) rootView.findViewById(R.id.input_email);
        input_password = (EditText) rootView.findViewById(R.id.input_password);

        input_email.setTypeface(type);
        input_password.setTypeface(type);


        txtLogin.setTypeface(type);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivityTap.class);
                startActivity(i);

                Toast.makeText(getActivity(), "Main page", Toast.LENGTH_SHORT).show();
            }
        });

        txt_sign_up_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentSignUpSelection fragment = new FragmentSignUpSelection();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.content, fragment);
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
        toolbar.setTitle("Login");
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