package natuan.org.androiddesigntablayout.fragments.fragmentTap;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.adapter.TattooStoreAdapter;
import natuan.org.androiddesigntablayout.fragments.fragmentTattooStore.TattooDetailActivity;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;


public class FragmentOfficeAccount extends Fragment implements TopMovieListView {

    TattooStoreAdapter adapter;
    MainPresenter mainPresenter;

    private ListView mListView;




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.office_account, container, false);
        mListView = (ListView) rootView.findViewById(R.id.list_view);

        mainPresenter  = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadData();


//        final View headerView = getActivity().getLayoutInflater().inflate(R.layout.item_header_tattoo_store_top,mListView, false);
//        mListView.addHeaderView(headerView);




        return rootView;
    }



    @Override
    public void setArticles(List<Posts> articles) {

        adapter = new TattooStoreAdapter(getActivity(),articles);
        mListView.setAdapter(adapter);

    }
}