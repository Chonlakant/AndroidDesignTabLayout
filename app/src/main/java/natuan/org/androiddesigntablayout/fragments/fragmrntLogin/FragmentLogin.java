package natuan.org.androiddesigntablayout.fragments.fragmrntLogin;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.auth.FacebookHandle;
import com.androidquery.callback.AjaxStatus;
import com.facebook.FacebookSdk;
import com.google.gson.Gson;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import me.drakeet.materialdialog.MaterialDialog;
import natuan.org.androiddesigntablayout.MainApplication;
import natuan.org.androiddesigntablayout.PrefManager;
import natuan.org.androiddesigntablayout.acivityLogin.MainLogin;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.activity.MainActivityTap;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.acivityLogin.MainActivityDisplayFragmentLogin;
import natuan.org.androiddesigntablayout.event.FailedNetworkEvent;
import natuan.org.androiddesigntablayout.event.FbAuthEvent;
import natuan.org.androiddesigntablayout.event.GetRecentChatEvent;
import natuan.org.androiddesigntablayout.event.LoadFbProfileEvent;
import natuan.org.androiddesigntablayout.event.LoginEvent;
import natuan.org.androiddesigntablayout.event.LoginFailedAuthEvent;
import natuan.org.androiddesigntablayout.event.LoginSuccessEvent;
import natuan.org.androiddesigntablayout.event.UpdateProfileEvent;
import natuan.org.androiddesigntablayout.fragments.BaseFragment;
import natuan.org.androiddesigntablayout.handler.ApiBus;
import natuan.org.androiddesigntablayout.model.FbProfile;
import natuan.org.androiddesigntablayout.model.UserProfile;

public class FragmentLogin extends BaseFragment {
    Boolean isLogin = false;
    Toolbar toolbar;
    // @InjectView(R.id.txt_sign_up_now)
    TextView txtSignNow;
    //@InjectView(R.id.txt_sign_up_facebook)
    TextView txtSignUpFacebook;
    //@InjectView(R.id.btn_login)
    Button txtLogin;
    TextView txt_sign_up_now;

    EditText input_email;
    EditText input_password;
    public PrefManager prefManager;
    private FbProfile profile;
    private AQuery aq;
    private FacebookHandle handle;
    private String facebookToken;

    public FragmentLogin() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        aq = new AQuery(getActivity());
        prefManager = MainApplication.get(getActivity()).getPrefManager();

        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    " natuan.org.androiddesigntablayout",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

    }

    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        FacebookSdk.sdkInitialize(this.getActivity());
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SWZ721BR.ttf");

        txtSignNow = (TextView) rootView.findViewById(R.id.txt_sign_up_now);
        txtSignUpFacebook = (TextView) rootView.findViewById(R.id.txt_sign_up_facebook);
        txt_sign_up_now = (TextView) rootView.findViewById(R.id.txt_sign_up_now);
        txtLogin = (Button) rootView.findViewById(R.id.txt_login);
        input_email = (EditText) rootView.findViewById(R.id.input_email);
        input_password = (EditText) rootView.findViewById(R.id.input_password);

        input_email.setTypeface(type);
        input_password.setTypeface(type);
        txtLogin.setTypeface(type);

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(getActivity()).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                //userEt.setText(possibleEmail);
            }
        }


        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input_email.getText().toString().trim().equals("") && !input_password.getText().toString().trim().equals(""))
                    ApiBus.getInstance().post(new LoginEvent(input_email.getText().toString().trim(),
                            input_password.getText().toString().trim()));
                else
                    Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.empty_input), Toast.LENGTH_SHORT).show();

                MaterialDialog mMaterialDialog = new MaterialDialog(getActivity())
                        .setTitle(R.string.progress_dialog)
                        .setMessage(R.string.please_wait);
                mMaterialDialog.show();

//                new MaterialDialog.Builder(getActivity())
//                        .title(R.string.progress_dialog)
//                        .content(R.strinๅg.please_wait)
//                        .progress(true, 0)
//                        .show();
            }
        });

        txt_sign_up_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentSignUpSelection fragment = new FragmentSignUpSelection();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.content, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        txtSignUpFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authFacebook();
            }
        });

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        toolbar.setTitle("Login");
        // toolbar.inflateMenu(R.menu.menu_main_recent_chat);
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

    public void authFacebook() {

        handle = new FacebookHandle(getActivity(), MainApplication.APP_ID, MainApplication.APP_PERMISSIONS);
        String url = "https://graph.facebook.com/me";
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.setInverseBackgroundForced(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setTitle("Authenicating...");

        aq.auth(handle).progress(dialog)
                .ajax(url, JSONObject.class, this, "facebookCb");
    }

    public void facebookCb(String url, JSONObject jo, AjaxStatus status)
            throws JSONException {
        if (jo != null) {

            Log.e("FB_JSON", jo.toString());
            Gson gson = new Gson();
            profile = gson.fromJson(jo.toString(), FbProfile.class);
            facebookToken = handle.getToken();
            Log.e("FB_AUTHED_TOKEN", facebookToken + "");

            prefManager
                    .fbToken().put(facebookToken)
                    .fbId().put(profile.id).commit();
            //getFragmentManager().beginTransaction().add(R.id.login_container, new FbAuthFragment()).commit();
            //ApiBus.getInstance().post(new LoadFbProfileEvent(profile,facebookToken));
            ApiBus.getInstance().post(new FbAuthEvent(facebookToken));


            Log.e("POSTED", "SENT POST");
        }
    }

    @Subscribe
    public void onLoginSuccess(LoginSuccessEvent event) {
        MainApplication.USER_TOKEN = event.getLoginData().token;
        //Log.e("ARAIWA", VMApp.USER_TOKEN);
        //Log.e("ARAIWA", event.getLoginData().toString());
        prefManager
                .name().put(event.getLoginData().user.name)
                .username().put(event.getLoginData().user.username)
                .password().put(event.getLoginData().user.password)
                .email().put(event.getLoginData().user.email)
                .userId().put(event.getLoginData().user.id)
                .token().put(event.getLoginData().token)
                .cover().put(event.getLoginData().user.cover)
                .avatar().put(event.getLoginData().user.avatar)
                .isLogin().put(true)
                .commit();

        MainApplication.fetchBadge(getActivity());


        final ParseInstallation installation = ParseInstallation
                .getCurrentInstallation();

        installation.put("user_id", Integer.parseInt(event.getLoginData().user.id));
        installation.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    System.out.println("ok");
                    //deviceToken = installation.get("deviceToken").toString();
                    //System.out.println(deviceToken);
                } else {
                    System.out.println("not ok" + e.getLocalizedMessage());
                }
            }
        });

        if (event.getLoginData().state != null) {
            if (event.getLoginData().state.equals("login")) {
                Intent main = new Intent(getActivity(), MainActivityTap.class);
                startActivity(main);
                Log.e("login login55", "login");
            } else if (event.getLoginData().state.equals("register")) {
//                Intent main = new Intent(getActivity(),MyIntro.class);
//                startActivity(main);
            }
        } else {
            Intent main = new Intent(getActivity(), MainActivityTap.class);
            startActivity(main);
        }


        //Snackbar.with(getActivity().getApplicationContext()).text(event.getLoginData().token).show(getActivity());

        Log.e("VM_PROFILE", event.getLoginData().user.toString());


        Intent main = new Intent(getActivity(), MainActivityTap.class);
        startActivity(main);


        UserProfile user = event.getLoginData().user;
        ApiBus.getInstance().post(new UpdateProfileEvent(user));

        getActivity().finish();
    }


    @Subscribe
    public void onLoginFailedNetwork(FailedNetworkEvent event) {
        Log.e("ARAIWA", "onLoginFailedNetwork");
        prefManager.clear();
        Toast.makeText(getActivity().getApplicationContext(), "Cannot connect to server", Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onLoginFailedAuth(LoginFailedAuthEvent event) {
        Log.e("ARAIWA", "onLoginFailedAuth");
        Toast.makeText(getActivity().getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();

        //mPref.clear();
    }

    @Produce
    public LoadFbProfileEvent produceProfileEvent() {
        return new LoadFbProfileEvent(profile, facebookToken);
    }


}