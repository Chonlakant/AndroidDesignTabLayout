package natuan.org.androiddesigntablayout.fragments.fragmentTap;

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
import android.widget.TextView;
import android.widget.Toast;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;

public class FragmentAddFriends extends Fragment {
    Toolbar toolbar;

    TextView txtAddPhone, txtInvite, txtQr, txtAddUsername;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main_add_friends, container, false);
        txtAddPhone = (TextView) rootView.findViewById(R.id.txtAddPhone);
        txtInvite = (TextView) rootView.findViewById(R.id.txtInvite);
        txtQr = (TextView) rootView.findViewById(R.id.txtQr);
        txtAddUsername = (TextView) rootView.findViewById(R.id.txtAddUsername);
        Invite();
        AddPhone();
        AddUsername();
        return rootView;
    }

    public void Invite() {
        txtInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentInvite fragment = new FragmentInvite();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.flContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    public void AddPhone() {
        txtAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentByPhone fragment = new FragmentByPhone();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.flContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    public void AddUsername() {
        txtAddUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentByUsername fragment = new FragmentByUsername();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.flContainer, fragment);
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

        toolbar.inflateMenu(R.menu.menu_add_friends);
        toolbar.setTitle("Add Friends");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_cancle:
//                Toast.makeText(getActivity(), "Add", Toast.LENGTH_SHORT).show();
//                FragmentAddFriends fragment = new FragmentAddFriends();
//                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.add(R.id.flContainer, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//                return true;
//
//
//        }
//        return super.onOptionsItemSelected(item);
//    }

}