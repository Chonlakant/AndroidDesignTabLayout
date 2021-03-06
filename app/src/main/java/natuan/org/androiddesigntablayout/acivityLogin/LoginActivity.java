package natuan.org.androiddesigntablayout.acivityLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.google.gson.Gson;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginFragment;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;

import natuan.org.androiddesigntablayout.MainApplication;
import natuan.org.androiddesigntablayout.PrefManager;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.MainActivityTap;
import natuan.org.androiddesigntablayout.event.LoginFailedAuthEvent;
import natuan.org.androiddesigntablayout.event.LoginSuccessEvent;
import natuan.org.androiddesigntablayout.event.UpdateProfileEvent;
import natuan.org.androiddesigntablayout.fragments.fragmrntLogin.FragmentLogin;
import natuan.org.androiddesigntablayout.handler.ActivityResultBus;
import natuan.org.androiddesigntablayout.handler.ApiBus;
import natuan.org.androiddesigntablayout.model.LoginData;
import natuan.org.androiddesigntablayout.model.UserProfile;

public class LoginActivity extends AppCompatActivity {

    public PrefManager prefManager;
    public boolean isLogin;
    boolean doubleBackToExitPressedOnce = false;

    Activity mActivity;

    public ParseUser user;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        prefManager = MainApplication.get(this).getPrefManager();
        setContentView(R.layout.activity_login);

//        CustomActivityOnCrash.setShowErrorDetails(true);
//        CustomActivityOnCrash.setRestartActivityClass(MainActivity.class);
//        CustomActivityOnCrash.install(this);

        callbackManager = CallbackManager.Factory.create();


        mActivity = this;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

            isLogin = prefManager.isLogin().getOr(false);

        if (savedInstanceState == null && !isLogin) {
            getSupportFragmentManager().beginTransaction().add(R.id.login_container, new FragmentLogin()).commit();

//            ParseLoginBuilder loginBuilder = new ParseLoginBuilder(
//                    LoginActivity.this);
//            Intent parseLoginIntent = loginBuilder.setParseLoginEnabled(true)
//                    .setParseLoginButtonText("Login")
//                    .setParseSignupButtonText("Register")
//                    .setParseLoginHelpText("Forgot password?")
//                    .setParseLoginInvalidCredentialsToastText("You email and/or password is not correct")
//                    .setParseLoginEmailAsUsername(true)
//                    .setParseSignupSubmitButtonText("Submit registration")
//                    .setFacebookLoginEnabled(true)
//                    .setFacebookLoginButtonText("Facebook")
//                    .setFacebookLoginPermissions(Arrays.asList("user_status", "read_stream"))
//                    .setTwitterLoginEnabled(false)
//                            //.setTwitterLoginButtontext("Twitter")
//                    .build();
//            startActivityForResult(parseLoginIntent, 12123);
        } else {
            Intent main = new Intent(LoginActivity.this,MainActivityTap.class);
            startActivity(main);
            ActivityResultBus.getInstance().postQueue(new UpdateProfileEvent(new UserProfile()));
            finish();

        }

    }

    public static CallbackManager getFbCallbackManager() {
        return CallbackManager.Factory.create();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getFbCallbackManager().onActivityResult(requestCode, resultCode, data);

        setResult(resultCode);
        Log.e("asdf555", requestCode + " " + resultCode);


        if(resultCode == RESULT_OK) {
            user = ParseUser.getCurrentUser();

            if(user != null) {

                HashMap fbObj = (HashMap) user.get("authData");

                if(fbObj != null) {
                    HashMap facebook = (HashMap) fbObj.get("facebook");
                    String fbAccessToken = facebook.get("access_token").toString();
                    String fbId = facebook.get("id").toString();

                    Log.e("fbAccessToken", fbId + " "  + fbAccessToken);
                    try {
                        fbAuthVM(fbId,fbAccessToken);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //Log.e("normalLogin",user.getEmail() + " "  +   user.getUsername());
                    if(ParseLoginFragment.HACKUSERNAME != null && ParseLoginFragment.HACKPASSWORD != null)
                        authVM(ParseLoginFragment.HACKUSERNAME, ParseLoginFragment.HACKPASSWORD);

                    Toast.makeText(mActivity,"no fb data for this user",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private void authVM(final String username, final String password) {
        final RequestBody formBody = new FormEncodingBuilder()
                .add("username", username)
                .add("password", password)
                .build();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Request request = new Request.Builder()
                        .url("http://api.candychat.net/1.0/auth")
                        .post(formBody)
                        .build();
                Response response = null;
                try {
                    response = MainApplication.getHttpClient().newCall(request).execute();
                    if (!response.isSuccessful()) {
                        ApiBus.getInstance().post(new LoginFailedAuthEvent());
                    } else {

                        Gson gson = new Gson();
                        LoginData loginData = gson.fromJson(response.body().charStream(), LoginData.class);

                        LoginSuccessEvent event = new LoginSuccessEvent(loginData);

//                        ChatApp.USER_TOKEN = event.getLoginData().token;
//                        Log.e("USER_TOKEN", ChatApp.USER_TOKEN);

                        user.put("user_id",Integer.parseInt(event.getLoginData().user.id));
                        user.put("name", event.getLoginData().user.name);
                        user.setUsername(event.getLoginData().user.username);
                        user.setEmail(event.getLoginData().user.email);

                        prefManager
                                .name().put(event.getLoginData().user.name)
                                .username().put(event.getLoginData().user.username)
                                .userId().put(event.getLoginData().user.id)
                                .token().put(event.getLoginData().token)
                                .cover().put(event.getLoginData().user.cover)
                                .avatar().put(event.getLoginData().user.avatar)
                                .isLogin().put(true)
                                .commit();

                        try {
                            user.save();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        //VMApp.updateParseInstallation(Integer.parseInt(event.getLoginData().user.id));

                        Intent main = new Intent(mActivity,MainActivityTap.class);
                        startActivity(main);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });




    }

    private void fbAuthVM(final String fbId, final String fbToken) throws IOException {

        final RequestBody formBody = new FormEncodingBuilder()
                .add("access_token", fbToken)
                .build();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://api.candychat.net/1.0/auth")
                        .post(formBody)
                        .build();

                Response response = null;
                try {
                    response = MainApplication.getHttpClient().newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!response.isSuccessful()) {
                    ApiBus.getInstance().post(new LoginFailedAuthEvent());
                } else {
                    Gson gson = new Gson();
                    LoginData loginData = null;
                    try {
                        loginData = gson.fromJson(response.body().charStream(), LoginData.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    LoginSuccessEvent event = new LoginSuccessEvent(loginData);

//                    ChatApp.USER_TOKEN = event.getLoginData().token;
//                    Log.e("USER_TOKEN", ChatApp.USER_TOKEN);

                    user.put("user_id",Integer.parseInt(event.getLoginData().user.id));
                    user.setUsername(event.getLoginData().user.username);

                    try {
                        user.save();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    prefManager
                            .name().put(event.getLoginData().user.name)
                            .username().put(event.getLoginData().user.username)
                            .userId().put(event.getLoginData().user.id)
                            .token().put(event.getLoginData().token)
                            .cover().put(event.getLoginData().user.cover)
                            .avatar().put(event.getLoginData().user.avatar)
                            .isLogin().put(true)
                            .commit();

                    prefManager
                            .fbToken().put(fbToken)
                            .fbId().put(fbId).commit();

                    Log.e("VM_PROFILE", event.getLoginData().user.toString());

                    //VMApp.updateParseInstallation(Integer.parseInt(event.getLoginData().user.id));

                    Intent main = new Intent(mActivity,MainActivityTap.class);
                    startActivity(main);

                    //Intent main = new Intent(getActivity(),LandingActivity.class);
                    //getActivity().startActivity(main);

                    //UserProfile user = event.getLoginData().user;
                    //ApiBus.getInstance().post(new UpdateProfileEvent(user));

                }
            }
        });


    }

//    @Subscribe
//    public void onLoginSuccess(co.aquario.socialkit.event.LoginSuccessEvent event) {
//        ParseInstallation installation = ParseInstallation
//                .getCurrentInstallation();
//
//        installation.put("user_id", Integer.parseInt(event.getLoginData().user.id));
//        installation.saveInBackground();
//    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish();
            return;
        } else {
            super.onBackPressed();
        }

        this.doubleBackToExitPressedOnce = true;
        //Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

    }

}
