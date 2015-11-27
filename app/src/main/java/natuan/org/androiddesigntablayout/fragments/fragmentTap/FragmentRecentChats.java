package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import natuan.org.androiddesigntablayout.BaseFragment;
import natuan.org.androiddesigntablayout.MainApplication;
import natuan.org.androiddesigntablayout.PrefManager;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.AdapterRecentChats;
import natuan.org.androiddesigntablayout.event.SomeEvent;
import natuan.org.androiddesigntablayout.event.SuccessEvent;
import natuan.org.androiddesigntablayout.handler.ApiBus;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.model.postss;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;

/**
 * Created by Tuan on 6/18/2015.
 */
public class FragmentRecentChats extends BaseFragment {
    Toolbar toolbar;
    ListView listView;
    AdapterRecentChats adapterRecentChats;
    ArrayList<Posts> list = new ArrayList<>();
    MainPresenter mMainPresenter;
    Boolean isCheck = false;
    private Bundle bundleState;
    PrefManager prefManager;

    EditText input_username;

    public static FragmentRecentChats getInstance(String message) {
        FragmentRecentChats mainFragment = new FragmentRecentChats();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onDestroyView() {
        bundleState = new Bundle();
        bundleState.putParcelable("posts", Parcels.wrap(list));
        super.onDestroyView();
    }

    boolean checkData = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recent_chats, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SWZ721BR.ttf");
        ApiBus.getInstance().post(new SomeEvent());
        input_username = (EditText) rootView.findViewById(R.id.input_username);

        input_username.setTypeface(type);

        if (bundleState != null) {
            list = Parcels.unwrap(bundleState.getParcelable("posts"));
        }
        if (checkData == false) {
            prepareListData();
            checkData = true;
            Log.e("checkData", checkData + "");
        } else {
            Toast.makeText(getContext(), "NO", Toast.LENGTH_SHORT).show();
        }
        listView = (ListView) rootView.findViewById(R.id.listView);
        Log.e("SizeGetMoview", list.size() + "");
        adapterRecentChats = new AdapterRecentChats(getActivity(), list);
        listView.setAdapter(adapterRecentChats);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("ChatRoom")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        }).create().show();
            }
        });

        return rootView;
    }

    private void updateFriendList(ArrayList<postss> loadList) {

//        ArrayList<postss> i = new ArrayList<>();
//


    }


    private void prepareListData() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("http://api.vdomax.com/user/3082/relations", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    if (response != null) {
                        JSONArray ja = response.getJSONArray("friends");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject obj = ja.getJSONObject(i);
                            String name = obj.getString("name");
                            String image = obj.getString("avatar");
                            String urlImage = "https://www.vdomax.com/" + image;
                            Log.e("aaaa", name + "");
                            String imageUrl = "http://www.mx7.com/i/91b/9SNAed.png";

                            Posts posts = new Posts();
                            posts.setName(name);
                            posts.setImage(urlImage);
                            list.add(posts);

                        }

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

    @Subscribe
    public void getMovie(SuccessEvent event) {
        updateFriendList(event.getSomeResponse());

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        toolbar.inflateMenu(R.menu.menu_main_recent_chat);
        toolbar.setTitle("RecentChat");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Settings:


                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //initDataTabHost();
        //callSocket();
        bundleState = new Bundle();
        bundleState.putParcelable("posts", Parcels.wrap(list));
    }

    @Override
    public void onResume() {
        super.onResume();
        isCheck = false;
    }
}
