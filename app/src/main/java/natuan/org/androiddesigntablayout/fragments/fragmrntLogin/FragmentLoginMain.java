package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.R;

public class FragmentLoginMain extends AppCompatActivity {


    @InjectView(R.id.btn_login)
    Button btnLogin;

    @InjectView(R.id.btn_sign_in)
    Button btnSignIn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_view_login, container, false);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentLogin fragment = new FragmentLogin();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });

        return rootView;
    }
}