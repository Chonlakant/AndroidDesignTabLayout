package natuan.org.androiddesigntablayout.handler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import natuan.org.androiddesigntablayout.event.SomeEvent;
import natuan.org.androiddesigntablayout.event.SuccessEvent;
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
                apiBus.post(new SuccessEvent(postss));
            }

            @Override
            public void failure(RetrofitError error) {
            Log.e("7777","2222");
            }
        });

    }
}
