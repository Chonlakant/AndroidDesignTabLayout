package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import natuan.org.androiddesigntablayout.R;

public class FragmentSignUpSelection extends Fragment {


    //@InjectView(R.id.txt_sign_phone)
    TextView txtSignPhone;

    //@InjectView(R.id.txt_sign_email)
    TextView txtSignUpEmail;

    // @InjectView(R.id.txt_sign_up_facebook)
    TextView txtSignUpFacebook;

    // @InjectView(R.id.txt_login_here)
    TextView txtSignUpHere;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

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
                transaction.replace(R.id.fragment_container, oneFragment);
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
                transaction.replace(R.id.fragment_container, oneFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        txtSignUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentLogin fragment = new FragmentLogin();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return rootView;
    }

}