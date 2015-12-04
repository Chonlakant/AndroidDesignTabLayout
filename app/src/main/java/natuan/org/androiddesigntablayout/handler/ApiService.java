package natuan.org.androiddesigntablayout.handler;

import java.util.Map;

import natuan.org.androiddesigntablayout.model.ListChatCoverstion;
import natuan.org.androiddesigntablayout.model.LoginData;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.model.RegisterData;
import natuan.org.androiddesigntablayout.model.postss;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by matthewlogan on 9/3/14.
 */
public interface ApiService {

    @GET("/api_movie/get_movie2.php?uid=1&cat=1")
    public void getMovie(Callback<postss> responseJson);

    @GET("/chat/recent/user/{id}/android")
    public void getRecentChat(@Path("id") int id, Callback<ListChatCoverstion> callback);


//    @GET("/story/15522")
//    public void getStory(@Path("id") int id,
//                         Callback<StoryDataResponse> responseJson);
}
