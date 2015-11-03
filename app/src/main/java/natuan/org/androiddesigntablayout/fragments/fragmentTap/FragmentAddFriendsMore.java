package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.CustomExpandableListViewFriends;
import natuan.org.androiddesigntablayout.model.Posts;

public class FragmentAddFriendsMore extends Fragment {
    Toolbar toolbar;
    ExpandableListView expListView;
    CustomExpandableListViewFriends mAdapter;
    List<Posts> list = new ArrayList<>();
    List<Posts> list2 = new ArrayList<>();
    List<String> me = new ArrayList<String>();
    List<String> thor = new ArrayList<String>();
    String name;
    String strMe;

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    public static final List<Posts> listurl = new ArrayList<>();




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main_add_friends_more, container, false);

        expListView = (ExpandableListView) rootView.findViewById(R.id.expand);

        prepareListData();

        return rootView;
    }


    private void prepareListData() {

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("http://ihdmovie.xyz/root/api_movie/get_movie2.php?uid=1&cat=1", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    if (response != null) {
                        JSONArray ja = response.getJSONArray("posts");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject obj = ja.getJSONObject(i);

                            String name = obj.getString("name");
                            String image = obj.getString("image");

                            Posts mainModel = new Posts();
                            mainModel.setName(name);
                            mainModel.setImage(image);
                            list.add(mainModel);
                            listurl.add(mainModel);
                            list2.add(mainModel);
                        }

                        for (int i = 0; i < list.size(); i++) {

                            name = list.get(i).getName();
                            Log.e("zzzz", name);

                            thor.add(name);

                        }
                        for (int j = 0; j < list2.size(); j++) {
                            strMe = list.get(j).getName();
                            me.add(strMe);
                        }
                        // expListView.expandGroup(0);


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

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Contact");
        listDataHeader.add("Contact phone list");


        // Header, Child data
        listDataChild.put(listDataHeader.get(0), me);
        listDataChild.put(listDataHeader.get(1), thor);

        mAdapter = new CustomExpandableListViewFriends(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        toolbar.inflateMenu(R.menu.menu_add_friends);
        toolbar.setTitle("Add Friends");
        menu.setGroupVisible(0, false);
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_cancle:
//                Toast.makeText(getActivity(), "Add", Toast.LENGTH_SHORT).show();
//                FragmentAddFriends fragment = new FragmentAddFriends();
//                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.add(R.id.flContainer, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//                return true;
//
//
//        }
//        return super.onOptionsItemSelected(item);
//    }

}