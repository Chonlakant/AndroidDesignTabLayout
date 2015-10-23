package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import natuan.org.androiddesigntablayout.activity.MainActivityTap;
import natuan.org.androiddesigntablayout.R;

public class FragmentRegisterionCompleted extends Fragment {

    //@InjectView(R.id.btn_start)
    Button btnStart;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registerion_completed, container, false);
        btnStart = (Button) rootView.findViewById(R.id.btn_start);
        Start();
        return rootView;
    }


    public void Start() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivityTap.class);
                startActivity(i);
            }
        });
    }

}