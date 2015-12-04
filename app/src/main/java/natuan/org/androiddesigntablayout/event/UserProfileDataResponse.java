package natuan.org.androiddesigntablayout.event;

import com.google.gson.annotations.Expose;

import natuan.org.androiddesigntablayout.model.BadgeCount;
import natuan.org.androiddesigntablayout.model.Userlogin;


/**
 * Created by matthewlogan on 9/3/14.
 */
public class UserProfileDataResponse {

    @Expose
    public String status;
    @Expose
    public Userlogin user;
    @Expose
    public BadgeCount count;

}
