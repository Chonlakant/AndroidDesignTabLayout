package bentaang.chonlakant.com.drawer.event;


import bentaang.chonlakant.com.drawer.model.ListChatCoverstion;

/**
 * Created by Mac on 8/6/15.
 */
public class GetRecentChatSuccess {
    public ListChatCoverstion response;

    public GetRecentChatSuccess(ListChatCoverstion response) {
        this.response = response;
    }
}
