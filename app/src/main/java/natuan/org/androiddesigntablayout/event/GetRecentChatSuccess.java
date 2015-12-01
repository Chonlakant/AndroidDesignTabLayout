package natuan.org.androiddesigntablayout.event;


import natuan.org.androiddesigntablayout.model.ListChatCoverstion;

/**
 * Created by Mac on 8/6/15.
 */
public class GetRecentChatSuccess {
    public ListChatCoverstion response;

    public GetRecentChatSuccess(ListChatCoverstion response) {
        this.response = response;
    }
}
