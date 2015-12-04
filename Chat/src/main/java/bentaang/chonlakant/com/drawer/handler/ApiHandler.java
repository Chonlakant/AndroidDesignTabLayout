package bentaang.chonlakant.com.drawer.handler;

import android.content.Context;
import android.util.Log;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import bentaang.chonlakant.com.drawer.event.GetRecentChatEvent;
import bentaang.chonlakant.com.drawer.event.GetRecentChatSuccess;
import bentaang.chonlakant.com.drawer.model.ListChatCoverstion;
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


    @Subscribe public  void onGetRecentChat(GetRecentChatEvent event){
        api.getRecentChat(6, new Callback<ListChatCoverstion>() {
            @Override
            public void success(ListChatCoverstion listChatCoverstion, Response response) {
            Log.e("65431",listChatCoverstion.getContent().get(0).getAvatar());

                if (listChatCoverstion.getContent().size() != 0)
                    ApiBus.getInstance().postQueue(new GetRecentChatSuccess(listChatCoverstion));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("7777", "2222");
            }
        });
    }


}
