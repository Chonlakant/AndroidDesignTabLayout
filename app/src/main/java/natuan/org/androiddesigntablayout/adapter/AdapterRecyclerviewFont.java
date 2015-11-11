package natuan.org.androiddesigntablayout.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.norbsoft.typefacehelper.TypefaceCollection;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import natuan.org.androiddesigntablayout.MainApplication;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.model.Posts;


public class AdapterRecyclerviewFont extends RecyclerView.Adapter<AdapterRecyclerviewFont.ViewHolder> {
    MainApplication myApp;

    private OnItemClickListener mItemClickListener;

    private final Context context;
    List<String> list = new ArrayList<>();

    public AdapterRecyclerviewFont(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.item_font, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Typeface myTypeface = null;


        if (position == 0) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu/Ubuntu-R.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 1) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu/Ubuntu-B.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 2) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/ZoodHarit-thai/ZoodHarit-thai.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 3) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Action-Man/Action_Man.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 4) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Juice/JUICE_Bold.ttf");

            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 5) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/arch_rival/SF_Arch_Rival.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }


        holder.txt_font.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_font;


        public ViewHolder(View view) {
            super(view);


            txt_font = (TextView) view.findViewById(R.id.txt_font);


            txt_font.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.txt_font:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }

            }

        }

    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemVideiosClickListener(final OnItemClickListener mItemClickListener) {
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