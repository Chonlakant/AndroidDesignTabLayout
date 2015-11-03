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

import natuan.org.androiddesigntablayout.R;


public class TattooStoreDetailAdapter extends BaseAdapter {
    private Context activity;
    public ArrayList<String> list = new ArrayList<String>();
    String[] strName;
    public TattooStoreDetailAdapter(Context a, ArrayList<String> list) {
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
        String path = list.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_grid, null);
            holder.sticker = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        //path = path + "&width=100&height=100&fill-to-fit=ffffff";

        //path = path.replace("assets","imgd.php?src=assets");



        Log.e("tattoopath",path);

        Picasso.with(activity)
                .load(path)
                .into(holder.sticker);

        return convertView;
    }


    public static class ViewHolder {
        ImageView sticker;

    }
}

