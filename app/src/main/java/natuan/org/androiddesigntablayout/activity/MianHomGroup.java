package natuan.org.androiddesigntablayout.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.fragments.fragmentFeed.FragmentWall;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentInfomationMember;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentInfomation_group_data;

public class MianHomGroup extends AppCompatActivity {
    Button wall, media, member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info_group);

        wall = (Button) findViewById(R.id.wall);
        media = (Button) findViewById(R.id.media);
        member = (Button) findViewById(R.id.member);

        FragmentWall threeFragment = new FragmentWall();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contan_home, threeFragment);
        // transaction.addToBackStack(null);
        transaction.commit();
        wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentWall threeFragment = new FragmentWall();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contan_home, threeFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentInfomation_group_data threeFragment = new FragmentInfomation_group_data();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contan_home, threeFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentInfomationMember threeFragment = new FragmentInfomationMember();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contan_home, threeFragment);
               // transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

}
