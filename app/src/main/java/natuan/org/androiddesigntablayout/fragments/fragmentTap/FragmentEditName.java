package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import natuan.org.androiddesigntablayout.R;

public class FragmentEditName extends Fragment {

    TextView txt_qr;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_name, container, false);

        txt_qr = (TextView) rootView.findViewById(R.id.txt_qr);
        txt_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentMoreQqCode fragment = new FragmentMoreQqCode();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.flContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
    }

}