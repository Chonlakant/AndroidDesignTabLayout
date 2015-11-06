package natuan.org.androiddesigntablayout.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.alexvasilkov.gestures.views.GestureImageView;
import com.alexvasilkov.gestures.views.interfaces.GestureView;
import com.alexvasilkov.gestures.views.utils.RecyclePagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.RoundedTransformation;
import natuan.org.androiddesigntablayout.logic.Painting;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.utils.glide.GlideHelper;

public class PaintingsImagesAdapter extends RecyclePagerAdapter<PaintingsImagesAdapter.ViewHolder> {

    private final ViewPager mViewPager;
    Context context;
    //private final Painting[] mPaintings;
    private List<Posts> list = new ArrayList<>();
    private final OnSetupGestureViewListener mSetupListener;

    public PaintingsImagesAdapter(ViewPager pager, List<Posts> list, OnSetupGestureViewListener listener, Context context) {
        mViewPager = pager;
        this.list = list;
        mSetupListener = listener;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup container) {
        ViewHolder holder = new ViewHolder(container);
        holder.image.getController().enableScrollInViewPager(mViewPager);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Posts i = list.get(position);
        if (mSetupListener != null) mSetupListener.onSetupGestureView(holder.image);
        holder.image.getController().resetState();


        Picasso.with(context)
                .load(i.getImage())
                .into(holder.image);
    }


    static class ViewHolder extends RecyclePagerAdapter.ViewHolder {
        public final GestureImageView image;

        public ViewHolder(ViewGroup container) {
            super(new GestureImageView(container.getContext()));
            image = (GestureImageView) itemView;
        }
    }

    public interface OnSetupGestureViewListener {
        void onSetupGestureView(GestureView view);
    }

}
