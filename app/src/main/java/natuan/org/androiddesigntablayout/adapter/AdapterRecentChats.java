package natuan.org.androiddesigntablayout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.RoundedTransformation;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.model.postss;

public class AdapterRecentChats extends BaseAdapter {
    Context mContext;
    ArrayList<postss> list = new ArrayList<>();

    public AdapterRecentChats(Context context, ArrayList<postss> list) {
        this.mContext = context;
        this.list = list;

    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;

        postss i = list.get(position);
        LayoutInflater mInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = mInflater.inflate(R.layout.item_recent_chats, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.txt_name.setText(i.getPosts().get(position).getName());


        viewHolder.txt_msg.setText(i.getPosts().get(position).getUrl());


        Picasso.with(mContext)
                .load(i.getPosts().get(position).getImage())
                .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 4))
                .into(viewHolder.ivUserAvatar);


        return view;
    }

    private class ViewHolder {
        public TextView txt_name, txt_msg, time;
        public ImageView ivUserAvatar;

        public ViewHolder(View convertView) {
            txt_name = (TextView) convertView.findViewById(R.id.title);
            txt_msg = (TextView) convertView.findViewById(R.id.artist);
            time = (TextView) convertView.findViewById(R.id.time);
            ivUserAvatar = (ImageView) convertView.findViewById(R.id.list_image);

        }
    }
}