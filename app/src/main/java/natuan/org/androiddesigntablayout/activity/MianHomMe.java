package natuan.org.androiddesigntablayout.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.fragments.fragmentFeed.FragmentWall;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentInfomation_home_me_data;

public class MianHomMe extends AppCompatActivity {
    Button btn_wall, media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info_home);


        btn_wall = (Button) findViewById(R.id.btn_wall);
        media = (Button) findViewById(R.id.media);


        FragmentWall threeFragment = new FragmentWall();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contan_home, threeFragment);
       // transaction.addToBackStack(null);
        transaction.commit();

        btn_wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentWall fragment = new FragmentWall();


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

                Toast.makeText(getApplicationContext(),"4456",Toast.LENGTH_SHORT).show();

                FragmentInfomation_home_me_data threeFragment = new FragmentInfomation_home_me_data();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contan_home, threeFragment);
               // transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

}
