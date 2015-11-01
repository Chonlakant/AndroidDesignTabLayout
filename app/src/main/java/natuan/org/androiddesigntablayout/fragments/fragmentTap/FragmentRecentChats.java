package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ListView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.BaseFragment;
import natuan.org.androiddesigntablayout.MainActivityChat;
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
public class FragmentRecentChats extends BaseFragment implements TopMovieListView {
    Toolbar toolbar;
    ListView listView;
    AdapterRecentChats adapterRecentChats;
    ArrayList<postss> list = new ArrayList<>();
    MainPresenter mMainPresenter;

    public static FragmentRecentChats getInstance(String message) {
        FragmentRecentChats mainFragment = new FragmentRecentChats();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recent_chats, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView);
        mMainPresenter = new MainPresenter();
        mMainPresenter.attachView(this);
        mMainPresenter.loadData();

        ApiBus.getInstance().post(new SomeEvent());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("ChatRoom")
//                        .setMessage("กรุณาเข้าสู่ระบบ หรือสมัคร folkrice เพื่อดำเนินการต่อไป")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent i = new Intent(getActivity(), MainActivityChat.class);
                                startActivity(i);
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Dismiss dialog and open cart
                                dialog.dismiss();

                            }
                        }).create().show();
            }
        });

        return rootView;
    }


    @Override
    public void setArticles(List<Posts> articles) {

    }

    @Subscribe
    public void getMovie(SuccessEvent event) {
        Log.e("888888", event.getSomeResponse().getPosts().get(0).getName());
        postss i = event.getSomeResponse();

        for(int a = 0; a <= i.getPosts().size();a++){
            list.add(i);

        }
        adapterRecentChats = new AdapterRecentChats(getActivity(), list);
        listView.setAdapter(adapterRecentChats);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        toolbar.inflateMenu(R.menu.menu_main_recent_chat);
        toolbar.setTitle("Camera");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_add:
//                Toast.makeText(getActivity(), "Add", Toast.LENGTH_SHORT).show();
//                FragmentAddFriends fragment = new FragmentAddFriends();
//                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.add(R.id.flContainer, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//                return true;


        }
        return super.onOptionsItemSelected(item);
    }
}
