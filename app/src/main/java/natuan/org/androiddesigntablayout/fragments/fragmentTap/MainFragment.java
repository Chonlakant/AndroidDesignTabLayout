package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import natuan.org.androiddesigntablayout.BaseFragment;
import natuan.org.androiddesigntablayout.MainActivityChat;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.RoundedTransformation;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.activity.MianHomGroup;
import natuan.org.androiddesigntablayout.activity.MianHomMe;
import natuan.org.androiddesigntablayout.adapter.CustomExpandableListView;
import natuan.org.androiddesigntablayout.event.SomeEvent;
import natuan.org.androiddesigntablayout.event.SuccessEvent;
import natuan.org.androiddesigntablayout.fragments.FragmentInfomation;
import natuan.org.androiddesigntablayout.handler.ApiBus;
import natuan.org.androiddesigntablayout.model.Posts;

/**
 * Created by Tuan on 6/18/2015.
 */
public class MainFragment extends BaseFragment {
    Toolbar toolbar;


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


    public static final List<Posts> listurl = new ArrayList<>();

    String name;
    String strMe;

    List<String> me = new ArrayList<String>();
    List<String> thor = new ArrayList<String>();
    List<String> ironMan = new ArrayList<String>();
    List<String> captainAmerica = new ArrayList<String>();
    Button btnNewGroup;
    private String message;
    TextView txt_profile_username, txt_profile_edit;
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
        ApiBus.getInstance().post(new SomeEvent());


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
                            dialog.dismiss();
                        }
                    });

                    TextView home = (TextView) dialog.findViewById(R.id.home);
                    home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                    ImageView img_info = (ImageView) dialog.findViewById(R.id.img_info);
                    img_info.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getActivity(), MianHomMe.class);
                            startActivity(i);
                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                }
                if (listDataHeader.get(groupPosition) == "Groups") {
                    final Dialog dialog = new Dialog(getActivity(), R.style.FullHeightDialog);
                    dialog.setContentView(R.layout.dialog_group);
                    ImageView img_group = (ImageView) dialog.findViewById(R.id.img_group);
                    ImageView img_friend_1 = (ImageView) dialog.findViewById(R.id.img_friend_1);
                    ImageView img_friend_2 = (ImageView) dialog.findViewById(R.id.img_friend_2);
                    ImageView img_friend_3 = (ImageView) dialog.findViewById(R.id.img_friend_3);
                    ImageView img_friend_4 = (ImageView) dialog.findViewById(R.id.img_friend_4);
                    ImageView info_group = (ImageView) dialog.findViewById(R.id.info_group);
                    info_group.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getActivity(), MianHomGroup.class);
                            startActivity(i);
                            dialog.dismiss();
                        }
                    });
                    Picasso.with(getActivity())
                            .load("http://fbi.dek-d.com/27/0315/5077/115653505")
                            .centerCrop()
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(img_friend_1);
                    Picasso.with(getActivity())
                            .load("http://webboard.yenta4.com/uploads/2013/05/14/190203-attachment.jpg")
                            .centerCrop()
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(img_friend_2);
                    Picasso.with(getActivity())
                            .load("http://www.keedkean.com/files/members/actual/71_99874970aa6acab68135d468ac5a1d1a.jpg")
                            .centerCrop()
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(img_friend_3);
                    Picasso.with(getActivity())
                            .load("http://fc05.deviantart.net/fs70/f/2010/242/4/8/lol__xd_by_yaraklaproos-d2xn9i2.jpg")
                            .centerCrop()
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(img_friend_4);

                    Picasso.with(getActivity())
                            .load("http://www.illustcourse.com/wp-content/gallery/inspiration/tumblr_l8qy4ldudp1qzvjlso1_500_1.png?v=7516fd43adaa")
                            .centerCrop()
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(img_group);

                    dialog.show();

                }
                if (listDataHeader.get(groupPosition) == "Favorite") {
                    final Dialog dialog = new Dialog(getActivity(), R.style.FullHeightDialog);
                    dialog.setContentView(R.layout.dialog_favortie);
                    ImageView img_bast_friend = (ImageView) dialog.findViewById(R.id.img_bast_friend);

                    Picasso.with(getActivity())
                            .load("http://pbs.twimg.com/media/CRMWPF3UsAAjufl.jpg")
                            .centerCrop()
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(img_bast_friend);

                    dialog.show();

                }
                if (listDataHeader.get(groupPosition) == "Friends") {
                    final Dialog dialog = new Dialog(getActivity(), R.style.FullHeightDialog);
                    dialog.setContentView(R.layout.dialog_friends);
                    TextView txt_chat = (TextView) dialog.findViewById(R.id.txt_chat);
                    txt_chat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getActivity(), MainActivityChat.class);
                            startActivity(i);
                            dialog.dismiss();
                        }
                    });

                    ImageView img_info = (ImageView) dialog.findViewById(R.id.img_info);
                    img_info.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FragmentInfomation fragment = new FragmentInfomation();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.add(R.id.flContainer, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            dialog.dismiss();
                        }
                    });

                    ImageView img_friend = (ImageView) dialog.findViewById(R.id.img_friend);

                    Picasso.with(getActivity())
                            .load("http://fbi.dek-d.com/27/0333/9527/114608673")
                            .centerCrop()
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(img_friend);
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

    @Subscribe
    public void getMovie(SuccessEvent event) {
        Log.e("0909090", event.getSomeResponse().getPosts().get(0).getName());
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

                        for (int i = 5; i < list.size(); i++) {

                            name = list.get(i).getName();
                            Log.e("zzzz", name);

                            thor.add(name);
                            captainAmerica.add(name);
                            ironMan.add(name);
                        }
                        for (int j = 8; j < list2.size(); j++) {
                            strMe = list.get(j).getName();
                            me.add(strMe);
                        }
                        // expListView.expandGroup(0);
                        expListView.expandGroup(3);

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        //toolbar.inflateMenu(R.menu.menu_main_friends);
        toolbar.setTitle("Friends");
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main_friends, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Toast.makeText(getActivity(), "Add", Toast.LENGTH_SHORT).show();
                FragmentAddFriends fragment = new FragmentAddFriends();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.flContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                return true;

            case R.id.action_search:


                return true;
            case R.id.action_refresh:
                Toast.makeText(getActivity(), "Refresh", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.action_status:

                return true;
            case R.id.action_friend:


                return true;
            case R.id.action_setting:


                return true;
            case R.id.action_send:
                FragmentMutiFriends fragment1 = new FragmentMutiFriends();
                FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction1.add(R.id.flContainer, fragment1);
                transaction1.addToBackStack(null);
                transaction1.commit();

                return true;
            case R.id.action_log_out:
                Toast.makeText(getActivity(), "Log out", Toast.LENGTH_SHORT).show();

                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}
