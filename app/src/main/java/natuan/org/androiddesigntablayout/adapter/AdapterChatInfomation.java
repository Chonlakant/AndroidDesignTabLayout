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

public class AdapterChatInfomation extends BaseAdapter {
    Context mContext;
   List<Posts> list = new ArrayList<>();

    public AdapterChatInfomation(Context context, List<Posts> list) {
        this.mContext= context;
        this.list = list;

    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {

        Posts i = list.get(position);
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.item_infomaton, parent, false);


        ImageView ivUserAvatar = (ImageView)view.findViewById(R.id.img_item);

        Picasso.with(mContext)
                .load(i.getImage())
//                .centerCrop()
                .into(ivUserAvatar);

        return view;
    }
}