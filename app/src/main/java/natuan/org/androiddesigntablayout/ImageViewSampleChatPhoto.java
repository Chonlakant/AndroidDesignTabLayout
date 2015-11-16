package natuan.org.androiddesigntablayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.utils.Views;
import com.alexvasilkov.gestures.views.interfaces.GestureView;
import com.loopj.android.http.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.PaintingsImagesAdapter;
import natuan.org.androiddesigntablayout.logic.Painting;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.utils.GestureSettingsMenu;

public class ImageViewSampleChatPhoto extends BaseActivity
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

        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("bitmap");

        String title = getIntent().getStringExtra("title");
        Log.e("gggg", bitmap+"");

        String urlImage = BitMapToString(bitmap);

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

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
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
