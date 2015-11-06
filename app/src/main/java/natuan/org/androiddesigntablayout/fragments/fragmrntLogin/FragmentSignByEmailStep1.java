package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;

public class FragmentSignByEmailStep1 extends Fragment {

    Toolbar toolbar;
    //@InjectView(R.id.input_email)
    EditText dtEmail;
    //@InjectView(R.id.input_password)
    EditText dtPassword;
    //@InjectView(R.id.btn_next)
    Button btnNext;
    //@InjectView(R.id.txt_skip)

   // @InjectView(R.id.img_show_password)
    CheckBox imgShowPassword;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up_by_email_step_1, container, false);

        dtEmail = (EditText) rootView.findViewById(R.id.input_email);
        dtPassword = (EditText) rootView.findViewById(R.id.input_password);
        btnNext = (Button) rootView.findViewById(R.id.btn_next);

        imgShowPassword = (CheckBox) rootView.findViewById(R.id.check_show_password);

        imgShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
//                  password_login.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                    // show password
                    dtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    Log.i("checker", "true");

                }else{
                    Log.i("checker", "false");
//                  password_login.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    // hide password
                    dtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                  password_login.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                  password_login.setClickable(true);
                }

            }
        });

        Next();

        return rootView;
    }

    public void Next(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentSignByEmail oneFragment = new FragmentSignByEmail();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, oneFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
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