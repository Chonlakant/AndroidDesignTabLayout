package natuan.org.androiddesigntablayout.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.model.Posts;


public class TattooStoreDetailAdapter extends BaseAdapter {
    private Context activity;
    public List<Posts> list = new ArrayList<Posts>();
    String[] strName;

    public TattooStoreDetailAdapter(Context a, List<Posts> list) {
        activity = a;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        Posts path = list.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_grid, null);
            holder.sticker = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        //path = path + "&width=100&height=100&fill-to-fit=ffffff";

        //path = path.replace("assets","imgd.php?src=assets");



        Picasso.with(activity)
                .load(path.getImage())
                .into(holder.sticker);

        return convertView;
    }


    public static class ViewHolder {
        ImageView sticker;

    }
}

