package natuan.org.androiddesigntablayout.presenter;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.model.Posts;


public class MainPresenter implements Presenter<TopMovieListView> {
    private TopMovieListView mMainView;

    List<Posts> list = new ArrayList<>();

    @Override
    public void attachView(TopMovieListView view) {
        this.mMainView = view;
    }



    public void loadData() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("http://ihdmovie.xyz/root/api_movie/get_movie2.php?uid=1&cat=1", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    if (response != null) {
                        JSONArray ja = response.getJSONArray("posts");
                        for (int i = 16; i < ja.length(); i++) {
                            JSONObject obj = ja.getJSONObject(i);

                            String name = obj.getString("name");
                            String image = obj.getString("image");

                            Posts mainModel = new Posts();
                            mainModel.setName(name);
                            mainModel.setImage(image);

                            list.add(mainModel);

                        }
                        mMainView.setArticles(list);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }


    @Override
    public void detachView() {
        this.mMainView = null;
    }
}
