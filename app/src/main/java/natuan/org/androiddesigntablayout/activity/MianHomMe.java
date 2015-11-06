package natuan.org.androiddesigntablayout.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.fragments.fragmentFeed.FragmentWall;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentInfomation_home_me_data;

public class MianHomMe extends BaseActivity {
    Button btn_wall, media;
    String[] DayOfWeek = {"Only me", "Singel"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info_home);


        btn_wall = (Button) findViewById(R.id.btn_wall);
        media = (Button) findViewById(R.id.media);

        Spinner mySpinner = (Spinner) findViewById(R.id.button3);
        mySpinner.setAdapter(new MyCustomAdapter(getApplicationContext(), R.layout.row, DayOfWeek));

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

                FragmentInfomation_home_me_data threeFragment = new FragmentInfomation_home_me_data();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contan_home, threeFragment);
                // transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    public class MyCustomAdapter extends ArrayAdapter<String> {

        public MyCustomAdapter(Context context, int textViewResourceId,
                               String[] objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            //return super.getView(position, convertView, parent);

            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.row, parent, false);
            TextView label = (TextView) row.findViewById(R.id.weekofday);
            label.setText(DayOfWeek[position]);


            return row;
        }
    }

}
