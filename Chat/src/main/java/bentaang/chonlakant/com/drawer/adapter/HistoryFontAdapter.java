package bentaang.chonlakant.com.drawer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bentaang.chonlakant.com.drawer.R;
import bentaang.chonlakant.com.drawer.constant.Constant;
import bentaang.chonlakant.com.drawer.model.HistoryFont;
import bentaang.chonlakant.com.drawer.model.Message2;
import bentaang.chonlakant.com.drawer.widgets.RoundedTransformation;


public class HistoryFontAdapter extends BaseAdapter {

    private Context context;
    private List<HistoryFont> mMessages = null;

    public HistoryFontAdapter(Context context, List<HistoryFont> messages) {
        super();
        this.context = context;
        this.mMessages = messages;
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return mMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent) {
        HistoryFont list = mMessages.get(position);
        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_history, null);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String type = list.getNamFont();
        Typeface myTypeface = null;
        if (type != null) {
            if (type.equals(Constant.TYPEFACE_Domino)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/DOMINO.ttf");
            }
            if (type.equals(Constant.TYPEFACE_Noodle)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/noodle.ttf");
            }
            if (type.equals(Constant.TYPEFACE_OneMore)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/OneMoreNight.ttf");
            }
            if (type.equals(Constant.TYPEFACE_ARCHRIVAL)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/arch_rival/SF_Arch_Rival.ttf");
            }
            if (type.equals(Constant.TYPEFACE_Drjoyfuk)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/DRjoyful.ttf");

            }
            if (type.equals(Constant.TYPEFACE_Parggar)) {

                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/Parggar_font.ttf");
            }
            if (type.equals(Constant.TYPEFACE_DaVia)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/david.ttf");
            }
            //EN
            if (type.equals(Constant.TYPEFACE_Cartoon)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/Cartoon.ttf");
            }
            if (type.equals(Constant.TYPEFACE_Cookies)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/cookies_lite.ttf");
            }
            if (type.equals(Constant.TYPEFACE_Paaymaay)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/paaymaay_regular.ttf");

            }
            if (type.equals(Constant.TYPEFACE_MEMORY)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Memory/memory.ttf");
            }
            if (type.equals(Constant.TYPEFACE_Mari)) {

                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/MARI_DAVID.ttf");
            }

            if (type.equals(Constant.TYPEFACE_Comica)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/Comica.ttf");

            }
            if (type.equals(Constant.TYPEFACE_Rtemehua)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/rtemehua.ttf");
            }
            if (type.equals(Constant.TYPEFACE_Vaground)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/VAGRounded_Bold.tt");
            }
            if (type.equals(Constant.TYPEFACE_Goudosb)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/GOUDOSB.ttf");
            }

            if (type.equals(Constant.TYPEFACE_Bangna)) {

                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/bangna_new.ttf");
            }
            if (type.equals(Constant.TYPEFACE_SUPERMARKET)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/supermarket/supermarket.ttf");

            }
            if (type.equals(Constant.TYPEFACE_SfSppend)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/SF_Speedwaystar_Shaded.ttf");
            }
            if (type.equals(Constant.TYPEFACE_Facile)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/Facile_Sans.ttf");
            }
            if (type.equals(Constant.TYPEFACE_WrTish)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/WR_Tish_Kid.ttf");
            }
            //TH
            if (type.equals(Constant.TYPEFACE_SanFrancisco)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/San_Francisco.ttf");
            }
            if (type.equals(Constant.TYPEFACE_Prachachon)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/Prachachon.ttf");
            }
            if (type.equals(Constant.TYPEFACE_DEFAULT)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu/Ubuntu-R.ttf");
            }
            if (type.equals(Constant.TYPEFACE_JUICE)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Juice/JUICE_Bold.ttf");
            }
            if (type.equals(Constant.TYPEFACE_Koren)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/Korean.ttf");
            }
            if (type.equals(Constant.TYPEFACE_ACTIONMAN)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Action-Man/Action_Man.ttf");
            }
            if (type.equals(Constant.TYPEFACE_Weiss)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/Weissxb.ttf");
            }
            if (type.equals(Constant.TYPEFACE_TheMillion)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/The_Million_Mile_Man.ttf");
            }
            if (type.equals(Constant.TYPEFACE_Pledite)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/PL_EDIT1_02.ttf");
            }
            if (type.equals(Constant.TYPEFACE_THSARABUN)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/THSarabunNew/THSarabunNew.ttf");
            }
        }

        viewHolder.img_color = (ImageView) convertView.findViewById(R.id.img_color);
        viewHolder.txt_font = (TextView) convertView.findViewById(R.id.txt_font);

        viewHolder.img_color.setBackgroundColor(list.getColor());
        viewHolder.txt_font.setText(list.getNamFont());
        viewHolder.txt_font.setTypeface(myTypeface);

        return convertView;
    }

    static class ViewHolder {

        public ImageView img_color;
        public TextView txt_font;

    }


}
