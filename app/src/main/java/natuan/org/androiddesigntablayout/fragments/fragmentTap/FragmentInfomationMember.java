package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.GenericArrayType;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.AdapterChatInfomation;
import natuan.org.androiddesigntablayout.adapter.AdapterGroupMember;
import natuan.org.androiddesigntablayout.adapter.AdapterListViewChatInfomation;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;

public class FragmentInfomationMember extends Fragment implements TopMovieListView {
    Toolbar toolbar;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        //toolbar.inflateMenu(R.menu.menu_main_tatoo);
        toolbar.setTitle("Members");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

}