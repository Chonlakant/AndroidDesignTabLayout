package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;

public class FragmentUserNameSetting extends Fragment {
    Toolbar toolbar;

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
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SWZ721BR.ttf");

        dtUserName = (EditText) rootView.findViewById(R.id.input_username);
        imgAvatar = (ImageView) rootView.findViewById(R.id.img_avatar);
        imgAllowAdd = (CheckBox) rootView.findViewById(R.id.img_allow_add);
        imgAutoAddFriend = (CheckBox) rootView.findViewById(R.id.img_auto_add_friend);

        btnDone = (Button) rootView.findViewById(R.id.btn_done);
        btnDone.setTypeface(type);
        dtUserName.setTypeface(type);

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        toolbar.setTitle("Register");
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