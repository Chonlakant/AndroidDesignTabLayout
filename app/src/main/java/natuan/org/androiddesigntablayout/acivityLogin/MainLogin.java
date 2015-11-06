package natuan.org.androiddesigntablayout.acivityLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.event.SomeEvent;
import natuan.org.androiddesigntablayout.event.SuccessEvent;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentEditName;
import natuan.org.androiddesigntablayout.fragments.fragmrntLogin.FragmentLogin;
import natuan.org.androiddesigntablayout.fragments.fragmrntLogin.FragmentSignUpSelection;
import natuan.org.androiddesigntablayout.handler.ApiBus;


public class MainLogin extends BaseActivity {
    //@InjectView(R.id.btn_login)
    Button btnLogin;

    //@InjectView(R.id.btn_sign_in)
    Button btnSignIn;
    LinearLayout view_toolbar_login;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_view_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        LoginAndSignIn();
//        getSupportActionBar().hide();
        ApiBus.getInstance().post(new SomeEvent());

    }

    public void LoginAndSignIn(){


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int type = 0;
//
                Intent i = new Intent(getApplicationContext(), ActivityMainLogin.class);
                i.putExtra("type", 0);
                startActivity(i);


            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int type = 1;
//                Toast.makeText(getApplication(), "Sing up = 1", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), ActivityMainLogin.class);
                i.putExtra("type", 1);
                startActivity(i);

            }
        });


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
