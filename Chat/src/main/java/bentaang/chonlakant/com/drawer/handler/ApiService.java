package bentaang.chonlakant.com.drawer.handler;


import bentaang.chonlakant.com.drawer.model.ListChatCoverstion;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by matthewlogan on 9/3/14.
 */
public interface ApiService {


    @GET("/api/chat/recent/user/3082")
    public void getRecentChat(@Path("id") int id, Callback<ListChatCoverstion> callback);

//    @GET("/story/15522")
//    public void getStory(@Path("id") int id,
//                         Callback<StoryDataResponse> responseJson);
}
