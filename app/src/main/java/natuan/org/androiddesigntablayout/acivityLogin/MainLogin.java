package natuan.org.androiddesigntablayout.acivityLogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.MainApplication;
import natuan.org.androiddesigntablayout.PrefManager;
import natuan.org.androiddesigntablayout.R;


public class MainLogin extends BaseActivity {
    @InjectView(R.id.btn_login)
    Button btnLogin;
    PrefManager pref;
    @InjectView(R.id.btn_sign_in)
    Button btnSignIn;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_view_login;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        LoginAndSignIn();
        getSupportActionBar().hide();
        pref = MainApplication.getPrefManager();
    }



    public void LoginAndSignIn(){


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = 0;

                Toast.makeText(getApplication(), "Login = 0", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivityDisplayFragmentLogin.class);
                i.putExtra("type", type);
                startActivity(i);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = 1;
                Toast.makeText(getApplication(),"Sing up = 1",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),MainActivityDisplayFragmentLogin.class);
                i.putExtra("type",type);
                startActivity(i);
            }
        });


    }


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


//    @Override
//    public void onResume() {
//        super.onResume();
//        if (pref.isLogin().getOr(true)) {
//
//            Intent i = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(i);
//
//        }else{
//            Intent i =new Intent(getApplication(),MainActivityLogin.class);
//            startActivity(i);
//        }
//    }
}
