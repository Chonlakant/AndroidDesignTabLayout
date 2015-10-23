package natuan.org.androiddesigntablayout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.MainFragmentFriends;


public class MainActivityAddFriends extends BaseActivity {

    Toolbar mToolbar;
    TextView txtAddPhone, txtInvite, txtQr, txtAddUsername;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main_add_friends;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtAddPhone = (TextView) findViewById(R.id.txtAddPhone);
        txtInvite = (TextView) findViewById(R.id.txtInvite);
        txtQr = (TextView) findViewById(R.id.txtQr);
        txtAddUsername = (TextView) findViewById(R.id.txtAddUsername);


        MainFragmentFriends fragment = new MainFragmentFriends();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();

        Invite();
        AddPhone();
        AddUsername();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_friends, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void Invite() {
        txtInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivityCotainer.class);
                i.putExtra("type", 5);
                startActivity(i);
            }
        });
    }

    public void AddPhone() {
        txtAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivityCotainer.class);
                i.putExtra("type", 3);
                startActivity(i);
            }
        });
    }

    public void AddUsername() {
        txtAddUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivityCotainer.class);
                i.putExtra("type", 4);
                startActivity(i);
            }
        });
    }
}
