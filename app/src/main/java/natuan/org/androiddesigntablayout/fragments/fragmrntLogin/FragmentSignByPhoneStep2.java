package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.R;

public class FragmentSignByPhoneStep2 extends Fragment {


    //@InjectView(R.id.input_email)
    EditText dtEmail;
    //@InjectView(R.id.input_password)
    EditText dtPassword;
    //@InjectView(R.id.btn_next)
    Button btnNext;
    //@InjectView(R.id.txt_skip)
    TextView txtSkip;
   // @InjectView(R.id.check_show_password)
    CheckBox imgShowPassword;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up_by_phone_number_step_2, container, false);

        dtEmail = (EditText) rootView.findViewById(R.id.input_email);
        dtPassword = (EditText) rootView.findViewById(R.id.input_password);
        btnNext = (Button) rootView.findViewById(R.id.btn_next);
        txtSkip = (TextView) rootView.findViewById(R.id.txt_skip);
        imgShowPassword = (CheckBox) rootView.findViewById(R.id.check_show_password);

        Next();

        return rootView;
    }

    public void Next(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentSignByPhone oneFragment = new FragmentSignByPhone();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, oneFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}