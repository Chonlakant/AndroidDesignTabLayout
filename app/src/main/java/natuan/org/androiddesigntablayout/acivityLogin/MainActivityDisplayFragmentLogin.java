package natuan.org.androiddesigntablayout.acivityLogin;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.fragments.fragmrntLogin.FragmentLogin;
import natuan.org.androiddesigntablayout.fragments.fragmrntLogin.FragmentSignUpSelection;


public class MainActivityDisplayFragmentLogin extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_login);
//        getSupportActionBar().hide();
        int type = getIntent().getIntExtra("type", 0);

        if(type == 0){
            Toast.makeText(getApplication(), type +"", Toast.LENGTH_SHORT).show();
            FragmentLogin fragment = new FragmentLogin();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
        }if(type == 1){
            Toast.makeText(getApplication(), type +"", Toast.LENGTH_SHORT).show();
            FragmentSignUpSelection fragment = new FragmentSignUpSelection();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
