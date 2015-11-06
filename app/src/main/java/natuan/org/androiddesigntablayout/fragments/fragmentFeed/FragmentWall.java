package natuan.org.androiddesigntablayout.fragments.fragmentFeed;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import natuan.org.androiddesigntablayout.BaseFragment;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.AdapterRecyclerViewFreeOne;
import natuan.org.androiddesigntablayout.event.SomeEvent;
import natuan.org.androiddesigntablayout.event.SuccessEvent;
import natuan.org.androiddesigntablayout.handler.ApiBus;
import natuan.org.androiddesigntablayout.model.User;

public class FragmentWall extends BaseFragment {
    RecyclerView rvContacts;
    AdapterRecyclerViewFreeOne adapter;
    Toolbar toolbar;
    public static FragmentWall getInstance(String message) {
        FragmentWall mainFragment = new FragmentWall();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    private ArrayList<Object> getSampleArrayList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new User("Dany Targaryen", "Valyria"));
        items.add(new User("Rob Stark", "Winterfell"));
        items.add(new User("Jon Snow", "Castle Black"));
        items.add("image");
        items.add("clip");
        items.add(new User("Tyrion Lanister", "King's Landing"));
        return items;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed_wall, container, false);
        rvContacts = (RecyclerView) rootView.findViewById(R.id.rvContacts);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));
        ApiBus.getInstance().post(new SomeEvent());
        bindDataToAdapter();

        return rootView;
    }

    @Subscribe
    public void getMovie(SuccessEvent event){

    }

    private void bindDataToAdapter() {
        // Bind adapter to recycler view object
        rvContacts.setAdapter(new AdapterRecyclerViewFreeOne(getSampleArrayList(), getActivity()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        //toolbar.inflateMenu(R.menu.menu_main_tatoo);
        toolbar.setTitle("Wall");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }


}