package natuan.org.androiddesigntablayout.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.fragments.fragmentFeed.FragmentFeedView;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentCamera;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentMore;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentRecentChats;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.MainFragment;
import natuan.org.androiddesigntablayout.impls.OnFragmentInteractionListener;
import natuan.org.androiddesigntablayout.widgets.CustomViewPager;
import natuan.org.androiddesigntablayout.widgets.adapters.FragmentPageAdapter;


public class MainActivityTap extends BaseActivity implements OnFragmentInteractionListener {
    @InjectView(R.id.viewpager)
    CustomViewPager mViewpager;
    @InjectView(R.id.tabs)
    TabLayout mTabs;
    Toolbar mToolbar;


    private FragmentPageAdapter pageAdapter;
    String library, recents, favourites, notifications, settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        library = getString(R.string.library);
        recents = getString(R.string.recents);
        favourites = getString(R.string.favourites);
        notifications = getString(R.string.notifications);
        settings = getString(R.string.settings);
        mViewpager.setPagingEnabled(false);
        setupViewPager(mViewpager);

        setupTabLayout(mTabs);

    }


    public void setupViewPager(ViewPager viewPager) {
        pageAdapter = new FragmentPageAdapter(getApplicationContext(), getSupportFragmentManager());
        pageAdapter.addFragment(FragmentFeedView.getInstance(library), "Timeline", R.drawable.clock);
        pageAdapter.addFragment(MainFragment.getInstance(recents), "Friends", R.drawable.user);
        pageAdapter.addFragment(FragmentCamera.getInstance(favourites), "Camera", R.drawable.camera_addon);
        pageAdapter.addFragment(FragmentRecentChats.getInstance(notifications), "Chats", R.drawable.chat);
        pageAdapter.addFragment(FragmentMore.getInstance(settings), "more", R.drawable.more);
        viewPager.setAdapter(pageAdapter);
    }

    public void setupTabLayout(TabLayout tabLayout) {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(mViewpager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pageAdapter.getTabView(i));
        }
        tabLayout.requestFocus();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
