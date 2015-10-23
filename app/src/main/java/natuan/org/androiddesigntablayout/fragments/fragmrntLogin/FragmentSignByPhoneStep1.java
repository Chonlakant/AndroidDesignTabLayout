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
import android.widget.Toast;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.R;

public class FragmentSignByPhoneStep1 extends Fragment {


   // @InjectView(R.id.input_email)
    EditText dtInputPhoneCode;
   // @InjectView(R.id.input_number_phone)
    EditText dtInputPhone;
   // @InjectView(R.id.btn_next)
    Button btnNext;
   // @InjectView(R.id.txt_skip)
    TextView txtSkip;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up_by_phone_number_step_1, container, false);

        dtInputPhoneCode = (EditText) rootView.findViewById(R.id.input_phone_code);
        dtInputPhone = (EditText) rootView.findViewById(R.id.input_number_phone);
        btnNext = (Button) rootView.findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentSignByPhoneStep2 oneFragment = new FragmentSignByPhoneStep2();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, oneFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


        return rootView;
    }

}