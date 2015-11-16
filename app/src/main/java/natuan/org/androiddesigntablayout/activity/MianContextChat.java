package natuan.org.androiddesigntablayout.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import natuan.org.androiddesigntablayout.MainFragment;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.fragments.fragmentFeed.FragmentWall;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentInfomation_home_me_data;

public class MianContextChat extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_context_chat);


        MainFragment threeFragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contan_home, threeFragment);
        // transaction.addToBackStack(null);
        transaction.commit();


    }

}
