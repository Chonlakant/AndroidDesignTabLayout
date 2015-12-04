package bentaang.chonlakant.com.drawer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

import bentaang.chonlakant.com.drawer.MediaManager;
import bentaang.chonlakant.com.drawer.constant.Constant;
import bentaang.chonlakant.com.drawer.R;
import bentaang.chonlakant.com.drawer.widgets.RoundedTransformation;
import bentaang.chonlakant.com.drawer.model.Message2;


public class MessageAdapter extends BaseAdapter {
    private View mAnimView;
    private Context context;
    private List<Message2> mMessages = null;
    private int mMinItemWidth;
    private int mMaxItemWidth;

    public MessageAdapter(Context context, List<Message2> messages) {
        super();

        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        mMaxItemWidth = (int) (outMetrics.widthPixels * 0.7f);
        mMinItemWidth = (int) (outMetrics.widthPixels * 0.15f);
        this.context = context;
        this.mMessages = messages;

    }

    @Override
    public int getCount() {
        return mMessages != null ? mMessages.size() : 0;
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
        if (this.mMessages == null || this.mMessages.get(position) == null) {
            return 0;
        }
        return this.mMessages.get(position).getIsSender() ? 1 : 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @SuppressLint("InflateParams")
    public View getView(final int position, View convertView, ViewGroup parent) {
        String type = null;
        String typeColor = null;
        boolean isSend = false;
        final Message2 m = mMessages.get(position);
        if (m != null) {
            isSend = m.getIsSender();
            Log.e("isSend", m.getType() + "");
            type = m.getTypeStyle();
            typeColor = m.getTypeColor();

            Log.e("typeColor", typeColor);
        }
        int colorName = 0;

        if (typeColor.equals("")) {
            colorName = Color.BLACK;
        } else if (typeColor != null) {
            colorName = Color.parseColor("#" + typeColor);
        } else {
            colorName = Color.BLACK;
        }


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
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/eng/VAGRounded_Bold.ttf");
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
                myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/th/Nophamas.ttf");
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


        } else {
            myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu/Ubuntu-R.ttf");
        }
        JSONObject dataObj;
        dataObj = null;
        try {
            dataObj = new JSONObject(m.getData());
        } catch (JSONException e) {
            e.printStackTrace();
        }
       ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            if (isSend) {
                convertView = LayoutInflater.from(context).inflate(R.layout.chat_item_list_right, null);


            } else {
                convertView = LayoutInflater.from(context).inflate(R.layout.chat_item_list_left, null);
            }


            viewHolder.isSend = isSend;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.sendDateTextView = (TextView) convertView.findViewById(R.id.sendDateTextView);
        viewHolder.sendTimeTextView = (TextView) convertView.findViewById(R.id.time_text);
        viewHolder.userAvatarImageView = (ImageView) convertView.findViewById(R.id.userAvatarImageView);
        viewHolder.userNameTextView = (TextView) convertView.findViewById(R.id.userNameTextView);
        viewHolder.textTextView = (TextView) convertView.findViewById(R.id.message_text);
        viewHolder.photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        viewHolder.faceImageView = (ImageView) convertView.findViewById(R.id.faceImageView);
        viewHolder.failImageView = (ImageView) convertView.findViewById(R.id.failImageView);
        viewHolder.sendingProgressBar = (ProgressBar) convertView.findViewById(R.id.sendingProgressBar);
        viewHolder.logo_clip = (ImageView) convertView.findViewById(R.id.logo_clip);
        viewHolder.chat_item_layout_content = (RelativeLayout) convertView.findViewById(R.id.chat_item_layout_content);
        viewHolder.loction = (LinearLayout) convertView.findViewById(R.id.layout_invite);
        viewHolder.user_reply_status = (TextView) convertView.findViewById(R.id.user_reply_status);
        viewHolder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
        viewHolder.logo_avatra = (ImageView) convertView.findViewById(R.id.logo_avatra);
        viewHolder.seconds = (TextView) convertView.findViewById(R.id.id_recorder_time);
        viewHolder.length = convertView.findViewById(R.id.id_recorder_length);
        viewHolder.mAnimView = convertView.findViewById(R.id.id_recorder_anim);

        viewHolder.isSend = isSend;
        convertView.setTag(viewHolder);

        try {
            String dateString = DateFormat.format("yyyy-MM-dd h:mmaa", m.getTime()).toString();
            String[] t = dateString.split(" ");
            viewHolder.sendDateTextView.setText(t[0]);
            viewHolder.sendTimeTextView.setText(t[1]);

            if (position == m.getType()) {
                viewHolder.sendDateTextView.setVisibility(View.VISIBLE);
            } else {
                //TODO is same day ?
                Message2 pmsg = mMessages.get(position - 1);
                if (inSameDay(pmsg.getTime(), m.getTime())) {
                    viewHolder.sendDateTextView.setVisibility(View.GONE);
                } else {
                    viewHolder.sendDateTextView.setVisibility(View.VISIBLE);
                }

            }

        } catch (Exception e) {
        }

//        viewHolder.userNameTextView.setText(m.getFromUserName());
        // viewHolder.userNameTextView.setVisibility(View.GONE);
//        Picasso.with(context).load(m.getFromUserAvatar()).centerCrop().resize(200, 200)
//                .transform(new RoundedTransformation(100, 4)).into(viewHolder.userAvatarImageView);
//        viewHolder.textTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);


        //Log.e("dataObj["+position+"]",m.getData());

        //LayoutParams sendTimeTextViewLayoutParams = (LayoutParams) viewHolder.sendTimeTextView.getLayoutParams();


        switch (m.getType()) {
            case 0://text
                if (m.getContent() != null) {
                    viewHolder.seconds.setVisibility(View.GONE);
                    viewHolder.mAnimView.setVisibility(View.GONE);
                    viewHolder.length.setVisibility(View.GONE);
                    Log.e("m.getContent()", m.getContent() + "");
                    viewHolder.faceImageView.setVisibility(View.GONE);
                    viewHolder.loction.setVisibility(View.GONE);
                    viewHolder.sendingProgressBar.setVisibility(View.GONE);
                    viewHolder.logo_clip.setVisibility(View.GONE);
                    viewHolder.txt_name.setVisibility(View.GONE);
                    viewHolder.logo_avatra.setVisibility(View.GONE);
                    viewHolder.textTextView.setVisibility(View.VISIBLE);
                    viewHolder.textTextView.setText(m.getContent());
                    viewHolder.photoImageView.setVisibility(View.GONE);
                    viewHolder.textTextView.setTextColor(colorName);
                    viewHolder.textTextView.setTypeface(myTypeface);
                } else {
                    viewHolder.textTextView.setText("empty");
                    viewHolder.textTextView.setVisibility(View.VISIBLE);
                    viewHolder.photoImageView.setVisibility(View.GONE);
                    viewHolder.faceImageView.setVisibility(View.GONE);
                }
                if (m.getIsSender()) {
                    //sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.message_text);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                    LayoutParams layoutParams = (LayoutParams) viewHolder.failImageView.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.message_text);
                    if (m.getSendSuccess() != null && !m.getSendSuccess()) {
                        //viewHolder.failImageView.setVisibility(View.VISIBLE);
                        viewHolder.failImageView.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.failImageView.setVisibility(View.GONE);
                    }

                    if (m.getState() != null && m.getState() == 0) {
                        viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                        viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.sendingProgressBar.setVisibility(View.GONE);
                    }

                } else {
                    viewHolder.failImageView.setVisibility(View.GONE);
                    viewHolder.sendingProgressBar.setVisibility(View.GONE);

                    //sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.message_text);
                    //viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                }

                break;

            case 1://face
                viewHolder.seconds.setVisibility(View.GONE);
                viewHolder.mAnimView.setVisibility(View.GONE);
                viewHolder.length.setVisibility(View.GONE);
                viewHolder.loction.setVisibility(View.GONE);
                viewHolder.photoImageView.setVisibility(View.GONE);
                viewHolder.textTextView.setVisibility(View.GONE);
                viewHolder.faceImageView.setVisibility(View.VISIBLE);
                viewHolder.sendingProgressBar.setVisibility(View.GONE);
                viewHolder.sendDateTextView.setVisibility(View.GONE);
                viewHolder.logo_clip.setVisibility(View.GONE);
                viewHolder.txt_name.setVisibility(View.GONE);
                viewHolder.logo_avatra.setVisibility(View.GONE);
                viewHolder.textTextView.setVisibility(View.GONE);
                viewHolder.photoImageView.setVisibility(View.GONE);


                if (dataObj != null && !dataObj.optString("tattooUrl").equals("")) {
                    String image = dataObj.optString("tattooUrl");
                    Log.e("4444", image);
                    //Picasso.with(context).load(dataObj.optString("tattooUrl")).into(viewHolder.faceImageView);
                    int resId = context.getResources().getIdentifier(image, "drawable", context.getPackageName());
                    viewHolder.faceImageView.setImageResource(resId);
                }


                if (m.getIsSender()) {
//                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, com.jialin.chat.R.id.faceImageView);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                    LayoutParams layoutParams = (LayoutParams) viewHolder.failImageView.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.faceImageView);
                    if (m.getSendSuccess() != null && !m.getSendSuccess()) {
                        //viewHolder.failImageView.setVisibility(View.VISIBLE);
                        viewHolder.failImageView.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.failImageView.setVisibility(View.GONE);
                    }

                    if (m.getState() != null && m.getState() == 0) {
                        viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                        viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.sendingProgressBar.setVisibility(View.GONE);
                    }

                } else {
                    viewHolder.failImageView.setVisibility(View.GONE);

//                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, com.jialin.chat.R.id.faceImageView);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                }

                break;


            case 2://photo
                viewHolder.seconds.setVisibility(View.GONE);
                viewHolder.mAnimView.setVisibility(View.GONE);
                viewHolder.length.setVisibility(View.GONE);
                viewHolder.logo_clip.setVisibility(View.GONE);
                viewHolder.loction.setVisibility(View.GONE);
                viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                viewHolder.sendDateTextView.setVisibility(View.GONE);
                viewHolder.logo_clip.setVisibility(View.GONE);
                viewHolder.txt_name.setVisibility(View.GONE);
                viewHolder.logo_avatra.setVisibility(View.GONE);
                viewHolder.textTextView.setVisibility(View.GONE);
                viewHolder.photoImageView.setVisibility(View.VISIBLE);
                viewHolder.faceImageView.setVisibility(View.GONE);

                if (dataObj != null) {
                    // from chat history
                    if (((dataObj.optString("fileType").equals("image/jpeg") || dataObj.optString("fileType").equals("image/png"))) && dataObj.optString("thumb") != null) {
                        // String imageUrl = "https://chat.vdomax.com:1314" + dataObj.optString("url");
                        String imageUrl = dataObj.optString("thumb");
                        Log.e("myurl", imageUrl);
                        if (imageUrl != null && !imageUrl.equals(""))
                            Picasso.with(context).load(imageUrl).fit().centerCrop().into(viewHolder.photoImageView);
                    } else {
                        // from local photo taken or picked
                        String uriStr = dataObj.optString("imageUriPhoto");
                        URI imageUri = null;
                        try {
                            imageUri = new URI(uriStr);
                            Bitmap myImg = BitmapFactory.decodeFile(imageUri.getPath());
                            if (imageUri.getPath() != null && !imageUri.getPath().equals(""))
                                Picasso.with(context).load(imageUri.getPath()).resize(400, 400).into(viewHolder.photoImageView);
                            //viewHolder.photoImageView.setImageBitmap(myImg);
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }


                }


                if (m.getIsSender()) {
//                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.photoImageView);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                    Log.e("getIsSender", m.getIsSender() + "");
                    LayoutParams layoutParams = (LayoutParams) viewHolder.failImageView.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.photoImageView);
                    if (m.getSendSuccess() != null && !m.getSendSuccess()) {
                        //viewHolder.failImageView.setVisibility(View.VISIBLE);
                        viewHolder.failImageView.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.failImageView.setVisibility(View.GONE);
                    }

                    if (m.getState() != null && m.getState() == 0) {
                        viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                        viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                    } else if (m.getState() == 1) {
                        viewHolder.sendingProgressBar.setVisibility(View.GONE);
                        viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                    }

                } else {
                    viewHolder.failImageView.setVisibility(View.GONE);
                    viewHolder.sendingProgressBar.setVisibility(View.GONE);
//                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.photoImageView);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                }

                break;

            case 3:
                viewHolder.seconds.setVisibility(View.GONE);
                viewHolder.mAnimView.setVisibility(View.GONE);
                viewHolder.length.setVisibility(View.GONE);
                viewHolder.loction.setVisibility(View.GONE);
                viewHolder.sendDateTextView.setVisibility(View.GONE);
                viewHolder.logo_clip.setVisibility(View.VISIBLE);
                viewHolder.txt_name.setVisibility(View.GONE);
                viewHolder.logo_avatra.setVisibility(View.GONE);
                viewHolder.textTextView.setVisibility(View.GONE);
                viewHolder.photoImageView.setVisibility(View.GONE);
                viewHolder.faceImageView.setVisibility(View.GONE);

                if ((dataObj.optString("fileType").equals("video/mp4") || dataObj.optString("fileType").equals("video/quicktime")) && dataObj.optString("thumb") != null) {
                    String imageUrl = dataObj.optString("thumb");
                    Log.e("myurlVDO", imageUrl);
                    if (imageUrl != null && !imageUrl.equals(""))
                        Picasso.with(context).load(imageUrl).fit().centerCrop().into(viewHolder.logo_clip);
                } else {
                    // from local photo taken or picked
                    String uriStr = dataObj.optString("imageUriPhoto");
                    URI imageUri = null;
                    try {
                        imageUri = new URI(uriStr);
                        Bitmap myImg = BitmapFactory.decodeFile(imageUri.getPath());
                        if (imageUri.getPath() != null && !imageUri.getPath().equals(""))
                            Picasso.with(context).load(imageUri.getPath()).resize(400, 400).into(viewHolder.logo_clip);
                        //viewHolder.photoImageView.setImageBitmap(myImg);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
                if (m.getIsSender()) {
                    //sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.message_text);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                    LayoutParams layoutParams = (LayoutParams) viewHolder.failImageView.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.message_text);
                    if (m.getSendSuccess() != null && !m.getSendSuccess()) {
                        //viewHolder.failImageView.setVisibility(View.VISIBLE);
                        viewHolder.failImageView.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.failImageView.setVisibility(View.GONE);
                    }

                    if (m.getState() != null && m.getState() == 0) {
                        viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                        viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.sendingProgressBar.setVisibility(View.GONE);
                    }

                } else {
                    viewHolder.failImageView.setVisibility(View.GONE);
                    viewHolder.sendingProgressBar.setVisibility(View.GONE);

                    //sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.message_text);
                    //viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                }
                break;

            case 6:
                viewHolder.seconds.setVisibility(View.GONE);
                viewHolder.mAnimView.setVisibility(View.GONE);
                viewHolder.logo_clip.setVisibility(View.GONE);
                viewHolder.length.setVisibility(View.GONE);
                viewHolder.loction.setVisibility(View.GONE);
                viewHolder.sendDateTextView.setVisibility(View.GONE);
                viewHolder.txt_name.setVisibility(View.GONE);
                viewHolder.logo_avatra.setVisibility(View.GONE);
                viewHolder.textTextView.setVisibility(View.GONE);
                viewHolder.photoImageView.setVisibility(View.VISIBLE);
                viewHolder.faceImageView.setVisibility(View.GONE);

                if (dataObj != null) {
                    String lat = dataObj.optString("latitude");
                    String lon = dataObj.optString("longtitude");
                    String regionName = dataObj.optString("regionName");
                    String mapImage = "https://maps.googleapis.com/maps/api/staticmap?zoom=13&size=600x400&maptype=roadmap&markers=color:blue%7Clabel:" + regionName + "%7C" + lat + "," + lon;

                    Picasso.with(context)
                            .load(mapImage)
                            .fit().centerCrop()
                            .into(viewHolder.photoImageView);
                }

                if (m.getIsSender()) {
//                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.photoImageView);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                } else {
//                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.photoImageView);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                }

                if (m.getIsSender()) {
                    //sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.message_text);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                    LayoutParams layoutParams = (LayoutParams) viewHolder.failImageView.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.message_text);
                    if (m.getSendSuccess() != null && !m.getSendSuccess()) {
                        //viewHolder.failImageView.setVisibility(View.VISIBLE);
                        viewHolder.failImageView.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.failImageView.setVisibility(View.GONE);
                    }

                    if (m.getState() != null && m.getState() == 0) {
                        viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                        viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.sendingProgressBar.setVisibility(View.GONE);
                    }

                } else {
                    viewHolder.failImageView.setVisibility(View.GONE);
                    viewHolder.sendingProgressBar.setVisibility(View.GONE);

                    //sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.message_text);
                    //viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                }

                break;

            case 7:
                viewHolder.seconds.setVisibility(View.GONE);
                viewHolder.mAnimView.setVisibility(View.GONE);
                viewHolder.length.setVisibility(View.GONE);
                viewHolder.logo_clip.setVisibility(View.GONE);
                viewHolder.loction.setVisibility(View.VISIBLE);
                viewHolder.sendDateTextView.setVisibility(View.GONE);
                viewHolder.txt_name.setVisibility(View.VISIBLE);
                viewHolder.logo_avatra.setVisibility(View.VISIBLE);
                viewHolder.textTextView.setVisibility(View.GONE);
                viewHolder.photoImageView.setVisibility(View.GONE);
                viewHolder.faceImageView.setVisibility(View.GONE);

                if (dataObj != null) {
                    String name = dataObj.optString("name");
                    String photo = dataObj.optString("photo");
                    viewHolder.txt_name.setText(name);

                    Picasso.with(context).load(photo)
                            .centerCrop()
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(viewHolder.logo_avatra);

                }

                if (m.getIsSender()) {
                    //sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.message_text);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                    LayoutParams layoutParams = (LayoutParams) viewHolder.failImageView.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.message_text);
                    if (m.getSendSuccess() != null && !m.getSendSuccess()) {
                        //viewHolder.failImageView.setVisibility(View.VISIBLE);
                        viewHolder.failImageView.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.failImageView.setVisibility(View.GONE);
                    }

                    if (m.getState() != null && m.getState() == 0) {
                        viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                        viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.sendingProgressBar.setVisibility(View.GONE);
                    }

                } else {
                    viewHolder.failImageView.setVisibility(View.GONE);
                    viewHolder.sendingProgressBar.setVisibility(View.GONE);

                    //sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.message_text);
                    //viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                }
                break;

            case 8:
                viewHolder.seconds.setVisibility(View.VISIBLE);
                viewHolder.mAnimView.setVisibility(View.VISIBLE);
                viewHolder.length.setVisibility(View.VISIBLE);
                viewHolder.loction.setVisibility(View.GONE);
                viewHolder.sendDateTextView.setVisibility(View.GONE);
                viewHolder.txt_name.setVisibility(View.GONE);
                viewHolder.logo_avatra.setVisibility(View.GONE);
                viewHolder.textTextView.setVisibility(View.GONE);
                viewHolder.logo_clip.setVisibility(View.GONE);
                viewHolder.photoImageView.setVisibility(View.GONE);
                viewHolder.faceImageView.setVisibility(View.GONE);

                ViewGroup.LayoutParams lp = viewHolder.length.getLayoutParams();
                lp.width = (int) (mMinItemWidth + (mMaxItemWidth / 60f * m.getTimeVoice()));

                final String filePath = m.getFilePath();
                final float timeVoice = m.getTimeVoice();
                viewHolder.seconds.setText(timeVoice + "");
               // viewHolder.mAnimView.setBackgroundResource(R.drawable.play_anim);
                Log.e("filePathAdapter", filePath);
                Log.e("timeVoiceAdapter", filePath);
//                final AnimationDrawable anim = (AnimationDrawable)  viewHolder.mAnimView.getBackground();
//                viewHolder.length.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (mAnimView != null) {
//                            mAnimView.setBackgroundResource(R.drawable.adj);
//                            mAnimView = null;
//                        }
//                        //play video
//
//                        anim.start();
//                        //play audio
//                        MediaManager.playSound(filePath, new MediaPlayer.OnCompletionListener() {
//
//                            @Override
//                            public void onCompletion(MediaPlayer mp) {
//                               // mAnimView.setBackgroundResource(R.drawable.adj);
//
//                            }
//                        });
//                    }
//                });

                if (m.getIsSender()) {
                    //sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.message_text);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                    LayoutParams layoutParams = (LayoutParams) viewHolder.failImageView.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.message_text);
                    if (m.getSendSuccess() != null && !m.getSendSuccess()) {
                        //viewHolder.failImageView.setVisibility(View.VISIBLE);
                        viewHolder.failImageView.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.failImageView.setVisibility(View.GONE);
                    }

                    if (m.getState() != null && m.getState() == 0) {
                        viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                        viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                    } else {
                        viewHolder.sendingProgressBar.setVisibility(View.GONE);
                    }

                } else {
                    viewHolder.failImageView.setVisibility(View.GONE);
                    viewHolder.sendingProgressBar.setVisibility(View.GONE);

                    //sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.message_text);
                    //viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                }
                break;

            default:
                viewHolder.textTextView.setText(m.getContent());
                viewHolder.photoImageView.setVisibility(View.GONE);
                viewHolder.faceImageView.setVisibility(View.GONE);
                break;
        }

        viewHolder.textTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        return convertView;
    }


    public List<Message2> getData() {
        return mMessages;
    }

    public void setData(List<Message2> data) {
        this.mMessages = data;
    }


    public static boolean inSameDay(Date date1, Date Date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(Date2);
        int year2 = calendar.get(Calendar.YEAR);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);

        if ((year1 == year2) && (day1 == day2)) {
            return true;
        }
        return false;
    }


    static class ViewHolder {

        public ImageView userAvatarImageView;
        public TextView sendDateTextView;
        public TextView userNameTextView;

        public TextView textTextView;
        public ImageView photoImageView;
        public ImageView faceImageView;
        public ImageView failImageView;
        public TextView user_reply_status;
        public ImageView logo_avatra;
        public ImageView logo_clip;
        public TextView txt_name;
        public TextView sendTimeTextView;
        public ProgressBar sendingProgressBar;
        public LinearLayout loction;
        public RelativeLayout chat_item_layout_content;
        public TextView seconds;
        public View length;
        public View mAnimView;
        public boolean isSend = true;
    }


}
