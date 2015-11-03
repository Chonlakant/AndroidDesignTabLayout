package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sonaive.library.HorizontalListView;

import java.lang.reflect.GenericArrayType;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.adapter.AdapterChatInfomation;
import natuan.org.androiddesigntablayout.adapter.AdapterGroupMember;
import natuan.org.androiddesigntablayout.adapter.AdapterListViewChatInfomation;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;

public class FragmentInfomationMember extends Fragment implements TopMovieListView {

    GridView gridView;
    AdapterGroupMember adapterGroupMember;
    MainPresenter mainPresenter;


    public static FragmentInfomationMember getInstance(String message) {
        FragmentInfomationMember mainFragment = new FragmentInfomationMember();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_member, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridView2);
        Toast.makeText(getActivity(), "ggg", Toast.LENGTH_SHORT).show();

        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadData();


        return rootView;
    }

    @Override
    public void setArticles(List<Posts> articles) {
        adapterGroupMember = new AdapterGroupMember(getActivity(), articles);
        gridView.setAdapter(adapterGroupMember);
    }
}