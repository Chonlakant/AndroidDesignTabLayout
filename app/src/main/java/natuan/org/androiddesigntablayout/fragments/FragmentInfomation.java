package natuan.org.androiddesigntablayout.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sonaive.library.HorizontalListView;

import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.adapter.AdapterChatInfomation;
import natuan.org.androiddesigntablayout.adapter.AdapterListViewChatInfomation;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;

public class FragmentInfomation extends Fragment implements TopMovieListView{

    HorizontalListView listPicture,listVoices,listvideos;
    ListView listGroup;
    AdapterChatInfomation mAdapter;
    AdapterListViewChatInfomation mAdapterListView;
    LinearLayout childScroll;

    MainPresenter mainPresenter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat_infomation, container, false);



        listPicture = (HorizontalListView) rootView.findViewById(R.id.hlv_List_picture);
        listVoices = (HorizontalListView) rootView.findViewById(R.id.hlv_List_voices);
        listvideos = (HorizontalListView) rootView.findViewById(R.id.hlv_List_videos);
        listGroup = (ListView) rootView.findViewById(R.id.list_group);
        mainPresenter  = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadData();

        setListViewHeightBasedOnItems(listGroup);


        return rootView;
    }

    @Override
    public void setArticles(List<Posts> articles) {
        mAdapter = new AdapterChatInfomation(getActivity(),articles);
        mAdapterListView = new AdapterListViewChatInfomation(getActivity(),articles);
        Log.e("articles",articles.get(0).getName());
        listPicture.setAdapter(mAdapter);
        listVoices.setAdapter(mAdapter);
        listvideos.setAdapter(mAdapter);
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
}