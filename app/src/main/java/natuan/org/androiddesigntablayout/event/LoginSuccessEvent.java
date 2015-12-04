package natuan.org.androiddesigntablayout.event;


import natuan.org.androiddesigntablayout.model.LoginData;

/**
 * Created by Mac on 3/2/15.
 */
public class LoginSuccessEvent {
    private LoginData loginData;

    public LoginSuccessEvent(LoginData loginData) {
        this.loginData = loginData;
    }

    public LoginData getLoginData() {
        return loginData;
    }
}
