package natuan.org.androiddesigntablayout.event;

import java.util.ArrayList;

import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.model.postss;

/**
 * Created by Mac on 3/2/15.
 */
public class SuccessEvent {
    private ArrayList<postss> someData;


    public SuccessEvent(ArrayList<postss> someData) {
        this.someData = someData;
    }



    public  ArrayList<postss> getSomeResponse() {
        return someData;
    }
}
