package natuan.org.androiddesigntablayout.event;


import natuan.org.androiddesigntablayout.model.FbProfile;

public class LoadFbProfileEvent {

    public FbProfile profile;

    public String facebookToken;

    public LoadFbProfileEvent(FbProfile profile, String facebookToken) {
        this.profile = profile;
        this.facebookToken = facebookToken;
    }
}
