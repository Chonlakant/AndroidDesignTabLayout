package bentaang.chonlakant.com.drawer.presenter;



import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bentaang.chonlakant.com.drawer.HistoryListView;
import bentaang.chonlakant.com.drawer.model.ChatMessage;
import bentaang.chonlakant.com.drawer.model.Message2;
import cz.msebera.android.httpclient.Header;



public class MainPresenter implements Presenter<HistoryListView> {
    private HistoryListView mMainView;

    List<ChatMessage> list = new ArrayList<>();

    @Override
    public void attachView(HistoryListView view) {
        this.mMainView = view;
    }



    public void loadData() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("http://api.candychat.net:1314/api/chat/2/history", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    if (response != null) {
                        JSONArray ja = response.getJSONArray("content");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject obj = ja.getJSONObject(i);
                            Log.e("obj",obj.toString());
                            int id = obj.getInt("id");
                            int conversationId = obj.getInt("conversationId");
                            int senderId = obj.getInt("senderId");
                            String message = obj.getString("message");
                            int messageType = obj.getInt("messageType");
                            int readCount = obj.getInt("readCount");
                            String createdAt = obj.getString("createdAt");
                            String data = obj.optString("data");

                            JSONObject sender = obj.getJSONObject("sender");
                            String username = sender.optString("username");
                            String avatar = sender.getString("avatar");
                            String profile = "https://www.vdomax.com/"+avatar;

                            ChatMessage mainModel = new ChatMessage();
                            mainModel.setId(id);
                            mainModel.setConversationId(conversationId);
                            mainModel.setMessageType(messageType);
                            mainModel.setMessage(message);
                            mainModel.setUsername(username);
                            mainModel.setSenderId(senderId);
                            mainModel.setAvatar(profile);
                            mainModel.setData(data);
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
