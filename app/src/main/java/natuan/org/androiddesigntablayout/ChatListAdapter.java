package natuan.org.androiddesigntablayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import natuan.org.androiddesigntablayout.activity.ImageViewSampleActivity;
import natuan.org.androiddesigntablayout.model.ChatMessage;
import natuan.org.androiddesigntablayout.model.Font;
import natuan.org.androiddesigntablayout.model.Status;
import natuan.org.androiddesigntablayout.model.UserType;
import natuan.org.androiddesigntablayout.widgets.Emoji;

/**
 * Created by madhur on 17/01/15.
 */
public class ChatListAdapter extends BaseAdapter {
    PrefManager prefManager;

    private ArrayList<ChatMessage> chatMessages;
    List<Font> fontList = new ArrayList<>();
    Context context;
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");

    public ChatListAdapter(ArrayList<ChatMessage> chatMessages, Context context) {
        this.chatMessages = chatMessages;
        this.context = context;

//        this.fontStyle = fontStyle;

    }


    @Override
    public int getCount() {
        return chatMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return chatMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;

        final String TYPEFACE_DEFAULT = "System default";
        final String TYPEFACE_ACTIONMAN = "Action man";
        final String TYPEFACE_ARCHRIVAL = "Arch Rival";
        final String TYPEFACE_JUICE = "Juice";
        final String TYPEFACE_ZOODHARIT = "Memory";
        final String TYPEFACE_SUPERMARKET = "SuperMarket";
        final String TYPEFACE_THSARABUN = "THSarabun";

        //EN
        final String TYPEFACE_Cartoon = "Cartoon";
        final String TYPEFACE_Comica = "Comica";
        final String TYPEFACE_DaVia = "DaVia";
        final String TYPEFACE_Facile = "Facile";
        final String TYPEFACE_Goudosb = "Goudosb";
        final String TYPEFACE_Koren = "Koren";
        final String TYPEFACE_Mari = "Mari";
        final String TYPEFACE_Noodle = "Noodle";
        final String TYPEFACE_OneMore = "OneMore";
        final String TYPEFACE_SanFrancisco = "SanFrancisco";
        final String TYPEFACE_SfSppend = "SfSppend";
        final String TYPEFACE_TheMillion = "TheMillion";
        final String TYPEFACE_Vaground = "Vaground";
        final String TYPEFACE_Weiss = "Weiss";

        //TH
        final String TYPEFACE_Bangna = "Bangna";
        final String TYPEFACE_Cookies = "Cookies";
        final String TYPEFACE_Domino = "Domino";
        final String TYPEFACE_Drjoyfuk = "Drjoyfuk";
        final String TYPEFACE_Paaymaay = "Paaymaay";
        final String TYPEFACE_Parggar = "Parggar";
        final String TYPEFACE_Pledite = "Pledite";
        final String TYPEFACE_Prachachon = "Prachachon";
        final String TYPEFACE_Rtemehua = "Rtemehua";
        final String TYPEFACE_WrTish = "WrTish";


        ChatMessage message = chatMessages.get(position);
        ViewHolder1 holder1;
        ViewHolder2 holder2;

        String type = message.getTypeStyle();
        String typeColor = message.getTypeColor();
        int colorName;

        if (typeColor != null) {
            colorName = Integer.parseInt(typeColor);
        } else {
            colorName = Color.BLACK;
        }
        Typeface myTypeface = null;
        if (type != null) {
            if (type.equals(TYPEFACE_DEFAULT)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu/Ubuntu-R.ttf");
            }
            if (type.equals(TYPEFACE_ZOODHARIT)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Memory/memory.ttf");
            }
            if (type.equals(TYPEFACE_ACTIONMAN)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Action-Man/Action_Man.ttf");
            }
            if (type.equals(TYPEFACE_JUICE)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Juice/JUICE_Bold.ttf");
            }
            if (type.equals(TYPEFACE_ARCHRIVAL)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/arch_rival/SF_Arch_Rival.ttf");
            }
            if (type.equals(TYPEFACE_SUPERMARKET)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/supermarket/supermarket.ttf");
            }
            if (type.equals(TYPEFACE_THSARABUN)) {
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/THSarabunNew/THSarabunNew.ttf");
            }
        } else {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu/Ubuntu-R.ttf");
        }

        if (message.getUserType() == UserType.SELF) {
            if (convertView == null) {
                v = LayoutInflater.from(context).inflate(R.layout.chat_user1_item, null, false);
                holder1 = new ViewHolder1();

                holder1.messageTextView = (TextView) v.findViewById(R.id.message_text);
                holder1.timeTextView = (TextView) v.findViewById(R.id.time_text);

                v.setTag(holder1);
            } else {
                v = convertView;
                holder1 = (ViewHolder1) v.getTag();

            }

            holder1.messageTextView.setText(Emoji.replaceEmoji(message.getMessageText(), holder1.messageTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(16)));
//            holder1.messageTextView.setTypeface(myTypeface);
//            holder1.messageTextView.setTextColor(colorName);

            holder1.timeTextView.setText(SIMPLE_DATE_FORMAT.format(message.getMessageTime()));


        } else if (message.getUserType() == UserType.OTHER) {

            if (convertView == null) {
                v = LayoutInflater.from(context).inflate(R.layout.chat_user2_item, null, false);

                holder2 = new ViewHolder2();

                holder2.messageTextView = (TextView) v.findViewById(R.id.message_text);
                holder2.timeTextView = (TextView) v.findViewById(R.id.time_text);
                holder2.messageStatus = (ImageView) v.findViewById(R.id.user_reply_status);
                holder2.photoImageView = (ImageView) v.findViewById(R.id.photoImageView);
                holder2.ic_play = (ImageView) v.findViewById(R.id.ic_play);
                v.setTag(holder2);

            } else {
                v = convertView;
                holder2 = (ViewHolder2) v.getTag();

            }

            holder2.messageTextView.setText(Emoji.replaceEmoji(message.getMessageText(), holder2.messageTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(16)));
            holder2.messageTextView.setTextColor(colorName);
            holder2.messageTextView.setTypeface(myTypeface);
            holder2.messageTextView.setText(message.getMessageText());
            holder2.timeTextView.setText(SIMPLE_DATE_FORMAT.format(message.getMessageTime()));
            final Bitmap bitmap;
            if (message.getmImage() != null) {
                Log.e("ok google", message.getmImage() + "");
                holder2.photoImageView.setVisibility(View.VISIBLE);
                // Bitmap myBitmap = BitmapFactory.decodeFile(message.getmImage());
                bitmap = message.getmImage();
                holder2.photoImageView.setImageBitmap(bitmap);

                holder2.ic_play.setVisibility(View.GONE);


            } else {
                holder2.photoImageView.setVisibility(View.GONE);
                Log.e("no google", message.getmImage() + "");
            }

            if (message.getMessageStatus() == Status.DELIVERED) {
                holder2.messageStatus.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_double_tick));
            } else if (message.getMessageStatus() == Status.SENT) {
                holder2.messageStatus.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_single_tick));

            }


        }


        return v;
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = chatMessages.get(position);
        return message.getUserType().ordinal();
    }

    private class ViewHolder1 {
        public TextView messageTextView;
        public TextView timeTextView;


    }

    private class ViewHolder2 {
        public ImageView messageStatus;
        public TextView messageTextView;
        public TextView timeTextView;
        public ImageView photoImageView, ic_play;

    }
}
