package natuan.org.androiddesigntablayout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.RoundedTransformation;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.model.postss;

public class AdapterMutiFriends extends BaseAdapter {
    Context mContext;
    List<Posts> list = new ArrayList<>();

    public AdapterMutiFriends(Context context, List<Posts> list) {
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

        Posts i = list.get(position);
        LayoutInflater mInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = mInflater.inflate(R.layout.item_send_to_multi_friend, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.txt_name.setText(i.getName());


        Picasso.with(mContext)
                .load(i.getImage())
                .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 4))
                .into(viewHolder.ivUserAvatar);


        return view;
    }

    private class ViewHolder {
        public TextView txt_name, txt_msg;
        public CheckBox time;
        public ImageView ivUserAvatar;

        public ViewHolder(View convertView) {
            txt_name = (TextView) convertView.findViewById(R.id.title);
            txt_msg = (TextView) convertView.findViewById(R.id.artist);
            time = (CheckBox) convertView.findViewById(R.id.time);
            ivUserAvatar = (ImageView) convertView.findViewById(R.id.list_image);

        }
    }
}