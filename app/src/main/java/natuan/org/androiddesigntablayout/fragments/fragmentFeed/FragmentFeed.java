package natuan.org.androiddesigntablayout.fragments.fragmentFeed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.ComplexRecyclerViewAdapter;
import natuan.org.androiddesigntablayout.model.User;

public class FragmentFeed extends Fragment {
    Toolbar toolbar;
    RecyclerView rvContacts;
    ComplexRecyclerViewAdapter adapter;

    public static FragmentFeed getInstance(String message) {
        FragmentFeed mainFragment = new FragmentFeed();
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
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        Toast.makeText(getActivity(), "sdsd", Toast.LENGTH_SHORT).show();
        rvContacts = (RecyclerView) rootView.findViewById(R.id.rvContacts);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));

        bindDataToAdapter();

        return rootView;
    }

    private void bindDataToAdapter() {
        // Bind adapter to recycler view object
        rvContacts.setAdapter(new ComplexRecyclerViewAdapter(getSampleArrayList()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        toolbar = ((BaseActivity) getActivity()).getToolbar();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

//        toolbar.setTitle("VDOMAX");
//        toolbar.setSubtitle("Home");
//
//        toolbar.inflateMenu(R.menu.menu_main_noti);
        inflater.inflate(R.menu.menu_main_noti, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_noti) {
            //Do whatever you want to do
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}