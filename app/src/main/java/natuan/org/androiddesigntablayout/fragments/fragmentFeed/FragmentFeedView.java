package natuan.org.androiddesigntablayout.fragments.fragmentFeed;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import natuan.org.androiddesigntablayout.BaseFragment;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.event.SuccessEvent;
import natuan.org.androiddesigntablayout.fragments.FragmentInfomation;

public class FragmentFeedView extends BaseFragment {
    Toolbar toolbar;
    ImageView img_more, img_list;
    TextView txt_write;

    public static FragmentFeedView getInstance(String message) {
        FragmentFeedView mainFragment = new FragmentFeedView();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed_view, container, false);

        img_more = (ImageView) rootView.findViewById(R.id.img_more);
        img_list = (ImageView) rootView.findViewById(R.id.img_list);
        txt_write = (TextView) rootView.findViewById(R.id.txt_write);
        txt_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentWriteOnEvent fragment = new FragmentWriteOnEvent();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.flContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        FragmentFeedOne threeFragment = new FragmentFeedOne();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contan_home, threeFragment);
        transaction.commit();


        img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentFeedTwo threeFragment = new FragmentFeedTwo();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contan_home, threeFragment);
                transaction.commit();
            }
        });

        img_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentFeedOne threeFragment = new FragmentFeedOne();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contan_home, threeFragment);
                transaction.commit();
            }
        });

        return rootView;
    }

    @Subscribe
    public void getMovie(SuccessEvent event) {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        SpannableString title = new SpannableString(getResources().getString(R.string.events));
        title.setSpan(Typeface.createFromAsset(getActivity().getAssets(), "fonts/SWZ721BR.ttf"), 0, title.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //toolbar.inflateMenu(R.menu.menu_main_noti);
        // menu = toolbar.getMenu();
        toolbar.setTitle(title);
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main_noti, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_noti) {
            //Do whatever you want to do
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}