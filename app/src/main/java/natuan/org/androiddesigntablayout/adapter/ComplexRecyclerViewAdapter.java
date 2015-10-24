package natuan.org.androiddesigntablayout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.model.User;
import natuan.org.androiddesigntablayout.viewholder.RecyclerViewSimpleTextViewHolder;
import natuan.org.androiddesigntablayout.viewholder.ViewHolder1;
import natuan.org.androiddesigntablayout.viewholder.ViewHolder2;
import natuan.org.androiddesigntablayout.viewholder.ViewHolder3;

public class ComplexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // The items to display in your RecyclerView
    private List<Object> items;

    private final int USER = 0, IMAGE = 1, CLIP = 3;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ComplexRecyclerViewAdapter(List<Object> items) {
        this.items = items;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof User) {
            return USER;
        } else if (items.get(position) instanceof String) {
            return IMAGE;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case USER:
                View v1 = inflater.inflate(R.layout.item_feed_text, viewGroup, false);
                viewHolder = new ViewHolder1(v1);
                break;
            case IMAGE:
                View v2 = inflater.inflate(R.layout.item_feed_photo, viewGroup, false);
                viewHolder = new ViewHolder2(v2);
                break;
            case CLIP:
                View v3 = inflater.inflate(R.layout.item_feed_clip, viewGroup, false);
                viewHolder = new ViewHolder3(v3);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        //More to come
        switch (viewHolder.getItemViewType()) {
            case USER:
                ViewHolder1 vh1 = (ViewHolder1) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case IMAGE:
                ViewHolder2 vh2 = (ViewHolder2) viewHolder;
                configureViewHolder2(vh2);
                break;
            case CLIP:
                ViewHolder3 vh3 = (ViewHolder3) viewHolder;
                configureViewHolder3(vh3);
                break;
            default:
                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    private void configureDefaultViewHolder(RecyclerViewSimpleTextViewHolder vh, int position) {
        vh.getLabel().setText((CharSequence) items.get(position));
    }

    private void configureViewHolder1(ViewHolder1 vh1, int position) {
        User user = (User) items.get(position);
        if (user != null) {
            vh1.getLabel1().setText("Name: " + user.getName1());
            vh1.getLabel2().setText("Hometown: " + user.getName2());
            vh1.getProfile_avatar().setImageResource(R.drawable.imge);
            vh1.getLn_comment().setVisibility(View.GONE);
        }
    }

    private void configureViewHolder2(ViewHolder2 vh2) {
        vh2.getImageView().setImageResource(R.drawable.imge);
        vh2.getThumb().setImageResource(R.drawable.imge);
        vh2.getLn_comment().setVisibility(View.GONE);
    }

    private void configureViewHolder3(ViewHolder3 vh3) {
        vh3.getImageView().setImageResource(R.drawable.imge);
        vh3.getThumb().setImageResource(R.drawable.imge);
        vh3.getLn_comment().setVisibility(View.GONE);
    }
}
