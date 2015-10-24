package natuan.org.androiddesigntablayout.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import natuan.org.androiddesigntablayout.R;

public class ViewHolder1 extends RecyclerView.ViewHolder {

    private TextView label1, label2;
    private ImageView profile_avatar;
    private LinearLayout ln_comment;

    public ViewHolder1(View v) {
        super(v);
        label1 = (TextView) v.findViewById(R.id.text);
        label2 = (TextView) v.findViewById(R.id.profile_name);
        profile_avatar = (ImageView) v.findViewById(R.id.profile_avatar);
        ln_comment = (LinearLayout) v.findViewById(R.id.ln_comment);
    }

    public TextView getLabel1() {
        return label1;
    }

    public void setLabel1(TextView label1) {
        this.label1 = label1;
    }

    public TextView getLabel2() {
        return label2;
    }

    public void setLabel2(TextView label2) {
        this.label2 = label2;
    }

    public ImageView getProfile_avatar() {
        return profile_avatar;
    }

    public void setProfile_avatar(ImageView profile_avatar) {
        this.profile_avatar = profile_avatar;
    }

    public LinearLayout getLn_comment() {
        return ln_comment;
    }

    public void setLn_comment(LinearLayout ln_comment) {
        this.ln_comment = ln_comment;
    }
}
