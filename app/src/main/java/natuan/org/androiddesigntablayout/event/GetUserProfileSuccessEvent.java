package natuan.org.androiddesigntablayout.event;


import natuan.org.androiddesigntablayout.model.BadgeCount;
import natuan.org.androiddesigntablayout.model.Userlogin;

/**
 * Created by Mac on 3/3/15.
 */
public class GetUserProfileSuccessEvent {
    private Userlogin user;
    private BadgeCount count;

    public GetUserProfileSuccessEvent(Userlogin user, BadgeCount count) {
        this.user = user;
        this.count = count;
    }

    public Userlogin getUser() {
        return user;
    }

    public BadgeCount getCount() {
        return count;
    }
}
