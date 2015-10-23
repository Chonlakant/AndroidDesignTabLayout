package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

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
import natuan.org.androiddesigntablayout.adapter.CustomExpandableListViewFriends;
import natuan.org.androiddesigntablayout.model.Posts;

/**
 * Created by Tuan on 6/18/2015.
 */
public class MainFragmentFriends extends Fragment {


    CustomExpandableListViewFriends listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    List<Posts> list = new ArrayList<>();
    List<Posts> list2 = new ArrayList<>();

    public static final Integer[] images = {
            R.drawable.candy_logo,
            R.drawable.group,
            R.drawable.star,
            R.drawable.user
    };
    String name;
    String nameAccount;
    List<String> thor = new ArrayList<String>();
    List<String> ironMan = new ArrayList<String>();
    List<String> captainAmerica = new ArrayList<String>();
    private String message;

    public static MainFragmentFriends getInstance(String message) {
        MainFragmentFriends mainFragment = new MainFragmentFriends();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_friends, container, false);

        expListView = (ExpandableListView) rootView.findViewById(R.id.expand);
        prepareListData();
        listAdapter = new CustomExpandableListViewFriends(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

//        expListView.expandGroup(1);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), listDataHeader.get(groupPosition) + " : " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) + childPosition + "",
                        Toast.LENGTH_SHORT).show();


                return false;
            }
        });





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
                            list2.add(mainModel);

                        }

                        for (int i = 18 ; i< list.size();i++){

                            name = list.get(i).getName();
                            Log.e("zzzz", name);


                            thor.add(name);

                        }

                        for(int j =21; j < list2.size();j++){
                            nameAccount = list2.get(j).getName();
                            ironMan.add(name);
                        }
                        expListView.expandGroup(0);
                        expListView.expandGroup(1);

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

        listDataHeader.add("Official account ");
        listDataHeader.add("Your phone contact");



        listDataChild.put(listDataHeader.get(0), ironMan); // Header, Child data
        listDataChild.put(listDataHeader.get(1), thor);


    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }



}
