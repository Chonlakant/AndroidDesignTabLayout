package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import natuan.org.androiddesigntablayout.activity.MainActivityTap;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.acivityLogin.MainActivityDisplayFragmentLogin;

public class FragmentLogin extends Fragment {
    Boolean isLogin = false;


   // @InjectView(R.id.txt_sign_up_now)
    TextView txtSignNow;
    //@InjectView(R.id.txt_sign_up_facebook)
    TextView txtSignUpFacebook;
    //@InjectView(R.id.btn_login)
    Button btnLogin;
    TextView txt_sign_up_now;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        txtSignNow = (TextView) rootView.findViewById(R.id.txt_sign_up_now);
        txtSignUpFacebook = (TextView) rootView.findViewById(R.id.txt_sign_up_facebook);
        txt_sign_up_now = (TextView) rootView.findViewById(R.id.txt_sign_up_now);
        btnLogin = (Button) rootView.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivityTap.class);
                startActivity(i);
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


}