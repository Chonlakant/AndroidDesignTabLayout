package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.R;

public class FragmentSignByEmail extends Fragment {


    @InjectView(R.id.txt_show_phone_number)
    TextView txtShowPhone;
    @InjectView(R.id.input_username)
    EditText dtUserName;
    @InjectView(R.id.input_lastname)
    EditText dtLastName;
    @InjectView(R.id.btn_register_now)
    Button btnRegisterNow;
    @InjectView(R.id.txt_skip)
    TextView txtSkip;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_email_final, container, false);

        txtShowPhone = (TextView) rootView.findViewById(R.id.txt_sign_email);
        dtUserName = (EditText) rootView.findViewById(R.id.input_username);
        dtLastName = (EditText) rootView.findViewById(R.id.input_lastname);
        btnRegisterNow = (Button) rootView.findViewById(R.id.btn_register_now);
        txtSkip = (TextView) rootView.findViewById(R.id.txt_skip);

        Register();

        return rootView;
    }

    public void Register(){
        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUserNameSetting oneFragment = new FragmentUserNameSetting();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, oneFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
    }

}