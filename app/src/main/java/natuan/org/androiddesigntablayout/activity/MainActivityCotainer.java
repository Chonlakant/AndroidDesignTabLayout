package natuan.org.androiddesigntablayout.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.fragments.FragmentInfomation;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentByPhone;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentByUsername;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentCreateGroup;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentInvite;


public class MainActivityCotainer extends BaseActivity {

    Toolbar mToolbar;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main_view_lable_ui;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {


        int type = getIntent().getIntExtra("type",0);

        if(type == 2){
            Toast.makeText(getApplication(), type + "", Toast.LENGTH_SHORT).show();
            FragmentCreateGroup fragment = new FragmentCreateGroup();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
        }if(type == 3){
            FragmentByPhone fragment = new FragmentByPhone();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
        }if(type == 4){
            FragmentByUsername fragment = new FragmentByUsername();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
        }if(type == 5){
            FragmentInvite fragment = new FragmentInvite();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
        }if(type == 6){
            FragmentInfomation fragment = new FragmentInfomation();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_phone, menu);
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
}
