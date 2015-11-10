package natuan.org.androiddesigntablayout;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.norbsoft.typefacehelper.TypefaceCollection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ArrayList<Font> fontStyle = new ArrayList<>();
    private Context context;
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");

    public ChatListAdapter(ArrayList<ChatMessage> chatMessages, Context context,ArrayList<Font> fontStyle) {
        this.chatMessages = chatMessages;
        this.context = context;
        this.fontStyle = fontStyle;

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

        Font listStyle = fontStyle.get(position);

        final String TYPEFACE_DEFAULT = "System default";
        final String TYPEFACE_ACTIONMAN = "Action man";
        final String TYPEFACE_ARCHRIVAL = "Arch Rival";
        final String TYPEFACE_JUICE = "Juice";
        final String TYPEFACE_UBUNTU = "Ubuntu";


        ChatMessage message = chatMessages.get(position);
        ViewHolder1 holder1;
        ViewHolder2 holder2;
        prefManager = MainApplication.getPrefManager();
        int color = prefManager.intColor().getOr(-3407872);
        String type = prefManager.font().getOr("ddd");
        Log.e("ddddddqq", type);

        Typeface myTypeface = null;


        if (type.equals(TYPEFACE_DEFAULT)) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu/Ubuntu-R.ttf");
        }
        if (type.equals(TYPEFACE_UBUNTU)) {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu/Ubuntu-B.ttf");
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
            holder1.messageTextView.setTextColor(color);
            holder1.messageTextView.setTypeface(myTypeface);
            holder1.timeTextView.setText(SIMPLE_DATE_FORMAT.format(message.getMessageTime()));


        } else if (message.getUserType() == UserType.OTHER) {

            if (convertView == null) {
                v = LayoutInflater.from(context).inflate(R.layout.chat_user2_item, null, false);

                holder2 = new ViewHolder2();


                holder2.messageTextView = (TextView) v.findViewById(R.id.message_text);
                holder2.timeTextView = (TextView) v.findViewById(R.id.time_text);
                holder2.messageStatus = (ImageView) v.findViewById(R.id.user_reply_status);
                v.setTag(holder2);

            } else {
                v = convertView;
                holder2 = (ViewHolder2) v.getTag();

            }

            holder2.messageTextView.setText(Emoji.replaceEmoji(message.getMessageText(), holder2.messageTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(16)));
            holder2.messageTextView.setTextColor(color);
            //holder2.messageTextView.setText(message.getMessageText());
            holder2.timeTextView.setText(SIMPLE_DATE_FORMAT.format(message.getMessageTime()));

            if (message.getMessageStatus() == Status.DELIVERED) {
                holder2.messageStatus.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_double_tick));
            } else if (message.getMessageStatus() == Status.SENT) {
                holder2.messageStatus.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_single_tick));

            }


        }


        return v;
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

    }
}
