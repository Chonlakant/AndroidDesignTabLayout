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

public class AdapterMore extends BaseAdapter {
    Context mContext;
    String[] name;
    int[] res;

    public AdapterMore(Context context, String[] name,int[] res) {
        this.mContext= context;
        this.name = name;
        this.res = res;

    }

    public int getCount() {
        return name.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.item_more, parent, false);

        TextView txt_name = (TextView)view.findViewById(R.id.textView13);
        txt_name.setText(name[position]);



        ImageView ivUserAvatar = (ImageView)view.findViewById(R.id.imageView21);
        ivUserAvatar.setBackgroundResource(res[position]);


        return view;
    }
}