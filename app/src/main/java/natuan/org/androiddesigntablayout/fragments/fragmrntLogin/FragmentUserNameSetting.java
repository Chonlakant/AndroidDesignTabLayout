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

public class FragmentUserNameSetting extends Fragment {


    //@InjectView(R.id.input_username)
    EditText dtUserName;
    //@InjectView(R.id.img_avatar)
    ImageView imgAvatar;
    //@InjectView(R.id.img_allow_add)
    CheckBox imgAllowAdd;
    //@InjectView(R.id.img_auto_add_friend)
    CheckBox imgAutoAddFriend;
   // @InjectView(R.id.btn_done)
    Button btnDone;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.username_setting, container, false);


        dtUserName = (EditText) rootView.findViewById(R.id.input_username);
        imgAvatar = (ImageView) rootView.findViewById(R.id.img_avatar);
        imgAllowAdd = (CheckBox) rootView.findViewById(R.id.img_allow_add);
        imgAutoAddFriend = (CheckBox) rootView.findViewById(R.id.img_auto_add_friend);
        btnDone = (Button) rootView.findViewById(R.id.btn_done);

        Done();
        return rootView;
    }


    public void Done() {
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentRegisterionCompleted oneFragment = new FragmentRegisterionCompleted();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, oneFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}