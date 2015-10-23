package natuan.org.androiddesigntablayout.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.RoundedTransformation;
import natuan.org.androiddesigntablayout.fragments.fragmentTap.MainFragment;

public class CustomExpandableListViewFriends extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;

    public CustomExpandableListViewFriends(Context context, List<String> listDataHeader,
                                           HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_child_friends, null);
        }


        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView3);
        TextView txtListChild = (TextView) convertView.findViewById(R.id.ExListChild);




        Picasso.with(_context)
                .load(MainFragment.listurl.get(childPosition).getImage())
                        //  .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 4))
                .into(imageView);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        String Title = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_header_friends, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.ExListHeader);
        TextView textView8 = (TextView) convertView.findViewById(R.id.textView8);
        ImageView imgListGroup = (ImageView) convertView.findViewById(R.id.img_header);
        imgListGroup.setVisibility(View.GONE);

        imgListGroup.setImageResource(MainFragment.images[groupPosition]);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);



        if(groupPosition == 0){
            textView8.setTypeface(null, Typeface.BOLD);
            textView8.setText("See more");
        }if(groupPosition == 1){
            textView8.setTypeface(null, Typeface.BOLD);
            textView8.setText("Edit");
        }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}