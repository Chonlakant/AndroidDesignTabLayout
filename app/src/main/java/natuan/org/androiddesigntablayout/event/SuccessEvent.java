package natuan.org.androiddesigntablayout.event;

import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.model.postss;

/**
 * Created by Mac on 3/2/15.
 */
public class SuccessEvent {
    private postss someData;

    public SuccessEvent(postss someData) {
        this.someData = someData;
    }

    public postss getSomeResponse() {
        return someData;
    }
}
