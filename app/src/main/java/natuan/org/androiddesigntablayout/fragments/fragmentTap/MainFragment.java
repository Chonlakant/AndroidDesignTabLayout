package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
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
import natuan.org.androiddesigntablayout.adapter.CustomExpandableListView;
import natuan.org.androiddesigntablayout.model.Posts;

/**
 * Created by Tuan on 6/18/2015.
 */
public class MainFragment extends Fragment {



    CustomExpandableListView listAdapter;
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


    public static final List<Posts> listurl =new ArrayList<>();

    String name;
    String strMe;

    List<String> me = new ArrayList<String>();
    List<String> thor = new ArrayList<String>();
    List<String> ironMan = new ArrayList<String>();
    List<String> captainAmerica = new ArrayList<String>();
    Button btnNewGroup;
    private String message;
    TextView txt_profile_username,txt_profile_edit;
    ImageView ivUserAvatar;

    boolean isCheck = false;
    Toolbar mToolbar;

    public static MainFragment getInstance(String message) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        expListView = (ExpandableListView) rootView.findViewById(R.id.expand);
        btnNewGroup = (Button) rootView.findViewById(R.id.btn_new_group);



        prepareListData();
        listAdapter = new CustomExpandableListView(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

//        expListView.expandGroup(1);




        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {


                return false;
            }
        });

        expListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
                isCheck = true;


                return false;
            }
        });


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(getActivity(), listDataHeader.get(groupPosition) + " : " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) + childPosition + "" + id + "",
//                        Toast.LENGTH_SHORT).show();


                if (listDataHeader.get(groupPosition) == "Me") {
                    final Dialog dialog = new Dialog(getActivity(), R.style.FullHeightDialog);
                    dialog.setContentView(R.layout.dialog_me);
                    ImageView imageView7 = (ImageView) dialog.findViewById(R.id.imageView7);
                    imageView7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FragmentEditName fragment = new FragmentEditName();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.add(R.id.flContainer, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    });

//                    FragmentInfomation fragment = new FragmentInfomation();
//                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    transaction.add(R.id.fragment_container, fragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();

                    dialog.show();

                }
                if (listDataHeader.get(groupPosition) == "Groups") {
                    final Dialog dialog = new Dialog(getActivity(), R.style.FullHeightDialog);
                    dialog.setContentView(R.layout.dialog_group);
                    dialog.show();

                }
                if (listDataHeader.get(groupPosition) == "Favorite") {
                    final Dialog dialog = new Dialog(getActivity(), R.style.FullHeightDialog);
                    dialog.setContentView(R.layout.dialog_favortie);

                    dialog.show();

                }
                if (listDataHeader.get(groupPosition) == "Friends") {
                    final Dialog dialog = new Dialog(getActivity(), R.style.FullHeightDialog);
                    dialog.setContentView(R.layout.dialog_favortie);

                    dialog.show();

                }


                return false;
            }
        });

        btnNewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentCreateGroup fragment = new FragmentCreateGroup();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.flContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
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
                            listurl.add(mainModel);
                            list2.add(mainModel);
                        }

                        for (int i = 5 ; i< list.size();i++){

                            name = list.get(i).getName();
                            Log.e("zzzz", name);

                            thor.add(name);
                            captainAmerica.add(name);
                            ironMan.add(name);
                        }
                        for(int j = 8 ; j < list2.size();j++){
                            strMe = list.get(j).getName();
                            me.add(strMe);
                        }
                       // expListView.expandGroup(0);
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

        listDataHeader.add("Me");
        listDataHeader.add("Groups");
        listDataHeader.add("Favorite");
        listDataHeader.add("Friends");


         // Header, Child data
        listDataChild.put(listDataHeader.get(0), me);
        listDataChild.put(listDataHeader.get(1), thor);
        listDataChild.put(listDataHeader.get(2), captainAmerica);
        listDataChild.put(listDataHeader.get(3), ironMan);

    }


    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

}
