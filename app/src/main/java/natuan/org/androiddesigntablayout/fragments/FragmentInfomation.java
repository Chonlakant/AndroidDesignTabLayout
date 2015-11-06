package natuan.org.androiddesigntablayout.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;


import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.activity.ImageViewSampleActivity;
import natuan.org.androiddesigntablayout.adapter.AdapterChatInfomation;
import natuan.org.androiddesigntablayout.adapter.AdapterListViewChatInfomation;
import natuan.org.androiddesigntablayout.adapter.AdapterRecyclerviewHorizontalMedia;
import natuan.org.androiddesigntablayout.adapter.AdapterRecyclerviewHorizontalVideos;
import natuan.org.androiddesigntablayout.adapter.AdapterRecyclerviewHorizontalVoices;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;

public class FragmentInfomation extends Fragment implements TopMovieListView {

    ListView listGroup;
    Toolbar toolbar;
    AdapterListViewChatInfomation mAdapterListView;
    LinearLayout childScroll;

    RecyclerView rvContacts;
    RecyclerView rvContacts2;
    RecyclerView rvContacts3;
    AdapterRecyclerviewHorizontalMedia adapterRecyclerviewHorizontal;
    AdapterRecyclerviewHorizontalVoices adapterRecyclerviewHorizontalVoices;
    AdapterRecyclerviewHorizontalVideos adapterRecyclerviewHorizontalVideos;

    MainPresenter mainPresenter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat_infomation, container, false);


        rvContacts = (RecyclerView) rootView.findViewById(R.id.rvContacts);
        rvContacts2 = (RecyclerView) rootView.findViewById(R.id.rvContacts2);
        rvContacts3 = (RecyclerView) rootView.findViewById(R.id.rvContacts3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvContacts.setLayoutManager(layoutManager);
        rvContacts2.setLayoutManager(layoutManager2);
        rvContacts3.setLayoutManager(layoutManager3);

        listGroup = (ListView) rootView.findViewById(R.id.list_group);
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadData();

        setListViewHeightBasedOnItems(listGroup);


        return rootView;
    }

    @Override
    public void setArticles(final List<Posts> articles) {

        mAdapterListView = new AdapterListViewChatInfomation(getActivity(), articles);
        Log.e("articles", articles.get(0).getName());
        adapterRecyclerviewHorizontal = new AdapterRecyclerviewHorizontalMedia(getActivity(), articles);

        rvContacts.setAdapter(adapterRecyclerviewHorizontal);
        adapterRecyclerviewHorizontal.SetOnItemClickListener(new AdapterRecyclerviewHorizontalMedia.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String imagUrl = articles.get(position).getImage();
                String title = articles.get(position).getName();
                Intent i = new Intent(getActivity(), ImageViewSampleActivity.class);
                i.putExtra("imagUrl", imagUrl);
                i.putExtra("title", title);
                startActivity(i);
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
        listGroup.setAdapter(mAdapterListView);
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        toolbar.inflateMenu(R.menu.menu_main_tatoo);
        toolbar.setTitle("Chat information");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }
}