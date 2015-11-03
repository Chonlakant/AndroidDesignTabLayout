package natuan.org.androiddesigntablayout.fragments.fragmentTap;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.AdapterMutiFriends;
import natuan.org.androiddesigntablayout.adapter.TattooStoreAdapter;
import natuan.org.androiddesigntablayout.fragments.fragmentTattooStore.TattooDetailActivity;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;


public class FragmentMutiFriends extends Fragment implements TopMovieListView {
    Toolbar toolbar;
    AdapterMutiFriends adapter;
    MainPresenter mainPresenter;
    List<Posts> list = new ArrayList<Posts>();
    private ListView mListView;
    private static boolean isNotAdded = true;
    private int mPage;
    public static final String ARG_PAGE = "ARG_PAGE";



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_muti_friends, container, false);
        mListView = (ListView) rootView.findViewById(R.id.listView3);

        mainPresenter  = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadData();


//        final View headerView = getActivity().getLayoutInflater().inflate(R.layout.item_header_tattoo_store_top,mListView, false);
//        mListView.addHeaderView(headerView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getActivity(), TattooDetailActivity.class);
                startActivity(i);
            }
        });


        return rootView;
    }



    @Override
    public void setArticles(List<Posts> articles) {

        adapter = new AdapterMutiFriends(getActivity(),articles);
        mListView.setAdapter(adapter);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        toolbar.inflateMenu(R.menu.menu_main_send);
        toolbar.setTitle("Send to multi friends");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }
}