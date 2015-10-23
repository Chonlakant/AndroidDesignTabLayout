package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.adapter.AdapterRecentChats;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;

/**
 * Created by Tuan on 6/18/2015.
 */
public class FragmentRecentChats extends Fragment implements TopMovieListView {

    ListView listView;
    AdapterRecentChats adapterRecentChats;
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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        });

        return rootView;
    }


    @Override
    public void setArticles(List<Posts> articles) {
        adapterRecentChats = new AdapterRecentChats(getActivity(),articles);
        listView.setAdapter(adapterRecentChats);
    }
}
