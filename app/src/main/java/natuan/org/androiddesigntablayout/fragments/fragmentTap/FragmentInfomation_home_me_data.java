package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sonaive.library.HorizontalListView;

import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.adapter.AdapterChatInfomation;
import natuan.org.androiddesigntablayout.adapter.AdapterListViewChatInfomation;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;

public class FragmentInfomation_home_me_data extends Fragment implements TopMovieListView{

    HorizontalListView listPicture,listVoices,listvideos;
    AdapterChatInfomation mAdapter;
    AdapterListViewChatInfomation mAdapterListView;
    LinearLayout childScroll;

    MainPresenter mainPresenter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info_home_data, container, false);



        listPicture = (HorizontalListView) rootView.findViewById(R.id.hlv_List_picture);
        listVoices = (HorizontalListView) rootView.findViewById(R.id.hlv_List_voices);
        listvideos = (HorizontalListView) rootView.findViewById(R.id.hlv_List_videos);

        mainPresenter  = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadData();




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

    }

}