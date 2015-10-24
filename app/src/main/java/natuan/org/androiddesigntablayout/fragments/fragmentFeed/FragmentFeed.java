package natuan.org.androiddesigntablayout.fragments.fragmentFeed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.adapter.ComplexRecyclerViewAdapter;
import natuan.org.androiddesigntablayout.model.User;

public class FragmentFeed extends Fragment {

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
    Toast.makeText(getActivity(),"sdsd",Toast.LENGTH_SHORT).show();
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




}