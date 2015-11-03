package natuan.org.androiddesigntablayout.fragments.fragmentTap;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.AdapterOfficeAccount;
import natuan.org.androiddesigntablayout.adapter.TattooStoreAdapter;
import natuan.org.androiddesigntablayout.fragments.fragmentTattooStore.TattooDetailActivity;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;


public class FragmentOfficeAccount extends Fragment implements TopMovieListView {

    AdapterOfficeAccount adapter;
    MainPresenter mainPresenter;
    Toolbar toolbar;
    private ListView mListView;




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.office_account, container, false);
        mListView = (ListView) rootView.findViewById(R.id.listView2);

        mainPresenter  = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadData();


//        final View headerView = getActivity().getLayoutInflater().inflate(R.layout.item_header_tattoo_store_top,mListView, false);
//        mListView.addHeaderView(headerView);




        return rootView;
    }



    @Override
    public void setArticles(List<Posts> articles) {

        adapter = new AdapterOfficeAccount(getActivity(),articles);
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

        toolbar.inflateMenu(R.menu.menu_main_office);
        toolbar.setTitle("Official account");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }
}