package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import natuan.org.androiddesigntablayout.R;

public class FragmentAddFriends extends Fragment {

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
                FragmentByUsername fragment = new FragmentByUsername();
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

}