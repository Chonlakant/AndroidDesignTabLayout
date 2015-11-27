package natuan.org.androiddesigntablayout.acivityLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.event.SomeEvent;
import natuan.org.androiddesigntablayout.fragments.fragmrntLogin.FragmentLogin;
import natuan.org.androiddesigntablayout.fragments.fragmrntLogin.FragmentSignUpSelection;
import natuan.org.androiddesigntablayout.handler.ApiBus;


public class ActivityMainLogin extends BaseActivity {

    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        int type = getIntent().getIntExtra("type", 0);
        if(type == 0){
//            FragmentLogin fragment = new FragmentLogin();
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.add(R.id.content, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();

            FragmentLogin threeFragment = new FragmentLogin();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, threeFragment);
            //transaction.addToBackStack(null);
            transaction.commit();
        }else if(type == 1){

//            FragmentSignUpSelection fragment = new FragmentSignUpSelection();
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.add(R.id.content, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();


            FragmentSignUpSelection threeFragment = new FragmentSignUpSelection();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, threeFragment);
            //transaction.addToBackStack(null);
            transaction.commit();
        }


    }



    @Override
    public void onResume() {
        super.onResume();
        ApiBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        ApiBus.getInstance().unregister(this);
    }


//
//    @Subscribe
//    public void onSomeSuccess(SuccessEvent event) {
//        Log.e("66666", event.getSomeResponse().getPosts().get(0).getName());
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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
