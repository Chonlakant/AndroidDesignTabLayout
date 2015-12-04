package bentaang.chonlakant.com.drawer.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bentaang.chonlakant.com.drawer.MainApplication;
import bentaang.chonlakant.com.drawer.R;


public class AdapterRecyclerviewFontTH extends RecyclerView.Adapter<AdapterRecyclerviewFontTH.ViewHolder> {
    MainApplication myApp;

    private OnItemClickListener mItemClickListener;

    private final Context context;
    List<String> list = new ArrayList<>();

    public AdapterRecyclerviewFontTH(Context context, List<String> list) {
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
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/paaymaay_regular.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 1) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/rtemehua.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);

        }
        if (position == 2) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/supermarket/supermarket.ttf");

            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 3) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/THSarabunNew/THSarabunNew.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 4) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/Prachachon.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 5) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/DRjoyful.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 6) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/cookies_lite.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 7) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/supermarket/supermarket.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 8) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/bangna_new.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 9) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/Parggar_font.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 10) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/PL_EDIT1_02.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 11) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/DOMINO.ttf");
            holder.txt_font.setText(list.get(position));
            holder.txt_font.setTypeface(myTypeface);
        }
        if (position == 12) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/Nophamas.ttf");
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

            if (v.getId() == R.id.txt_font) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, getPosition());
                }
            }

        }

    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemVideiosClickListenerTh(final OnItemClickListener mItemClickListener) {
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