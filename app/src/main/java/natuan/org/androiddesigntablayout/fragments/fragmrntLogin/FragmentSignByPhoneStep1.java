package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

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
import android.widget.TextView;
import android.widget.Toast;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;

public class FragmentSignByPhoneStep1 extends Fragment {

    Toolbar toolbar;
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
                transaction.replace(R.id.content, oneFragment);
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