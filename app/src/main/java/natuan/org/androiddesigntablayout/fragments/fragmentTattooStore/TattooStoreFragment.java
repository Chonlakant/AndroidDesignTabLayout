package natuan.org.androiddesigntablayout.fragments.fragmentTattooStore;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.adapter.TattooStoreAdapter;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.model.TattooStore;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;


public class TattooStoreFragment extends Fragment implements TopMovieListView {

    TattooStoreAdapter adapter;
    MainPresenter mainPresenter;
    List<Posts> list = new ArrayList<Posts>();
    private ListView mListView;
    private static boolean isNotAdded = true;
    private int mPage;
    public static final String ARG_PAGE = "ARG_PAGE";



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tattoo_store, container, false);
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

        list = articles;
        adapter = new TattooStoreAdapter(getActivity(),articles);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getActivity(), TattooDetailActivity.class);
                Parcelable wrapped = Parcels.wrap(list);
                Bundle bundle = new Bundle();
                bundle.putParcelable("tattoo", wrapped);
                i.putExtra("bundle", bundle);
                startActivity(i);
            }
        });
    }
}