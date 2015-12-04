package natuan.org.androiddesigntablayout.handler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;

import natuan.org.androiddesigntablayout.MainApplication;
import natuan.org.androiddesigntablayout.event.FailedNetworkEvent;
import natuan.org.androiddesigntablayout.event.FbAuthEvent;
import natuan.org.androiddesigntablayout.event.GetUserProfileEvent;
import natuan.org.androiddesigntablayout.event.GetUserProfileSuccessEvent;
import natuan.org.androiddesigntablayout.event.LoginEvent;
import natuan.org.androiddesigntablayout.event.LoginFailedAuthEvent;
import natuan.org.androiddesigntablayout.event.LoginSuccessEvent;
import natuan.org.androiddesigntablayout.event.RegisterEvent;
import natuan.org.androiddesigntablayout.event.RegisterFailedEvent;
import natuan.org.androiddesigntablayout.event.RegisterSuccessEvent;
import natuan.org.androiddesigntablayout.event.RequestOtpEvent;
import natuan.org.androiddesigntablayout.event.UserProfileDataResponse;
import natuan.org.androiddesigntablayout.model.LoginData;
import natuan.org.androiddesigntablayout.model.RegisterData;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by matthewlogan on 9/3/14.
 */
public class ApiHandlerVM {

    private Context context;
    private ApiServiceVM api;
    private ApiBus apiBus;

    public ApiHandlerVM(Context context, ApiServiceVM api,
                        ApiBus apiBus) {

        this.context = context;
        this.api = api;
        this.apiBus = apiBus;
    }

    public void registerForEvents() {
        apiBus.register(this);
    }

    @Subscribe public void onLoginEvent(LoginEvent event) {
        Log.e("HEY2!","Login: " +event.getUsername() + " : " + event.getPassword());

        Map<String, String> options = new HashMap<String, String>();
        options.put("username", event.getUsername());
        options.put("password", event.getPassword());

        api.login(options, new Callback<LoginData>() {
            @Override
            public void success(LoginData loginData, Response response) {
                //Log.e("loginData",loginData.apiToken);
                Log.e("response", response.getBody().toString());

                if (loginData.status.equals("1"))

                    ApiBus.getInstance().post(new LoginSuccessEvent(loginData));
                else
                    ApiBus.getInstance().post(new LoginFailedAuthEvent());
                Log.e("loginData", loginData.state + "");
                Log.e("POSTBACK", "post response back to LoginFragment");
            }

            @Override
            public void failure(RetrofitError error) {
                //Log.e("response",error.getBody().toString());
                Log.e("failedNetwork", "failedNetworkEvent");
                apiBus.post(new FailedNetworkEvent());
            }
        });
    }


    @Subscribe public void onFbLoginEvent(FbAuthEvent event) {

        Map<String, String> options = new HashMap<String, String>();
        options.put("access_token", event.getFbToken());

        api.fbLogin(options, new Callback<LoginData>() {
            @Override
            public void success(LoginData loginData, Response response) {
                //Log.e("loginData",loginData.apiToken);
                Log.e("response", response.getBody().toString());

                if (loginData.status.equals("1"))
                    apiBus.post(new LoginSuccessEvent(loginData));
                else
                    apiBus.post(new LoginFailedAuthEvent());

                Log.e("POSTBACK", "post response back to LoginFragment");
            }

            @Override
            public void failure(RetrofitError error) {
                //Log.e("response",error.getBody().toString());
                apiBus.post(new FailedNetworkEvent());
            }
        });
    }

    @Subscribe public void onRegisterEvent(RegisterEvent event) {
        Map<String, String> options = new HashMap<String, String>();

        options.put("name", event.getName());
        options.put("username", event.getUsername());
        options.put("password", event.getPassword());
        options.put("email", event.getEmail());
        options.put("gender", event.getGender());

        api.register(options, new Callback<RegisterData>() {
            @Override
            public void success(RegisterData registerData, Response response) {
                //Log.e("loginData",loginData.apiToken);
                Log.e("response", response.getBody().mimeType());

                if (registerData.status.equals("1")) {
                    apiBus.post(new RegisterSuccessEvent(registerData));
                } else {
                    apiBus.post(new RegisterFailedEvent(registerData.message));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("response", error.getBody().toString());
                apiBus.post(new FailedNetworkEvent());
            }
        });
    }

    @Subscribe public void onRequestOtp(RequestOtpEvent event) {
        Map<String, String> options = new HashMap<String, String>();

        options.put("mobile", event.getMobile());
        options.put("message", event.getMessage());

        api.otp(options);
    }

    @Subscribe public void onGetUserProfile(GetUserProfileEvent event) {

        Map<String, String> options = new HashMap<String, String>();
        String userId = MainApplication.mPref.userId().getOr("7");
        if(!userId.equals("0"))
            options.put("user_id", userId);
        Log.e("OK",userId);
        if(!event.getUserId().equals("")) {

            api.getProfile(options,Integer.parseInt(event.getUserId()), new Callback<UserProfileDataResponse>() {
                @Override
                public void success(UserProfileDataResponse userProfileDataResponse, Response response) {
                    GetUserProfileSuccessEvent event = new GetUserProfileSuccessEvent(userProfileDataResponse.user, userProfileDataResponse.count);
                    ApiBus.getInstance().post(event);
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("error", error.toString());
                }

            });
        } else {
            api.getProfileUsername(options,event.getUsername(), new Callback<UserProfileDataResponse>() {
                @Override
                public void success(UserProfileDataResponse userProfileDataResponse, Response response) {
                    GetUserProfileSuccessEvent event = new GetUserProfileSuccessEvent(userProfileDataResponse.user, userProfileDataResponse.count);
                    ApiBus.getInstance().post(event);
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("error", error.toString());
                }

            });
        }

    }

}
