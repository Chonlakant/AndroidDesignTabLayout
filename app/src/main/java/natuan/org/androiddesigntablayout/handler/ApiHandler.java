package natuan.org.androiddesigntablayout.handler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import natuan.org.androiddesigntablayout.event.GetRecentChatEvent;
import natuan.org.androiddesigntablayout.event.GetRecentChatSuccess;
import natuan.org.androiddesigntablayout.event.LoginEvent;
import natuan.org.androiddesigntablayout.event.LoginFailedAuthEvent;
import natuan.org.androiddesigntablayout.event.LoginSuccessEvent;
import natuan.org.androiddesigntablayout.event.SomeEvent;
import natuan.org.androiddesigntablayout.event.SuccessEvent;
import natuan.org.androiddesigntablayout.model.ListChatCoverstion;
import natuan.org.androiddesigntablayout.model.LoginData;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.model.postss;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by matthewlogan on 9/3/14.
 */
public class ApiHandler {


    private Context context;
    private ApiService api;
    private ApiBus apiBus;

    public ApiHandler(Context context, ApiService api,
                      ApiBus apiBus) {

        this.context = context;
        this.api = api;
        this.apiBus = apiBus;
    }

    public void registerForEvents() {
        apiBus.register(this);
    }

    @Subscribe public void onGetMovie(SomeEvent event) {
        api.getMovie(new Callback<postss>() {
            @Override
            public void success(postss postss, Response response) {

                ArrayList<postss> list = new ArrayList<postss>();


                for (int i = 0; i < postss.getPosts().size(); i++) {
                    list.add(postss);
                    Log.e("SizeGetMoview", list.size() + "");
                }
                apiBus.post(new SuccessEvent(list));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("7777", "2222");
            }
        });

    }
    @Subscribe public  void onGetRecentChat(final GetRecentChatEvent event){
        api.getRecentChat(2868, new Callback<ListChatCoverstion>() {
            @Override
            public void success(ListChatCoverstion listChatCoverstion, Response response) {
            Log.e("65431",event.userId+"");


                if (listChatCoverstion.getContent().size() != 0)
                    ApiBus.getInstance().postQueue(new GetRecentChatSuccess(listChatCoverstion));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }




}
