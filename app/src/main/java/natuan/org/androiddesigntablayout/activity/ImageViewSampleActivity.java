package natuan.org.androiddesigntablayout.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.utils.Views;
import com.alexvasilkov.gestures.views.interfaces.GestureView;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.adapter.PaintingsImagesAdapter;
import natuan.org.androiddesigntablayout.logic.Painting;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.utils.GestureSettingsMenu;

public class ImageViewSampleActivity extends BaseActivity
        implements ViewPager.OnPageChangeListener, PaintingsImagesAdapter.OnSetupGestureViewListener {

    private Painting[] mPaintings;

    private ViewPager mViewPager;
    private TextView mTitleView;
    List<Posts> list = new ArrayList<>();
    private GestureSettingsMenu mSettingsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_sample);


        String urlImage = getIntent().getStringExtra("imagUrl");
        String title = getIntent().getStringExtra("title");
        Log.e("gggg", urlImage);

        Posts i = new Posts();
        i.setImage(urlImage);
        list.add(i);
        mPaintings = Painting.getAllPaintings(getResources());

        mSettingsMenu = new GestureSettingsMenu();
        mSettingsMenu.onRestoreInstanceState(savedInstanceState);

        mTitleView = Views.find(this, R.id.painting_title);
        mTitleView.setText(title);
        mViewPager = Views.find(this, R.id.paintings_view_pager);
        mViewPager.setAdapter(new PaintingsImagesAdapter(mViewPager, list, this, getApplication()));
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.view_pager_margin));
        onPageSelected(0); // Manually calling for first item
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mSettingsMenu.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mSettingsMenu.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mSettingsMenu.onOptionsItemSelected(item)) {
            invalidateOptionsMenu();
            mViewPager.getAdapter().notifyDataSetChanged();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // no-op
    }

    @Override
    public void onPageSelected(int position) {
        CharSequence title = new SpannableBuilder(this)
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append(R.string.paintings_author).append("\n")
                .clearStyle()
                .append(mPaintings[position].getTitle())
                .build();

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // no-op
    }

    @Override
    public void onSetupGestureView(GestureView view) {
        mSettingsMenu.applySettings(view);
    }

}
