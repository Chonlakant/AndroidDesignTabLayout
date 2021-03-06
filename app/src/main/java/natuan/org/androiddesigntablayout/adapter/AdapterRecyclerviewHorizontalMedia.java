package natuan.org.androiddesigntablayout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.model.Posts;


public class AdapterRecyclerviewHorizontalMedia extends RecyclerView.Adapter<AdapterRecyclerviewHorizontalMedia.ViewHolder> {


    private OnItemClickListener mItemClickListener;

    private final Context context;
    List<Posts> list = new ArrayList<>();

    public AdapterRecyclerviewHorizontalMedia(Context context, List<Posts> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.item_infomaton, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Posts item = list.get(position);


        Picasso.with(context)
                .load(item.getImage())
                .fit().centerCrop()
                .into(holder.ivUserAvatar);
        ;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView ivUserAvatar;


        public ViewHolder(View view) {
            super(view);


            ivUserAvatar = (ImageView) view.findViewById(R.id.img_item);


            ivUserAvatar.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.img_item:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }

            }

        }

    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    /*
     * Snippet from http://stackoverflow.com/a/363692/1008278
     */
    public static int randInt(int min, int max) {
        final Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    /* ==========This Part is not necessary========= */

}