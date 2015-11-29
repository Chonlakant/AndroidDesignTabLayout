package natuan.org.androiddesigntablayout.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TabLayoutHelper;

/**
 * Created by Tuan on 6/18/2015.
 */
public class FragmentPageAdapter extends FragmentStatePagerAdapter  {
    private Context mContext;

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentTitles = new ArrayList<>();
    private List<Integer> mFragmentIcons = new ArrayList<>();
    private List<String> mFragmentCount = new ArrayList<>();

    public FragmentPageAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    public void addFragment(Fragment fragment, String title, int drawable,String count) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
        mFragmentIcons.add(drawable);
        mFragmentCount.add(count);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    public View getTabView(int position) {
        View tab = LayoutInflater.from(mContext).inflate(R.layout.tabbar_view, null);
        TextView tabText = (TextView) tab.findViewById(R.id.tabText);
        TextView text_count = (TextView) tab.findViewById(R.id.text_count);
        ImageView tabImage = (ImageView) tab.findViewById(R.id.tabImage);
        tabText.setText(mFragmentTitles.get(position));
        text_count.setText(mFragmentCount.get(position));
        tabImage.setBackgroundResource(mFragmentIcons.get(position));
        if (position == 0) {
            tab.setSelected(true);
        }if(position == 1){
            text_count.setVisibility(View.GONE);
        }if(position == 2){
            text_count.setVisibility(View.GONE);
        }if(position == 3){
        }if(position == 4){
            text_count.setVisibility(View.GONE);
        }
        return tab;
    }

}
