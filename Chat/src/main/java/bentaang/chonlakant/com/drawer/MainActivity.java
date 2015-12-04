package bentaang.chonlakant.com.drawer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.norbsoft.typefacehelper.TypefaceCollection;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import bentaang.chonlakant.com.drawer.activity.ActivityVoice;
import bentaang.chonlakant.com.drawer.activity.ContactActivity;
import bentaang.chonlakant.com.drawer.activity.ViedoActivity;
import bentaang.chonlakant.com.drawer.adapter.AdapterRecyclerviewFont;
import bentaang.chonlakant.com.drawer.adapter.AdapterRecyclerviewFontTH;
import bentaang.chonlakant.com.drawer.adapter.HistoryFontAdapter;
import bentaang.chonlakant.com.drawer.adapter.MessageAdapter;
import bentaang.chonlakant.com.drawer.constant.Constant;
import bentaang.chonlakant.com.drawer.constant.HashMapTypeface;
import bentaang.chonlakant.com.drawer.constant.HashMapTypefaceTh;
import bentaang.chonlakant.com.drawer.emojiview.FaceCategroyAdapter;
import bentaang.chonlakant.com.drawer.emojiview.FunctionAdapter;
import bentaang.chonlakant.com.drawer.emojiview.OnOperationListener;
import bentaang.chonlakant.com.drawer.emojiview.Option;
import bentaang.chonlakant.com.drawer.emojiview.PagerSlidingTabStrip;
import bentaang.chonlakant.com.drawer.model.ChatMessage;
import bentaang.chonlakant.com.drawer.model.HistoryFont;
import bentaang.chonlakant.com.drawer.model.Message2;
import bentaang.chonlakant.com.drawer.presenter.MainPresenter;
import bentaang.chonlakant.com.drawer.upload.EndpointManager;
import bentaang.chonlakant.com.drawer.upload.FileUploadService;
import bentaang.chonlakant.com.drawer.upload.UploadCallback;
import bentaang.chonlakant.com.drawer.view.AudioRecorderButton;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;
import uz.shift.colorpicker.LineColorPicker;
import uz.shift.colorpicker.OnColorChangedListener;


import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;

public class MainActivity extends FragmentActivity implements HistoryListView {
    public static final String CHAT_SERVER = "https://chat.vdomax.com:1314";
    private MenuDrawer mDrawer;
    private ListView chatListView;
    private EditText chatEditText1;
    private ImageView enterChatView1;
    Dialog dialog;

    private OnOperationListener onOperationListener;
    private Map<Integer, ArrayList<String>> faceData;
    private List<Option> functionData;

    /**
     * face box
     **/
    private RelativeLayout bottomHideLayout;
    private static RelativeLayout faceLayout;
    private ViewPager faceCategroyViewPager;
    private PagerSlidingTabStrip faceCategroyTabs;

    /**
     * other function box
     **/
    private LinearLayout moreTypeLayout;
    private ViewPager fuctionViewPager;
    private LinearLayout pagePointLayout;
    List<View> functionGridViewList;
    List<ImageView> pointViews;

    //...

    FaceCategroyAdapter faceCategroyAdapter;

    private Context context;
    private FragmentManager fragmentManager;


    Button emojiButton;

    private PrefManager prefManager;
    private ImageView btn_reset;
    private TextView txt_preview;

    private RelativeLayout chat_layout;

    public List<String> fontList;
    public List<String> fontListTh;

    private Bitmap bitmap;

    private ImageView image_wallpaper;
    private ImageView enter_camera;

    private TextView txt_take_photo;
    private TextView txt_gallery;
    private TextView txt_video;
    private TextView txt_loction;
    private TextView txt_contact;
    private TextView txt_voice;

    public static int count = 0;
    private List<Message2> listMessages = new ArrayList<>();
    private MessageAdapter adapter;
    private LineColorPicker horizontalPicker;

    RelativeLayout chat_font, chat_attament;
    AdapterRecyclerviewFont mAdapter;
    AdapterRecyclerviewFontTH mAdapterTH;
    TextView txt_history;
    public HashMap<String, TypefaceCollection> mTypefaceMap;
    public HashMap<String, TypefaceCollection> mTypefaceMapTh;


    boolean isCheckColor;
    boolean isCheckAtt;
    private RecyclerView rvContacts;
    private ToggleButton mBtnItalic;
    private ToggleButton mBtnBold;
    private Uri mFileURI = null;

    HashMapTypeface hashMapTypeface;
    HashMapTypefaceTh hashMapTypefaceTH;

    List<HistoryFont> listFont = new ArrayList<>();
    private String mName = "OKOK";
    private String mUsername = "sdsd";
    private String mAvatarUrl = "https://lh3.googleusercontent.com/Q_DqZvzMKQiYAfcRLYznNHwNv0NeYTM_-L14liuM98mm_R3T9-KwvTUQhM86CuC6DjI=w300";
    ;


    private String mPartnerName = "aaa";
    private String mPartnerUsername = "ddd";
    private String mPartnerAvatarUrl = "https://lh3.googleusercontent.com/Q_DqZvzMKQiYAfcRLYznNHwNv0NeYTM_-L14liuM98mm_R3T9-KwvTUQhM86CuC6DjI=w300";

    HistoryFontAdapter mAdapterFont;
    private Integer mCid;
    public boolean isConnected = false;
    FunctionAdapter functionAdapter;

    private Integer mChatType; // 0 = 1-1 chat, 1 = public group chat, 2 = private group chat
    private Integer mUserId;
    private Integer mPartnerId;
    String conten;
    public static String jsonObjStr;
    private String socketUrl = CHAT_SERVER;
    private Socket mSocket;

    {
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
            sc.init(null, null, null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        IO.setDefaultSSLContext(sc);
        IO.Options opts = new IO.Options();
        opts.secure = true;
        opts.sslContext = sc;

        try {
            mSocket = IO.socket(socketUrl);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    String nameFont;
    String typeColor = "000000";
    String dir;

    Toolbar toolbar;
    MainPresenter mainPresenter;


    String namFontHistory;
    int colorFontHistory;
    TextView txt_en;
    TextView txt_th;
    AudioRecorderButton mAudioRecorderButton;

    private View mAnimView;

    String pathTest;
    float timeTest;
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        mDrawer = MenuDrawer.attach(this, Position.RIGHT);
        mDrawer.setContentView(R.layout.activity_main_chat);
        mDrawer.setMenuView(R.layout.layout_more_chat);
        mDrawer.setMenuSize(150);
        image_wallpaper = (ImageView) findViewById(R.id.image_wallpaper);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_history = (TextView) findViewById(R.id.txt_history);
        txt_en = (TextView) findViewById(R.id.txt_en);
        txt_th = (TextView) findViewById(R.id.txt_th);
        enter_camera = (ImageView) findViewById(R.id.enter_camera);
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_recorder_button);
        mAudioRecorderButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(float seconds, String filePath) {
                // TODO Auto-generated method stub
//                Recorder recorder=new Recorder(seconds, filePath);
//                mDatas.add(recorder);
//                mAdapter.notifyDataSetChanged();
//                mListView.setSelection(mDatas.size()-1);
                pathTest = filePath;
                timeTest = seconds;
                Log.e("seconds", seconds + "");
                Log.e("filePathVoice", filePath);
                if (filePath != null) {
                    String dataJson = "";
                        dataJson = "{'thumb':'" + filePath + "'}";
                    Message2 sendingMessage = new Message2(Message2.MSG_TYPE_VOICE,
                            Message2.MSG_STATE_SENDING, "",
                            mAvatarUrl, "", "", "",
                            dataJson, true, false,
                            new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), typeColor, filePath, null, seconds, filePath);
                    listMessages.add(sendingMessage);

                    File clipVoice = new File(filePath);
                    Log.e("Voice", clipVoice + "");
                    uploadFileRetrofit(clipVoice, Message2.MSG_TYPE_VOICE, sendingMessage);
                }

            }
        });


        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadData();
        toolbar.inflateMenu(R.menu.menu_main_chat);//changed
        //toolbar2 menu items CallBack listener
        toolbar.setTitle("WOUchat");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_attach) {
                    onClick3();
                    return true;
                } else if (item.getItemId() == R.id.action_color) {
                    onClick();

                    return true;
                } else if (item.getItemId() == R.id.context_menu) {
                    mDrawer.toggleMenu();
                    return true;
                } else {

                }

                return false;
            }
        });


        context = getApplicationContext();
        mUserId = getIntent().getIntExtra("USER_ID_1", 1);
        mPartnerId = getIntent().getIntExtra("USER_ID_2", 6);
        mCid = getIntent().getIntExtra("CONVERSATION_ID", 2);
        mChatType = getIntent().getIntExtra("CHAT_TYPE", 0);

        mAvatarUrl = "http://th-bigbike.com/wp-content/uploads/2014/12/Suzuki-GSX-R750-4-290x195.jpg";
        mName = "Test1";
        mUsername = "Test1";
        mPartnerName = "Test2";
        mPartnerUsername = "Test2";
        mPartnerAvatarUrl = "";
        chat_layout = (RelativeLayout) findViewById(R.id.chat_layout);
        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        initConnect();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false);
        rvContacts.setLayoutManager(layoutManager);
        chatListView = (ListView) findViewById(R.id.chat_list_view);
        adapter = new MessageAdapter(getApplicationContext(), listMessages);
        chatListView.setAdapter(adapter);

        bottomHideLayout = (RelativeLayout) findViewById(R.id.bottomHideLayout);
        moreTypeLayout = (LinearLayout) findViewById(R.id.moreTypeLayout);
        faceLayout = (RelativeLayout) findViewById(R.id.faceLayout);
        faceCategroyViewPager = (ViewPager) findViewById(R.id.faceCategroyViewPager);
        faceCategroyTabs = (PagerSlidingTabStrip) findViewById(R.id.faceCategroyTabs);


        fuctionViewPager = (ViewPager) findViewById(R.id.fuctionViewPager);
        pagePointLayout = (LinearLayout) findViewById(R.id.pagePointLayout);

        chat_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                chat_attament.setVisibility(View.GONE);
                isCheckAtt = false;

                chat_font.setVisibility(View.GONE);
                isCheckColor = false;
            }
        });

        fuctionViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                for (int i = 0; i < pointViews.size(); i++) {
                    if (arg0 == i) {
                        pointViews.get(i).setBackgroundResource(R.drawable.point_selected);
                    } else {
                        pointViews.get(i).setBackgroundResource(R.drawable.point_normal);
                    }
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        fragmentManager = getSupportFragmentManager();

        faceCategroyAdapter = new FaceCategroyAdapter(fragmentManager);


        txt_take_photo = (TextView) findViewById(R.id.txt_take_photo);
        txt_gallery = (TextView) findViewById(R.id.txt_gallery);
        txt_video = (TextView) findViewById(R.id.txt_video);
        txt_loction = (TextView) findViewById(R.id.txt_loction);
        txt_contact = (TextView) findViewById(R.id.txt_contact);
        txt_voice = (TextView) findViewById(R.id.txt_voice);


//        Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SWZ721BR.ttf");
//        SpannableStringBuilder SS = new SpannableStringBuilder("CandyChat");
//        SS.setSpan(new CustomTypefaceSpan("", font2), 0, SS.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
//        getActivity().getSupportActionBar();


        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();
        emojiButton = (Button) findViewById(R.id.emojiButton);
        btn_reset = (ImageView) findViewById(R.id.btn_reset);
        prefManager = MainApplication.getPrefManager();
        txt_preview = (TextView) findViewById(R.id.txt_preview);
        chat_font = (RelativeLayout) findViewById(R.id.chat_font);
        chat_attament = (RelativeLayout) findViewById(R.id.chat_attament);

        emojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (faceLayout.getVisibility() == View.VISIBLE) {
                    hideFaceLayout();
                } else {
                    showFaceLayout();
                }
            }
        });
        chatListView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        chatEditText1 = (EditText) findViewById(R.id.chat_edit_text1);
        enterChatView1 = (ImageView) findViewById(R.id.enter_chat1);


        enterChatView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conten = chatEditText1.getText().toString();
                Message2 message = new Message2(Message2.MSG_TYPE_TEXT, Message2.MSG_STATE_SUCCESS, mName, mAvatarUrl, "", "", conten, "{}", true, true, new Date(), typeColor, nameFont, null, 0, "");
                adapter.getData().add(message);
                chatListView.setSelection(chatListView.getBottom());

                attemptSendMessageToServer(Message2.MSG_TYPE_TEXT, conten, "{}");
                chat_font.setVisibility(View.GONE);
                chat_attament.setVisibility(View.GONE);
                chatEditText1.setText("");
                isCheckColor = false;
                isCheckAtt = false;
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
        txt_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takePhoto();
                isCheckColor = false;
                isCheckAtt = false;
                chat_attament.setVisibility(View.GONE);
            }
        });

        txt_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pickPhoto();
                isCheckColor = false;
                isCheckAtt = false;
                chat_attament.setVisibility(View.GONE);

            }
        });

        txt_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildVideoDialog();
                isCheckColor = false;
                isCheckAtt = false;
            }
        });

        txt_loction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                statusCheck();
//                LocationPickerIntent intent6 = new LocationPickerIntent(getActivity());
//                getActivity().startActivityForResult(intent6, 500);
//                isCheckColor = false;
//                isCheckAtt = false;
//                chat_attament.setVisibility(View.GONE);
            }
        });
        txt_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                startActivityForResult(intent, 700);
                isCheckColor = false;
                isCheckAtt = false;
                chat_attament.setVisibility(View.GONE);
            }
        });
        txt_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityVoice.class);
                startActivityForResult(intent, 800);
                isCheckColor = false;
                isCheckAtt = false;
                chat_attament.setVisibility(View.GONE);

            }
        });

        horizontalPicker = (LineColorPicker) findViewById(R.id.picker);


        // Create palette from HEX values
        int[] colors = new int[Constant.pallete.length];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.parseColor(Constant.pallete[i]);
        }

        // Set palette
        horizontalPicker.setColors(colors);

        // Set selected color [optional]
        horizontalPicker.setSelectedColor(colors[0]);

        // Get selected color
        final int color = horizontalPicker.getColor();

        updateColor(color);

        OnColorChangedListener onChangeListener = new OnColorChangedListener() {

            @Override
            public void onColorChanged(int c) {

                typeColor = Integer.toHexString(c);
                typeColor = typeColor.toUpperCase();
                Log.e("Selected color ", typeColor + "");
                prefManager.intColor().put(c);
                prefManager.commit();
                chatEditText1.setTextColor(c);
                txt_preview.setTextColor(c);
                updateColor(c);
            }
        };

        horizontalPicker.setOnColorChangedListener(onChangeListener);
        mBtnItalic = (ToggleButton) findViewById(R.id.btn_italic);
        mBtnBold = (ToggleButton) findViewById(R.id.btn_bold);

        //FontList
        hashMapTypeface = new HashMapTypeface();
        hashMapTypefaceTH = new HashMapTypefaceTh();

//        mTypefaceMapTh = hashMapTypefaceTH.getTypeFace(getApplicationContext());
//        mTypefaceMap = hashMapTypeface.getTypeFace(getApplicationContext());
//
//        fontList = new ArrayList<String>(mTypefaceMap.size());
//        fontListTh = new ArrayList<String>(mTypefaceMapTh.size());
//
//        fontList.addAll(mTypefaceMap.keySet());
//        fontListTh.addAll(mTypefaceMapTh.keySet());
//
//
//        mAdapter = new AdapterRecyclerviewFont(getApplicationContext(), fontList);
//        rvContacts.setAdapter(mAdapter);
//
//        mAdapter.SetOnItemVideiosClickListener(new AdapterRecyclerviewFont.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
//                applyDynamicTypeface(fontList.get(position), mBtnBold.isChecked(), mBtnItalic.isChecked());
//                Log.e("font55", fontList.get(position) + "");
//                nameFont = fontList.get(position);
//                prefManager.font().put(fontList.get(position));
//                prefManager.numTh().put(0);
//                prefManager.commit();
//
//            }
//        });

//        txt_en.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAdapter = new AdapterRecyclerviewFont(getApplicationContext(), fontList);
//                rvContacts.setAdapter(mAdapter);
//                mAdapter.SetOnItemVideiosClickListener(new AdapterRecyclerviewFont.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
//                        applyDynamicTypeface(fontList.get(position), mBtnBold.isChecked(), mBtnItalic.isChecked());
//                        Log.e("font55", fontList.get(position) + "");
//                        nameFont = fontList.get(position);
//                        prefManager.font().put(fontList.get(position));
//                        prefManager.numTh().put(0);
//                        prefManager.commit();
//
//                    }
//                });
//            }
//        });
//        txt_th.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAdapterTH = new AdapterRecyclerviewFontTH(getApplicationContext(), fontListTh);
//                rvContacts.setAdapter(mAdapterTH);
//                mAdapterTH.SetOnItemVideiosClickListenerTh(new AdapterRecyclerviewFontTH.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
//                        applyDynamicTypefaceTh(fontListTh.get(position), mBtnBold.isChecked(), mBtnItalic.isChecked());
//                        Log.e("font55", fontListTh.get(position) + "");
//                        nameFont = fontListTh.get(position);
//                        prefManager.font().put(fontListTh.get(position));
//                        prefManager.numEn().put(1);
//                        prefManager.commit();
//                    }
//                });
//            }
//        });


        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Typeface   myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/supermarket/supermarket.ttf");
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-R.ttf");
                txt_preview.setTextColor(Color.BLACK);
                txt_preview.setTypeface(typeface);
                chatEditText1.setTextColor(Color.BLACK);
                chatEditText1.setTypeface(typeface);
                typeColor = "000000";
                nameFont = "System default";

            }
        });

        chatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Message2 m = listMessages.get(i);
                int msgType = m.getType();
                Log.e("msgTypeClicked", msgType + "");
                String jsonDataStr = m.getData();
                Log.e("clickedJsonData", jsonDataStr);
                JSONObject dataObj = null;
                try {
                    dataObj = new JSONObject(jsonDataStr);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                switch (msgType) {
                    case 0:
                        break;
                    case 1:

                        break;
                    case 2:

                        String fileType = dataObj.optString("fileType");

                        if (fileType.equals("image/jpeg") || fileType.equals("image/png")) {
//                            Intent intent = new Intent(getActivity(), PhotoPagerActivity.class);
//
//                            ArrayList<String> urls = new ArrayList<>();
//                            String imageUrl = dataObj.optString("url");
//                            urls.add(0, imageUrl);
//
//                            intent.putExtra("current_item", 1);
//                            intent.putStringArrayListExtra("photos", urls);
//
//                            getActivity().startActivity(intent);
                        }

                        break;
                    case 3:
                        // intent video player
                        String clipPath = dataObj.optString("url");

                        Intent ii = new Intent(getApplicationContext(), ViedoActivity.class);
                        ii.putExtra("videoPath", clipPath);
                        startActivity(ii);
                        break;
                    case 6:
                        String lat = dataObj.optString("latitude");
                        String lon = dataObj.optString("longtitude");
                        viewLocation(lat, lon);
                        break;

                    case 8:

                        if(mAnimView!=null)
                        {
                            mAnimView.setBackgroundResource(R.drawable.adj);
                            mAnimView=null;
                        }
                        //play video
                        mAnimView=view.findViewById(R.id.id_recorder_anim);
                        mAnimView.setBackgroundResource(R.drawable.play_anim);
                        AnimationDrawable anim=(AnimationDrawable)mAnimView.getBackground();
                        anim.start();
                       //Log.e("pathTest",pathTest);
                        //play audio
                        MediaManager.playSound(pathTest,new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mAnimView.setBackgroundResource(R.drawable.adj);

                            }
                        });
                        break;
                }
            }
        });

        faceCategroyAdapter.setOnOperationListener(new bentaang.chonlakant.com.drawer.emojiview.OnOperationListener() {
            @Override
            public void send(String content) {

            }

            @Override
            public void selectedFace(String content) {
                System.out.println("===============" + content);
                String jsonObjStr = "{'tattooUrl':'" + content + "'}";
                System.out.println("===============" + jsonObjStr);
                Message2 message = new Message2(Message2.MSG_TYPE_FACE, Message2.MSG_STATE_SUCCESS, mName, mAvatarUrl, "", "", content, jsonObjStr, true, true, new Date(), typeColor, nameFont, null, 0, "");
                adapter.getData().add(message);
                chatListView.setSelection(chatListView.getBottom());

                attemptSendMessageToServer(Message2.MSG_TYPE_FACE, content, jsonObjStr);
                hideFaceLayout();
            }

            @Override
            public void selectedFuncation(int index) {

            }
        });

        String prefix = "https://www.vdomax.com/";
        String suffix = "";

        ArrayList<String> faceNameList = new ArrayList<String>();
        for (int x = 1; x <= 10; x++) {
            faceNameList.add("big" + x);
        }
        for (int x = 1; x <= 10; x++) {
            faceNameList.add("big" + x);
        }

        ArrayList<String> faceNameList1 = new ArrayList<String>();
        for (int x = 1; x <= 7; x++) {
            faceNameList1.add("cig" + x);
        }


        ArrayList<String> faceNameList2 = new ArrayList<String>();
        for (int x = 1; x <= 24; x++) {
            faceNameList2.add("dig" + x);
        }
        ArrayList<String> faceNameList3 = new ArrayList<String>();
        for (int x = 1; x <= 10; x++) {
            faceNameList3.add("candy" + x);
        }
        ArrayList<String> faceNameList4 = new ArrayList<String>();
        for (int x = 1; x <= 10; x++) {
            faceNameList4.add("candyor" + x);
        }


        Map<Integer, ArrayList<String>> faceData = new HashMap<Integer, ArrayList<String>>();
        faceData.put(R.drawable.em_cate_magic, faceNameList2);
        faceData.put(R.drawable.em_cate_rib, faceNameList1);
        faceData.put(R.drawable.em_cate_duck, faceNameList);
        faceData.put(R.drawable.em_cate_candy_chat_or, faceNameList4);
        faceData.put(R.drawable.em_cate_candy_chat, faceNameList3);
        setFaceData(faceData);

//        namFontHistory = prefManager.font().getOr("no");
//        colorFontHistory = prefManager.intColor().getOr(000000);

//        Log.e("namFontHistory", namFontHistory);
//        Log.e("colorFontHistory", "" + colorFontHistory);

//        HistoryFont itemFont = new HistoryFont();
//        itemFont.setColor(colorFontHistory);
//        itemFont.setNamFont(namFontHistory);
//        listFont.add(itemFont);
//        mAdapterFont = new HistoryFontAdapter(getApplicationContext(), listFont);

        txt_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getActivity(), R.style.FullHeightDialog);
                dialog.setContentView(R.layout.dialog_history);

//                ListView listviewFont = (ListView) dialog.findViewById(R.id.listView);
//                listviewFont.setAdapter(mAdapterFont);

//                listviewFont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        int color = listFont.get(position).getColor();
//                        String namFontHistory = listFont.get(position).getNamFont();
//
//                        nameFont = namFontHistory;
//                        typeColor = Integer.toString(color);
//                        txt_preview.setTextColor(color);
//                        chatEditText1.setTextColor(color);
//                        // chatEditText1.setTypeface(mTypefaceMap.get(selectedFont));
//
//                        dialog.dismiss();
//                    }
//                });

                dialog.show();
            }
        });

        enter_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takePhoto();
            }
        });

        chatEditText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != null && !"".equals(s.toString().trim())){
                    enter_camera.setVisibility(View.GONE);
                    enterChatView1.setEnabled(true);
                    enterChatView1.setVisibility(View.VISIBLE);
                }else{
                    enter_camera.setVisibility(View.VISIBLE);
                    if(enter_camera.getVisibility() == View.VISIBLE){
                        enterChatView1.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    public void pickPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        // intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", Constant.PHOTO_SIZE_WIDTH);
        intent.putExtra("outputY", Constant.PHOTO_SIZE_HEIGHT);
        startActivityForResult(intent, Constant.SELECT_PHOTO);
    }

    public void pickVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(intent, Constant.RESULT_PICK_VIDEO);
    }

    public void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, Constant.RESULT_VIDEO_CAP);
    }

    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        // intent.putExtra("crop", "true");
        startActivityForResult(intent, Constant.CAMERA_REQUEST);
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }

    public void viewLocation(String lat, String lon) {
        String uri = MessageFormat.format("geo:{0},{1}?q={0},{1}", lat, lon);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(Intent.createChooser(intent, getResources().getText(R.string.view_via)));
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private Activity getActivity() {
        return this;
    }


    private void doColorSpanForFirstString(String firstString, TextView txtSpan) {
        String changeString = (firstString != null ? firstString : "");
        //        String totalString = " has accepted your friend request. You have received 100 chips";
        String totalString = changeString;
        Spannable spanText = new SpannableString(totalString);
        spanText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.orange)), 0, changeString.length(), 0);
        txtSpan.setText(spanText);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected void onPause() {
        super.onPause();

        //  hideEmojiPopup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_chat, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_attach) {
            onClick3();
            return true;
        } else if (item.getItemId() == R.id.action_color) {
            onClick();
            return true;
        } else if (item.getItemId() == R.id.context_menu) {
            mDrawer.toggleMenu();
            return true;
        } else {

        }
        return super.onOptionsItemSelected(item);


    }

    public void onClick() {
        if (isCheckColor != true) {
            chat_font.setVisibility(View.VISIBLE);
            isCheckColor = true;
        } else {
            chat_font.setVisibility(View.GONE);
            isCheckColor = false;
        }
        if (isCheckAtt == true) {
            chat_attament.setVisibility(View.GONE);
            isCheckAtt = false;
        }

    }

    public void onClick3() {
        if (isCheckAtt != true) {
            chat_attament.setVisibility(View.VISIBLE);
            isCheckAtt = true;
        } else {

            chat_attament.setVisibility(View.GONE);
            isCheckAtt = false;
        }
        if (isCheckColor == true) {
            chat_font.setVisibility(View.GONE);
            isCheckColor = false;
        }


    }

    public void applyDynamicTypeface(String selectedFont, boolean flgBold, boolean flgItalic) {

        // Std typeface style set for ordinary textview
        chatEditText1.setTypeface(null, getTypefaceStyle(flgBold, flgItalic));


        int numTH = prefManager.numTh().getOr(1);
        int numEn = prefManager.numEn().getOr(0);
        Log.e("numTH", numTH + "");


        // Apply custom typeface
        typeface(chatEditText1, mTypefaceMap.get(selectedFont));
        typeface(txt_preview, mTypefaceMap.get(selectedFont));


    }

    public void applyDynamicTypefaceTh(String selectedFont, boolean flgBold, boolean flgItalic) {

        // Std typeface style set for ordinary textview
        chatEditText1.setTypeface(null, getTypefaceStyle(flgBold, flgItalic));


        int numTH = prefManager.numTh().getOr(1);
        int numEn = prefManager.numEn().getOr(0);
        Log.e("numTH", numTH + "");


        // Apply custom typeface
        typeface(chatEditText1, mTypefaceMapTh.get(selectedFont));
        typeface(txt_preview, mTypefaceMapTh.get(selectedFont));


    }

    private int getTypefaceStyle(boolean flgBold, boolean flgItalic) {
        if (flgBold && flgItalic) {
            return Typeface.BOLD_ITALIC;
        } else if (flgBold) {
            return Typeface.BOLD;
        } else if (flgItalic) {
            return Typeface.ITALIC;
        } else {
            return Typeface.NORMAL;
        }
    }


    /**
     * Display selected color
     */
    private void updateColor(int color) {
        String hex = Integer.toHexString(color);

        hex = hex.toUpperCase();

    }


    private void initConnect() {
        if (!isConnected) {
            mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    //mSocket.emit("OnlineUser");
                    JSONObject jObj = new JSONObject();
                    try {
                        jObj.put("userId", mUserId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mSocket.emit("Authenticate", jObj);
                    //addUser(mUsername); //username
                }
            });
        }


        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.on("Authenticate:Success", onAuthSuccess);
        mSocket.on("Authenticate:Failure", onAuthFailure);
        mSocket.on("JoinRoomSuccess", onUserJoined);
        //mSocket.on("JoinRoomFailure", null);

        mSocket.on("SendMessage", onSendMessage);
        mSocket.on("LeaveRoom", onUserLeft);
        //mSocket.on("Typing", onTyping);
        //mSocket.on("StopTyping", onStopTyping);
        //mSocket.on("Read",null);
        //mSocket.on("login" , onLogin);
        mSocket.on("OnlineUser", onOnlineUser);
        mSocket.connect();
        Log.e("สำเร็จ", "สำเร็จ");
    }

    private void attemptSendMessageToServer(int messageType, String theMessage, String jsonObjStr) {
        if (null == mUsername) return;
        if (!mSocket.connected()) return;

        //mTyping = false;

        String message = theMessage;
        JSONObject jObj = new JSONObject();
        JSONObject jObj2 = null;
        try {
            jObj2 = new JSONObject(jsonObjStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject senderObj = new JSONObject();
        JSONObject jo = new JSONObject();
        JSONObject jo2 = new JSONObject();
        JSONArray arr = new JSONArray();
        Log.e("SendMessage", "is sending");

        try {

            senderObj.put("senderId", mUserId + "");
            senderObj.put("id", mUserId);
            senderObj.put("username", mUsername);
            senderObj.put("name", mName);
            senderObj.put("avatarUrl", mAvatarUrl);

            jo2.put("message", message);
            jo.put("size", "4");
            if (typeColor != null) {
                jo.put("color", typeColor);

            } else {
                typeColor = "000000";
                jo.put("color", typeColor);
            }

            if (nameFont != null) {
                jo.put("style", nameFont);
            } else {
                nameFont = "System default";
                jo.put("style", nameFont);
            }

            jo2.put("style", jo);
            arr.put(jo2);

            String avatar = null;
            //String[] parts = avatar.split(".");
            //senderObj.put("avatar", parts[0]);
            //senderObj.put("extension", parts[1]);


            if (messageType == 1) {
                jObj2.put("tattooUrl", message);
                jObj.put("message", "");
            } else {
                jObj.put("message", message);

            }


            jObj.put("senderId", mUserId + "");
            int messageRoomType = 0;
            if (mChatType == 1)
                messageRoomType = 2;
            else
                messageRoomType = 1;

            if (mChatType == 0)
                messageRoomType = mChatType;

            jObj.put("messageRoomType", messageRoomType);
            jObj.put("conversationId", mCid);
            jObj.put("messageType", messageType);
            jObj.put("data", jObj2);
            jObj.put("sender", senderObj);
            jObj.put("messageColorful", arr);
            //jObj.put("createdAt",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

            Log.e("SendMessage", jObj.toString(4));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        mSocket.emit("SendMessage", jObj);
        Log.e("SendMessage", "sent");
    }

    public void interpretMessage(final JSONObject obj) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject data = obj;
                JSONObject sender;
                JSONArray ar;
                JSONObject styleOb;
                String username;
                String message;
                String dataJson;
                int messageType;
                int senderId;
                String color = null;
                String style = null;
                String senderName;
                String senderAvatar;


                try {
                    //data.getString("time");
                    Log.e("JSON RECEIVED:", data.toString(4));
                    username = mUsername;
                    senderId = data.optInt("senderId");
                    message = data.optString("message");
                    messageType = data.optInt("messageType");
                    dataJson = data.optString("data");


                    //if(data.optJSONObject("sender") != null ) {
                    //  sender = data.optJSONObject("sender");
                    ar = data.getJSONArray("messageColorful");
                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject o = ar.getJSONObject(i);
                        JSONObject st = o.getJSONObject("style");
                        color = st.optString("color");
                        style = st.optString("style");
                        Log.e("color", color);
                        Log.e("style", style);
                    }
                    Log.e("12345", ar.toString());

                    senderName = data.optString("senderName");
                    senderAvatar = EndpointManager.prefix + "/" + data.optString("senderAvatar") + "." + data.optString("senderExtension");
                    Log.e("avatarUrl", senderAvatar);


                    //} else {
                    //  senderName = mName;
                    //senderAvatar = mAvatarUrl;
                    //}


                    if (messageType != 0) {
                        //message = message.concat("(" + data.optJSONObject("data").toString(4) + ")");
                    }

                    if (mUserId != senderId) {
                        Message2 msgObj = new Message2(messageType, Message2.MSG_STATE_SUCCESS, "", senderAvatar, "", "", message, dataJson, false, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), color, style, null, 0, "");
                        listMessages.add(msgObj);
                        adapter.notifyDataSetChanged();
                        chatListView.setSelection(chatListView.getBottom());
                    }

                } catch (JSONException e) {
                    return;
                }
                //removeTyping(username);
                //addMessage(messageType,senderId,username, message,dataJson);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", requestCode + " + " + resultCode);
        if (resultCode == Activity.RESULT_OK) {

            File f = new File(Environment.getExternalStorageDirectory()
                    .toString());
            for (File temp : f.listFiles()) {
                if (temp.getName().equals("temp.jpg")) {
                    f = temp;
                    break;
                }
            }

            if (requestCode == Constant.CAMERA_REQUEST && resultCode == RESULT_OK) {

                String path = f.getAbsolutePath();
                Uri selectedImageUri = Uri.parse(path);
                File file = imagePathToFile(selectedImageUri, path);
                String dataJson = "{}";
                Message2 sendingMessage = new Message2(Message2.MSG_TYPE_PHOTO,
                        Message2.MSG_STATE_SENDING,
                        "", mAvatarUrl,
                        "", "", "",
                        dataJson,
                        true,
                        false,
                        new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), typeColor, nameFont, null, 0, "");

                listMessages.add(sendingMessage);
                //uploadFile(file);
                uploadFileRetrofit(file, Message2.MSG_TYPE_PHOTO, sendingMessage);

            } else if (requestCode == Constant.SELECT_PHOTO && resultCode == RESULT_OK && null != data) {
                Uri selectedImageUri = data.getData();

                Log.e("selectedImageUri", selectedImageUri + "");

                String path = getRealPath(getApplicationContext(), selectedImageUri);
                File file = imagePathToFile(selectedImageUri, path);

                Log.e("file", file + "");


                String dataJson = "{}";
                Message2 sendingMessage = new Message2(Message2.MSG_TYPE_PHOTO,
                        Message2.MSG_STATE_SENDING, "", mAvatarUrl, "", "", "",
                        dataJson, true, false,
                        new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), typeColor, nameFont, null, 0, "");

                listMessages.add(sendingMessage);

                //uploadFile(file);
                uploadFileRetrofit(file, Message2.MSG_TYPE_PHOTO, sendingMessage);


            } else if (requestCode == Constant.RESULT_PICK_VIDEO) {

                mFileURI = data.getData();
                Log.e("mFileURI", mFileURI + "");
                if (mFileURI != null) {
                    String vdoThumb = ChatUtil.getThumbnailPathForLocalFile(getActivity(), mFileURI);
                    String dataJson = "";
                    if (vdoThumb != null)
                        dataJson = "{'thumb':'" + vdoThumb + "'}";

                    Message2 sendingMessage = new Message2(Constant.RESULT_PICK_VIDEO,
                            Message2.MSG_STATE_SENDING, "",
                            mAvatarUrl, "", "", "",
                            dataJson, true, false,
                            new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), typeColor, nameFont, null, 0, "");
                    listMessages.add(sendingMessage);

                    String path = ChatUtil.getRealPathFromURIVideo(getActivity(), mFileURI);
                    File clip = new File(path);
                    Log.e("Clip", clip + "");
                    uploadFileRetrofit(clip, Message2.MSG_TYPE_CLIP, sendingMessage);

                }

            } else if (requestCode == Constant.RESULT_VIDEO_CAP) {

                mFileURI = data.getData();
                Log.e("mFileURI", mFileURI + "");

                if (mFileURI != null) {
                    String vdoThumb = ChatUtil.getThumbnailPathForLocalFile(getActivity(), mFileURI);
                    String dataJson = "";
                    if (vdoThumb != null)
                        dataJson = "{'thumb':'" + vdoThumb + "'}";

                    Message2 sendingMessage = new Message2(Constant.RESULT_PICK_VIDEO,
                            Message2.MSG_STATE_SENDING, "", mAvatarUrl, "", "", "",
                            dataJson,
                            true, false, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), typeColor, nameFont, null, 0, "");
                    listMessages.add(sendingMessage);

                    String path = ChatUtil.getRealPathFromURIVideo(getActivity(), mFileURI);
                    File clip = new File(path);
                    uploadFileRetrofit(clip, Message2.MSG_TYPE_CLIP, sendingMessage);


                }

            } else if (requestCode == 500) {

                String lat = data.getStringExtra("LAT");
                String lon = data.getStringExtra("LON");
                String locationName = data.getStringExtra("LOCATION_NAME");
                //Location location = data.getParcelableExtra("LOCATION");

                String mapImage = "https://maps.googleapis.com/maps/api/staticmap?zoom=13&size=600x400&maptype=roadmap&markers=color:blue%7Clabel:" + locationName + "%7C" + lat + "," + lon;

                //{"cityName":"??????","regionName":"??????","latitude":"13.837663","longtitude":"100.616730"}

                String dataJson = "{'cityName':'" + locationName + "'" +
                        ",'imageUrl':'" + mapImage + "'" +
                        ",'regionName':'" + locationName + "'" +
                        ",'latitude':'" + lat + "'" +
                        ",'longtitude':'" + lon + "'}";

                Log.e("sendDataJson", dataJson);


                Message2 message = new Message2(Message2.MSG_TYPE_LOCATION, Message2.MSG_STATE_SUCCESS, "", "https://www.vdomax.com/photos/2015/04/pr8af_108899_c04356ab5e9726bb6e650e5b9cc17cbc_thumb.jpg", "", "", "", dataJson, true, false, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), typeColor, nameFont, null, 0, "");
                listMessages.add(message);

                attemptSendMessageToServer(Message2.MSG_TYPE_LOCATION, "", dataJson);

            } else if (requestCode == 700) {

                String name = data.getStringExtra("data");
                Log.e("มาไหม", name);

                //{"cityName":"??????","regionName":"??????","latitude":"13.837663","longtitude":"100.616730"}

                String dataJson = "{'userid':'" + "" + "'" +
                        ",'name':'" + name + "'" +
                        ",'last':'" + "" + "'" +
                        ",'photo':'" + "http://image.dek-d.com/27/0287/9085/114304545" + "'" +
                        ",'fullname':'" + "" + "'}";

                Log.e("sendDataJson", dataJson);


                Message2 message = new Message2(Message2.MSG_TYPE_CONTACT, Message2.MSG_STATE_SUCCESS, "", "https://www.vdomax.com/photos/2015/04/pr8af_108899_c04356ab5e9726bb6e650e5b9cc17cbc_thumb.jpg", "", ""
                        , "", dataJson, true, false, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8)
                        , typeColor, nameFont, null, 0, "");
                listMessages.add(message);

                attemptSendMessageToServer(Message2.MSG_TYPE_CONTACT, "", dataJson);
            }
            //
            else if (requestCode == 800) {
                String name = data.getStringExtra("data");
                Log.e("มานะๆ", name);

                if (name != null) {
                    String dataJson = "";
                    if (name != null)
                        dataJson = "{'thumb':'" + name + "'}";

                    Message2 sendingMessage = new Message2(Message2.MSG_TYPE_VOICE,
                            Message2.MSG_STATE_SENDING, "",
                            mAvatarUrl, "", "", "",
                            dataJson, true, false,
                            new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), typeColor, name, null, 0, "");
                    listMessages.add(sendingMessage);

                    File clip = new File(name);
                    Log.e("Voice", clip + "");
                    uploadFileRetrofit(clip, Message2.MSG_TYPE_VOICE, sendingMessage);
                }

            }

            adapter.notifyDataSetChanged();
        }
        chatListView.setSelection(chatListView.getBottom());
    }


    private void sendMessage(final Message2 chatMessage) {

        listMessages.add(chatMessage);


        if (adapter != null)
            adapter.notifyDataSetChanged();

    }


    public void buildVideoDialog() {
        final CharSequence[] items = {"Record Video", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Record Video")) {
                    recordVideo();
                    chat_attament.setVisibility(View.GONE);
                } else if (items[item].equals("Choose from Library")) {
                    pickVideo();
                    chat_attament.setVisibility(View.GONE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    // EMITTER GROUP
    private Emitter.Listener onAuthSuccess = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            JSONObject jObj = new JSONObject();
            isConnected = true;

            try {
                jObj.put("conversationId", mCid);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mSocket.emit("JoinRoom", jObj);
        }
    };

    private Emitter.Listener onAuthFailure = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e("CHAT", "onConnectError");

        }
    };

    private Emitter.Listener onOnlineUser = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            JSONObject data = (JSONObject) args[0];
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    JSONObject data = (JSONObject) args[0];
                }
            });
        }
    };
    private Emitter.Listener onUserJoined = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "เข้าแล้ว", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    private Emitter.Listener onUserLeft = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    int numUsers;
                    try {
                        username = data.getString("username");
                        numUsers = data.getInt("numUsers");
                    } catch (JSONException e) {
                        return;
                    }

                }
            });
        }
    };

    private Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String cid = data.optString("conversationId");
                }
            });
        }
    };

    private Emitter.Listener onStopTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String cid = data.optString("conversationId");
                }
            });
        }
    };


    private Emitter.Listener onSendMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            Log.e("onSendMessage", args.toString());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Toast.makeText(getApplicationContext().getApplicationContext(), "Default Signature Fail", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            if (getApplicationContext() == null)
                return;

            interpretMessage((JSONObject) args[0]);
        }
    };

    private Runnable onTypingTimeout = new Runnable() {
        @Override
        public void run() {

            mSocket.emit("stop typing");
        }
    };

    public static String getRealPath(Context context, Uri mFileURI) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            return ChatUtil.getRealPathFromURIForKitKat(context, mFileURI);
        } else {
            return ChatUtil.getRealPathFromURI(context, mFileURI);
        }
    }

    private void uploadFileRetrofit(File file, final int msgType, final Message2 message) {
        FileUploadService service = buildUploadApi();
        TypedFile typedFile = new TypedFile("multipart/form-data", file);
        String description = "hello, this is description speaking";

        service.upload(typedFile, description, new Callback<UploadCallback>() {
            @Override
            public void success(UploadCallback cb, Response response) {
                Log.e("Upload", "success " + cb.toString());
                String dataJson;
                if (cb.getFileType() != null) {
                    if (cb.getFileType().equals("image/jpeg") || cb.getFileType().equals("image/png") || cb.getFileType().equals("image/gif"))
                        dataJson = "{'url':'" + cb.getFull_path() + "'" +
                                ",'full_path':'" + cb.getFull_path() + "'" +
                                ",'fileName':'" + cb.getFileName() + "'" +
                                ",'id':'" + cb.getId() + "'" +
                                ",'active':'" + cb.getActive() + "'" +
                                ",'thumb':'" + cb.getThumb() + "'" +
                                ",'fileType':'" + cb.getFileType() + "'}";
                    else
                        dataJson = "{'url':'" + cb.getFull_path() + "'" +
                                ",'full_path':'" + cb.getFull_path() + "'" +
                                ",'fileName':'" + cb.getFileName() + "'" +
                                ",'id':'" + cb.getId() + "'" +
                                ",'active':'" + cb.getActive() + "'" +
                                ",'thumb':'" + cb.getThumb() + "'" +
                                ",'fileType':'" + cb.getFileType() + "'}";

                    message.setIsSend(true);
                    message.setState(Message2.MSG_STATE_SUCCESS);
                    message.setData(dataJson);

                    adapter.notifyDataSetChanged();
                    attemptSendMessageToServer(msgType, "", dataJson);
                } else {
                    Toast.makeText(getActivity(), "Upload is not success, try again", Toast.LENGTH_SHORT).show();
                    message.setState(Message2.MSG_STATE_FAIL);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Upload", "error " + error);
            }
        });
    }

    FileUploadService buildUploadApi() {
        String BASE_URL = "http://chat.vdomax.com";

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)

                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                    }
                })
                .build()
                .create(FileUploadService.class);
    }


    File imagePathToFile(Uri selectedImageUri, String path) {
        Bitmap bm;
        BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

        bm = BitmapFactory.decodeFile(path, btmapOptions);
        OutputStream fOut = null;
        File file = new File(path);
        try {
            fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
            fOut.flush();
            fOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    public void showFaceLayout() {
        hideKeyboard(getActivity());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                moreTypeLayout.setVisibility(View.GONE);
                faceLayout.setVisibility(View.VISIBLE);
                bottomHideLayout.setVisibility(View.VISIBLE);
            }
        }, 50);
    }

    public void hideFaceLayout() {
        moreTypeLayout.setVisibility(View.GONE);
        faceLayout.setVisibility(View.GONE);
        bottomHideLayout.setVisibility(View.GONE);
    }

    public void hide() {
        bottomHideLayout.setVisibility(View.GONE);
        hideKeyboard(getActivity());
    }

    public static void hideKeyboard(Context context) {
        Activity activity = (Activity) context;
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive() && activity.getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }


    public Map<Integer, ArrayList<String>> getFaceData() {
        return faceData;
    }

    public void setFaceData(Map<Integer, ArrayList<String>> faceData) {
        this.faceData = faceData;
        faceCategroyAdapter.setData(faceData);
        faceCategroyViewPager.setAdapter(faceCategroyAdapter);
        faceCategroyTabs.setViewPager(faceCategroyViewPager);
        if (faceData.size() < 2) {
            faceCategroyTabs.setVisibility(View.GONE);
        }
    }

    public List<Option> getFunctionData() {
        return functionData;
    }

    public void setFunctionData(List<Option> functionData) {
        functionData = functionData;
        pointViews = new ArrayList<ImageView>();
        functionGridViewList = new ArrayList<View>();

        for (int x = 0; x < (functionData.size() % 8 == 0 ? functionData.size() / 8 : (functionData.size() / 8) + 1); x++) {
            GridView view = new GridView(context);
            FunctionAdapter functionAdapter = new FunctionAdapter(context,
                    functionData.subList(x * 8,
                            ((x + 1) * 8) > functionData.size() ? functionData.size() : ((x + 1) * 8)));
            view.setAdapter(functionAdapter);
            // faceAdapters.add(emojiAdapter);

            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    if (onOperationListener != null) {
                        onOperationListener.selectedFuncation(position);
                    }
                }
            });
            view.setNumColumns(4);

            view.setBackgroundColor(Color.TRANSPARENT);
            view.setHorizontalSpacing(1);
            view.setVerticalSpacing(1);
            view.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            view.setCacheColorHint(0);
            view.setPadding(5, 0, 5, 0);
            view.setSelector(new ColorDrawable(Color.TRANSPARENT));
            view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            view.setGravity(Gravity.CENTER);

            functionGridViewList.add(view);

            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(R.drawable.point_normal);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            layoutParams.width = 8;
            layoutParams.height = 8;
            pagePointLayout.addView(imageView, layoutParams);
            if (x == 0) {
                imageView.setBackgroundResource(R.drawable.point_selected);
            }
            pointViews.add(imageView);
        }

        fuctionViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return functionGridViewList.size();
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

            @Override
            public void destroyItem(View arg0, int arg1, Object arg2) {
                System.out.println(arg0 + "  " + arg1 + "  " + arg2);
                ((ViewPager) arg0).removeView(functionGridViewList.get(arg1));
            }

            @Override
            public Object instantiateItem(View arg0, int arg1) {
                ((ViewPager) arg0).addView(functionGridViewList.get(arg1));
                return functionGridViewList.get(arg1);
            }

        });

    }


    @Override
    public void setArticles(List<ChatMessage> articles) {
        Log.e("articles", articles.get(0).getSenderId() + "");
        //History(articles);
    }

    public void History(List<ChatMessage> articles) {
        Collections.reverse(articles);
        for (int i = 0; i < articles.size(); i++) {
            ChatMessage m = articles.get(i);
            Log.e("123345", articles.get(i).getSenderId() + "");
            Message2 message = null;
            boolean isSender = false;
            if (m.getSenderId() == mUserId) {
                isSender = true;
            } else {
                // setChatTitle("@" + m.sender.username);
                isSender = false;
            }


            if (m.messageType == 0) {

                // 0 = normal message
                message = new Message2(Message2.MSG_TYPE_TEXT, Message2.MSG_STATE_SUCCESS, m.getUsername(), m.getAvatar(), "", "", m.message, m.data, isSender, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), "000000", nameFont, null, 0, "");
                //addMessage(m.messageType,m.senderId,m.sender.username + "(type:"+m.messageType+")",m.message,m.data);

            } else if (m.messageType == 1) {

                // 1 = tattoo
                message = new Message2(Message2.MSG_TYPE_FACE, Message2.MSG_STATE_SUCCESS, m.getUsername(), m.getAvatar(), "", "", m.message, m.data, isSender, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), "000000", nameFont, null, 0, "");

                //addMessage(m.messageType,m.senderId,m.sender.username + "(type:"+m.messageType+")",m.data,m.data);
            } else if (m.messageType == 2) {

                // 2 = image
                message = new Message2(Message2.MSG_TYPE_PHOTO, Message2.MSG_STATE_SUCCESS, m.getUsername(), m.getAvatar(), "", "", m.message, m.data, isSender, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), "000000", nameFont, null, 0, "");

            } else if (m.messageType == 6) {

                // 6 = location

                message = new Message2(Message2.MSG_TYPE_LOCATION, Message2.MSG_STATE_SUCCESS, m.getUsername(), m.getAvatar(), "", "", m.data, m.data, isSender, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), "000000", nameFont, null, 0, "");

            } else if (m.messageType == 7) {

                // 7 = contact

            } else if (m.messageType == 8) {

                // 8 = voice message

            }

            listMessages.add(message);

        }


        chatListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        chatListView.setSelection(chatListView.getBottom());


    }
}
