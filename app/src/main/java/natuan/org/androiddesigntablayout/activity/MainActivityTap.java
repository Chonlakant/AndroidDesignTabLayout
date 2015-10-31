package natuan.org.androiddesigntablayout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.MainApplication;
import natuan.org.androiddesigntablayout.PrefManager;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.fragments.fragmentFeed.FragmentFeed;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentAddFriends;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentByUsername;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentCamera;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentMore;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.FragmentRecentChats;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.MainFragment;
import natuan.org.androiddesigntablayout.impls.OnFragmentInteractionListener;
import natuan.org.androiddesigntablayout.widgets.adapters.FragmentPageAdapter;


public class MainActivityTap extends BaseActivity implements OnFragmentInteractionListener {
    @InjectView(R.id.viewpager)
    ViewPager mViewpager;
    @InjectView(R.id.tabs)
    TabLayout mTabs;
    Toolbar mToolbar;

    PrefManager pref;
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
        setupViewPager(mViewpager);
        setupTabLayout(mTabs);
        pref = MainApplication.getPrefManager();
    }


    public void setupViewPager(ViewPager viewPager) {
        pageAdapter = new FragmentPageAdapter(getApplicationContext(), getSupportFragmentManager());
        pageAdapter.addFragment(FragmentFeed.getInstance(library), "Timeline", R.drawable.clock);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_add:
//                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
//                FragmentAddFriends fragment = new FragmentAddFriends();
//                FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
//                transaction.add(R.id.flContainer, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//                return true;
//
//            case R.id.action_search:
//                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
//
//                return true;
//            case R.id.action_refresh:
//                Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
//
//                return true;
//            case R.id.action_status:
//                Toast.makeText(this, "Status", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.action_friend:
//                Toast.makeText(this, "Friend", Toast.LENGTH_SHORT).show();
//
//                return true;
//            case R.id.action_setting:
//                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
//
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (pref.isLogin().getOr(true)) {


        }
    }


}
