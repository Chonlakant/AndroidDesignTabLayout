package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.AdapterChatInfomation;
import natuan.org.androiddesigntablayout.adapter.AdapterRecyclerviewHorizontalMedia;
import natuan.org.androiddesigntablayout.adapter.AdapterRecyclerviewHorizontalVideos;
import natuan.org.androiddesigntablayout.adapter.AdapterRecyclerviewHorizontalVoices;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;

public class FragmentInfomation_home_me_data extends Fragment implements TopMovieListView {
    TextView menu_pictures;
    Toolbar toolbar;
    AdapterChatInfomation mAdapter;
    RecyclerView rvContacts;
    RecyclerView rvContacts2;
    RecyclerView rvContacts3;
    AdapterRecyclerviewHorizontalMedia adapterRecyclerviewHorizontal;
    AdapterRecyclerviewHorizontalVoices adapterRecyclerviewHorizontalVoices;
    AdapterRecyclerviewHorizontalVideos adapterRecyclerviewHorizontalVideos;
    MainPresenter mainPresenter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info_home_data, container, false);
        menu_pictures = (TextView) rootView.findViewById(R.id.menu_more_picture);

        rvContacts = (RecyclerView) rootView.findViewById(R.id.rvContacts);
        rvContacts2 = (RecyclerView) rootView.findViewById(R.id.rvContacts2);
        rvContacts3 = (RecyclerView) rootView.findViewById(R.id.rvContacts3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvContacts.setLayoutManager(layoutManager);
        rvContacts2.setLayoutManager(layoutManager2);
        rvContacts3.setLayoutManager(layoutManager3);


        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadData();

        menu_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return rootView;
    }

    @Override
    public void setArticles(final List<Posts> articles) {
        mAdapter = new AdapterChatInfomation(getActivity(), articles);
        adapterRecyclerviewHorizontal = new AdapterRecyclerviewHorizontalMedia(getActivity(), articles);

        rvContacts.setAdapter(adapterRecyclerviewHorizontal);
        adapterRecyclerviewHorizontal.SetOnItemClickListener(new AdapterRecyclerviewHorizontalMedia.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String imagUrl = articles.get(position).getImage();
                String title = articles.get(position).getName();

            }
        });

        adapterRecyclerviewHorizontalVoices = new AdapterRecyclerviewHorizontalVoices(getActivity(), articles);
        rvContacts2.setAdapter(adapterRecyclerviewHorizontalVoices);
        adapterRecyclerviewHorizontalVoices.SetVoicesOnItemClickListener(new AdapterRecyclerviewHorizontalVoices.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        adapterRecyclerviewHorizontalVideos = new AdapterRecyclerviewHorizontalVideos(getActivity(), articles);
        rvContacts3.setAdapter(adapterRecyclerviewHorizontalVideos);
        adapterRecyclerviewHorizontalVideos.SetOnItemVideiosClickListener(new AdapterRecyclerviewHorizontalVideos.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

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
        toolbar.setTitle("Media");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }




}