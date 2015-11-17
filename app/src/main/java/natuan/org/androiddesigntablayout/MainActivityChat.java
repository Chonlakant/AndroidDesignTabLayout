package natuan.org.androiddesigntablayout;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.norbsoft.typefacehelper.TypefaceCollection;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.AdapterRecyclerviewFont;
import natuan.org.androiddesigntablayout.model.ChatMessage;
import natuan.org.androiddesigntablayout.model.Font;
import natuan.org.androiddesigntablayout.model.Status;
import natuan.org.androiddesigntablayout.model.UserType;
import natuan.org.androiddesigntablayout.widgets.Emoji;
import natuan.org.androiddesigntablayout.widgets.EmojiView;
import natuan.org.androiddesigntablayout.widgets.SizeNotifierRelativeLayout;
import uz.shift.colorpicker.LineColorPicker;
import uz.shift.colorpicker.OnColorChangedListener;

import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;


public class MainActivityChat extends BaseActivity implements SizeNotifierRelativeLayout.SizeNotifierRelativeLayoutDelegate
        , NotificationCenter.NotificationCenterDelegate, View.OnClickListener {
    private ListView chatListView;
    public EditText chatEditText1;
     ArrayList<ChatMessage> chatMessages;
    private ImageView enterChatView1, emojiButton;
    private ChatListAdapter listAdapter;
    private EmojiView emojiView;
    private SizeNotifierRelativeLayout sizeNotifierRelativeLayout;
    private boolean showingEmoji;
    private int keyboardHeight;
    private boolean keyboardVisible;
    private WindowManager.LayoutParams windowLayoutParams;
    PrefManager prefManager;
    Button btn_reset;
    Dialog dialog;
    TextView txt_preview;
    private FragmentManager fragmentManager;
    public List<String> fontList;

    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECT_PHOTO = 100;
    private static final int RESULT_PICK_VIDEO = 4;
    private static final int RESULT_VIDEO_CAP = 5;
    private Uri imageToUploadUri;

    static String str_Camera_Photo_ImagePath = "";
    private static File f;
    private static int Take_Photo = 2;
    private static String str_randomnumber = "";
    static String str_Camera_Photo_ImageName = "";
    public static String str_SaveFolderName;
    private static File wallpaperDirectory;
    Bitmap bitmap;
    int storeposition = 0;
    EditText editText;

    TextView txt_take_photo;
    TextView txt_gallery;
    TextView txt_video;

    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;

    ArrayList<Font> listFontStyle = new ArrayList<>();

    protected static final String TAG = "ShiftPicker";

    private LineColorPicker horizontalPicker;
    String[] pallete = new String[]{"#000000", "#67bb43", "#41b691",
            "#4182b6", "#4149b6", "#7641b6", "#b741a7", "#c54657", "#d1694a"};
    RelativeLayout chat_font, chat_attament;
    LinearLayout chat_more;
    AdapterRecyclerviewFont mAdapter;

    private static final String TYPEFACE_ACTIONMAN = "Action man";
    private static final String TYPEFACE_ARCHRIVAL = "Arch Rival";
    private static final String TYPEFACE_JUICE = "Juice";
    private static final String TYPEFACE_DEFAULT = "System default";
    private static final String TYPEFACE_ZOOD = "Memory";
    private static final String TYPEFACE_SUPERMARKET = "SuperMarket";
    private static final String TYPEFACE_THSARABUN = "THSarabun";

    //EN
    private static final String TYPEFACE_Cartoon = "Cartoon";
    private static final String TYPEFACE_Comica = "Comica";
    private static final String TYPEFACE_DaVia = "DaVia";
    private static final String TYPEFACE_Facile = "Facile";
    private static final String TYPEFACE_Goudosb = "Goudosb";
    private static final String TYPEFACE_Koren = "Koren";
    private static final String TYPEFACE_Mari = "Mari";
    private static final String TYPEFACE_Noodle = "Noodle";
    private static final String TYPEFACE_OneMore = "OneMore";
    private static final String TYPEFACE_SanFrancisco = "SanFrancisco";
    private static final String TYPEFACE_SfSppend = "SfSppend";
    private static final String TYPEFACE_TheMillion = "TheMillion";
    private static final String TYPEFACE_Vaground = "Vaground";
    private static final String TYPEFACE_Weiss = "Weiss";

    //TH
    private static final String TYPEFACE_Bangna = "Bangna";
    private static final String TYPEFACE_Cookies = "Cookies";
    private static final String TYPEFACE_Domino = "Domino";
    private static final String TYPEFACE_Drjoyfuk = "Drjoyfuk";
    private static final String TYPEFACE_Paaymaay = "Paaymaay";
    private static final String TYPEFACE_Parggar = "Parggar";
    private static final String TYPEFACE_Pledite = "Pledite";
    private static final String TYPEFACE_Prachachon = "Prachachon";
    private static final String TYPEFACE_Rtemehua = "Rtemehua";
    private static final String TYPEFACE_WrTish = "WrTish";


    String photoPath;


    public Map<String, TypefaceCollection> mTypefaceMap;
    boolean isCheckBotton;
    RecyclerView rvContacts;
    private Spinner mTypefaceSpinner;
    private ToggleButton mBtnItalic;
    private ToggleButton mBtnBold;
    private Uri mFileURI = null;
    String imageUrl;
    private EditText.OnKeyListener keyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            // If the event is a key-down event on the "enter" button
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                // Perform action on key press

                editText = (EditText) v;

                ChatMessage chatMessage = new ChatMessage();


                if (v == chatEditText1) {
                    bitmap = null;
                    chatMessage.setMessageText(chatEditText1.getText().toString());
                    chatMessage.setMessageTime(new Date().getTime());
                    chatMessage.setTypeStyle(nameFont);
                    chatMessage.setTypeColor(typeColor);
                    chatMessage.setmType(ChatMessage.MSG_TYPE_TEXT);
                    chatMessage.setUserType(UserType.OTHER);
                    sendMessage(chatMessage);

                }

                chatEditText1.setText("");

                return true;
            }
            return false;

        }
    };

    private ImageView.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ChatMessage chatMessage = new ChatMessage();
            if (v == enterChatView1) {
                bitmap = null;
                chatMessage.setMessageText(chatEditText1.getText().toString());
                chatMessage.setMessageTime(new Date().getTime());
                chatMessage.setTypeStyle(nameFont);
                chatMessage.setTypeColor(typeColor);
                chatMessage.setUserType(UserType.OTHER);
                chatMessage.setmType(ChatMessage.MSG_TYPE_TEXT);
                sendMessage(chatMessage);
                chat_font.setVisibility(View.GONE);
                chat_attament.setVisibility(View.GONE);

                hideKeyboard(v);
            }

            chatEditText1.setText("");

        }
    };

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private final TextWatcher watcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (chatEditText1.getText().toString().equals("")) {

            } else {
                enterChatView1.setImageResource(R.drawable.ic_chat_send);

            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() == 0) {
                enterChatView1.setImageResource(R.drawable.ic_chat_send);
            } else {
                enterChatView1.setImageResource(R.drawable.ic_chat_send_active);
            }
        }
    };

    String nameFont;
    String typeColor;

    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        txt_take_photo = (TextView) findViewById(R.id.txt_take_photo);
        txt_gallery = (TextView) findViewById(R.id.txt_gallery);
        txt_video = (TextView) findViewById(R.id.txt_video);


        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();

        btn_reset = (Button) findViewById(R.id.btn_reset);
        prefManager = MainApplication.getPrefManager();
        txt_preview = (TextView) findViewById(R.id.txt_preview);
        AndroidUtilities.statusBarHeight = getStatusBarHeight();
        chat_font = (RelativeLayout) findViewById(R.id.chat_font);
        chat_more = (LinearLayout) findViewById(R.id.chat_more);
        chat_attament = (RelativeLayout) findViewById(R.id.chat_attament);
        chatMessages = new ArrayList<>();

        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvContacts.setLayoutManager(layoutManager);
        chatListView = (ListView) findViewById(R.id.chat_list_view);
        chatListView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        chatEditText1 = (EditText) findViewById(R.id.chat_edit_text1);
        enterChatView1 = (ImageView) findViewById(R.id.enter_chat1);


        txt_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                chat_attament.setVisibility(View.GONE);
            }
        });

        txt_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                chat_attament.setVisibility(View.GONE);
            }
        });

        txt_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildVideoDialog();

            }
        });

        // Hide the emoji on click of edit text
        chatEditText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showingEmoji)
                    hideEmojiPopup();
            }
        });


        horizontalPicker = (LineColorPicker) findViewById(R.id.picker);

        // Create palette from HEX values
        int[] colors = new int[pallete.length];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.parseColor(pallete[i]);
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
                Log.d("Selected color ", c + "");
                typeColor = Integer.toString(c);
                prefManager.intColor().put(c);
                prefManager.commit();
                chatEditText1.setTextColor(c);
                txt_preview.setTextColor(c);
                updateColor(c);
            }
        };

        horizontalPicker.setOnColorChangedListener(onChangeListener);
        emojiButton = (ImageView) findViewById(R.id.emojiButton);

        emojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEmojiPopup(!showingEmoji);
            }
        });

        listAdapter = new ChatListAdapter(chatMessages, this);


        chatListView.setAdapter(listAdapter);


        listAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                chatListView.setSelection(listAdapter.getCount() - 1);
            }
        });


        chatEditText1.setOnKeyListener(keyListener);

        enterChatView1.setOnClickListener(clickListener);

        chatEditText1.addTextChangedListener(watcher1);

        sizeNotifierRelativeLayout = (SizeNotifierRelativeLayout) findViewById(R.id.chat_layout);
        sizeNotifierRelativeLayout.delegate = this;

        NotificationCenter.getInstance().addObserver(this, NotificationCenter.emojiDidLoaded);

        mBtnItalic = (ToggleButton) findViewById(R.id.btn_italic);
        mBtnBold = (ToggleButton) findViewById(R.id.btn_bold);
        // mTypefaceSpinner = (Spinner) findViewById(R.id.spinner);

        mBtnItalic.setOnClickListener(this);
        mBtnBold.setOnClickListener(this);

        // Retrieve custom typefaces from Application subclass
        MainApplication myApp = (MainApplication) getApplication();
        mTypefaceMap = new HashMap<String, TypefaceCollection>(6);

        mTypefaceMap.put(TYPEFACE_ACTIONMAN, myApp.getActionManTypeface());
        mTypefaceMap.put(TYPEFACE_ARCHRIVAL, myApp.getArchRivalTypeface());
        mTypefaceMap.put(TYPEFACE_ZOOD, myApp.getZoodHaritTypeface());
        mTypefaceMap.put(TYPEFACE_SUPERMARKET, myApp.getsUperMarketTypeface());
        mTypefaceMap.put(TYPEFACE_THSARABUN, myApp.getThSarabunNewTypeface());
        mTypefaceMap.put(TYPEFACE_JUICE, myApp.getJuiceTypeface());
        mTypefaceMap.put(TYPEFACE_DEFAULT, myApp.getSystemDefaultTypeface());

        mTypefaceMap.put(TYPEFACE_Cartoon, myApp.getEnCartoonTypeface());
        mTypefaceMap.put(TYPEFACE_Comica, myApp.getEnComicaTypeface());
        mTypefaceMap.put(TYPEFACE_DaVia, myApp.getEnDaViaTypeface());
        mTypefaceMap.put(TYPEFACE_Facile, myApp.getEnFacileTypeface());
        mTypefaceMap.put(TYPEFACE_Goudosb, myApp.getEnGoudosbTypeface());
        mTypefaceMap.put(TYPEFACE_Koren, myApp.getEnKorenTypeface());
        mTypefaceMap.put(TYPEFACE_Mari, myApp.getEnMariTypeface());
        mTypefaceMap.put(TYPEFACE_Noodle, myApp.getEnNoodleTypeface());
        mTypefaceMap.put(TYPEFACE_OneMore, myApp.getEnOneMoreTypeface());
        mTypefaceMap.put(TYPEFACE_SanFrancisco, myApp.getEnSanFranciscoTypeface());
        mTypefaceMap.put(TYPEFACE_SfSppend, myApp.getEnSfSppendTypeface());
        mTypefaceMap.put(TYPEFACE_TheMillion, myApp.getEnTheMillionTypeface());
        mTypefaceMap.put(TYPEFACE_Vaground, myApp.getEnVagroundTypeface());
        mTypefaceMap.put(TYPEFACE_Weiss, myApp.getEnWeissTypeface());
        mTypefaceMap.put(TYPEFACE_Bangna, myApp.getThBangnaTypeface());
        mTypefaceMap.put(TYPEFACE_Cookies, myApp.getThCookiesTypeface());
        mTypefaceMap.put(TYPEFACE_Domino, myApp.getThDominoTypeface());
        mTypefaceMap.put(TYPEFACE_Drjoyfuk, myApp.getThDrjoyfukTypeface());
        mTypefaceMap.put(TYPEFACE_Paaymaay, myApp.getThPaaymaayTypeface());
        mTypefaceMap.put(TYPEFACE_Parggar, myApp.getThParggarTypeface());
        mTypefaceMap.put(TYPEFACE_Pledite, myApp.getThPlediteTypeface());
        mTypefaceMap.put(TYPEFACE_Prachachon, myApp.getThPrachachonTypeface());
        mTypefaceMap.put(TYPEFACE_Rtemehua, myApp.getThRtemehuaTypeface());
        mTypefaceMap.put(TYPEFACE_WrTish, myApp.getThWrTishTypeface());

        fontList = new ArrayList<String>(mTypefaceMap.keySet().size());
        fontList.addAll(mTypefaceMap.keySet());

        Log.e("123456", fontList.size() + "");

        mAdapter = new AdapterRecyclerviewFont(getActivity(), fontList);
        rvContacts.setAdapter(mAdapter);

        final TextView textView32 = (TextView) findViewById(R.id.textView32);

        mAdapter.SetOnItemVideiosClickListener(new AdapterRecyclerviewFont.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                applyDynamicTypeface(fontList.get(position), mBtnBold.isChecked(), mBtnItalic.isChecked());
                Log.e("font55", fontList.get(position) + "");
                nameFont = fontList.get(position);
                prefManager.font().put(fontList.get(position));
                prefManager.commit();

            }
        });


        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-R.ttf");
                txt_preview.setTextColor(Color.BLACK);
                chatEditText1.setTextColor(Color.BLACK);
                chatEditText1.setTypeface(typeface);
            }
        });


    }

    public String nextSessionId() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }


    private Activity getActivity() {
        return this;
    }


    /**
     * Show or hide the emoji popup
     *
     * @param show
     */
    private void showEmojiPopup(boolean show) {
        showingEmoji = show;

        if (show) {
            if (emojiView == null) {
                if (getActivity() == null) {
                    return;
                }
                emojiView = new EmojiView(getActivity());

                emojiView.setListener(new EmojiView.Listener() {
                    public void onBackspace() {
                        chatEditText1.dispatchKeyEvent(new KeyEvent(0, 67));
                    }

                    public void onEmojiSelected(String symbol) {
                        int i = chatEditText1.getSelectionEnd();
                        if (i < 0) {
                            i = 0;
                        }
                        try {
                            CharSequence localCharSequence = Emoji.replaceEmoji(symbol, chatEditText1.getPaint().getFontMetricsInt(), AndroidUtilities.dp(20));
                            chatEditText1.setText(chatEditText1.getText().insert(i, localCharSequence));
                            int j = i + localCharSequence.length();
                            chatEditText1.setSelection(j, j);
                        } catch (Exception e) {
                            Log.e(Constants.TAG, "Error showing emoji");
                        }
                    }
                });


                windowLayoutParams = new WindowManager.LayoutParams();
                windowLayoutParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
                if (Build.VERSION.SDK_INT >= 21) {
                    windowLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
                } else {
                    windowLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
                    windowLayoutParams.token = getActivity().getWindow().getDecorView().getWindowToken();
                }
                windowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            }

            final int currentHeight;

            if (keyboardHeight <= 0)
                keyboardHeight = MainApplication.getInstance().getSharedPreferences("emoji", 0).getInt("kbd_height", AndroidUtilities.dp(200));

            currentHeight = keyboardHeight;

            WindowManager wm = (WindowManager) MainApplication.getInstance().getSystemService(Activity.WINDOW_SERVICE);

            windowLayoutParams.height = currentHeight;
            windowLayoutParams.width = AndroidUtilities.displaySize.x;

            try {
                if (emojiView.getParent() != null) {
                    wm.removeViewImmediate(emojiView);
                }
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage());
            }

            try {
                wm.addView(emojiView, windowLayoutParams);
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage());
                return;
            }

            if (!keyboardVisible) {
                if (sizeNotifierRelativeLayout != null) {
                    sizeNotifierRelativeLayout.setPadding(0, 0, 0, currentHeight);
                }

                return;
            }

        } else {
            removeEmojiWindow();
            if (sizeNotifierRelativeLayout != null) {
                sizeNotifierRelativeLayout.post(new Runnable() {
                    public void run() {
                        if (sizeNotifierRelativeLayout != null) {
                            sizeNotifierRelativeLayout.setPadding(0, 0, 0, 0);
                        }
                    }
                });
            }
        }


    }


    /**
     * Remove emoji window
     */
    private void removeEmojiWindow() {
        if (emojiView == null) {
            return;
        }
        try {
            if (emojiView.getParent() != null) {
                WindowManager wm = (WindowManager) MainApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
                wm.removeViewImmediate(emojiView);
            }
        } catch (Exception e) {
            Log.e(Constants.TAG, e.getMessage());
        }
    }


    /**
     * Hides the emoji popup
     */
    public void hideEmojiPopup() {
        if (showingEmoji) {
            showEmojiPopup(false);
        }
    }

    /**
     * Check if the emoji popup is showing
     *
     * @return
     */
    public boolean isEmojiPopupShowing() {
        return showingEmoji;
    }


    /**
     * Updates emoji views when they are complete loading
     *
     * @param id
     * @param args
     */
    @Override
    public void didReceivedNotification(int id, Object... args) {
        if (id == NotificationCenter.emojiDidLoaded) {
            if (emojiView != null) {
                emojiView.invalidateViews();
            }

            if (chatListView != null) {
                chatListView.invalidateViews();
            }
        }
    }

    @Override
    public void onSizeChanged(int height) {

        Rect localRect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);

        WindowManager wm = (WindowManager) MainApplication.getInstance().getSystemService(Activity.WINDOW_SERVICE);
        if (wm == null || wm.getDefaultDisplay() == null) {
            return;
        }


        if (height > AndroidUtilities.dp(50) && keyboardVisible) {
            keyboardHeight = height;
            MainApplication.getInstance().getSharedPreferences("emoji", 0).edit().putInt("kbd_height", keyboardHeight).commit();
        }


        if (showingEmoji) {
            int newHeight = 0;

            newHeight = keyboardHeight;

            if (windowLayoutParams.width != AndroidUtilities.displaySize.x || windowLayoutParams.height != newHeight) {
                windowLayoutParams.width = AndroidUtilities.displaySize.x;
                windowLayoutParams.height = newHeight;

                wm.updateViewLayout(emojiView, windowLayoutParams);
                if (!keyboardVisible) {
                    sizeNotifierRelativeLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            if (sizeNotifierRelativeLayout != null) {
                                sizeNotifierRelativeLayout.setPadding(0, 0, 0, windowLayoutParams.height);
                                sizeNotifierRelativeLayout.requestLayout();
                            }
                        }
                    });
                }
            }
        }


        boolean oldValue = keyboardVisible;
        keyboardVisible = height > 0;
        if (keyboardVisible && sizeNotifierRelativeLayout.getPaddingBottom() > 0) {
            showEmojiPopup(false);
        } else if (!keyboardVisible && keyboardVisible != oldValue && showingEmoji) {
            showEmojiPopup(false);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.emojiDidLoaded);
    }

    /**
     * Get the system status bar height
     *
     * @return
     */
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

        hideEmojiPopup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.action_attach:
                onClick3();
                return true;
            case R.id.action_color:

                onClick();

                return true;

            case R.id.context_menu:

                onClick2();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick() {

        if (isCheckBotton != true) {
            chat_font.setVisibility(View.VISIBLE);
            isCheckBotton = true;
        } else {

            chat_font.setVisibility(View.GONE);
            isCheckBotton = false;
        }


    }

    public void onClick2() {

        if (isCheckBotton != true) {
            chat_more.setVisibility(View.VISIBLE);
            isCheckBotton = true;
        } else {

            chat_more.setVisibility(View.GONE);
            isCheckBotton = false;
        }


    }

    public void onClick3() {

        if (isCheckBotton != true) {
            chat_attament.setVisibility(View.VISIBLE);
            isCheckBotton = true;
        } else {

            chat_attament.setVisibility(View.GONE);
            isCheckBotton = false;
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putInt(STATE_SELECTED_FONT, mTypefaceSpinner.getSelectedItemPosition());
    }

    @Override
    public void onClick(View v) {
//        applyDynamicTypeface(
//                (String) mAdapter.getSelectedItem(),
//                mBtnBold.isChecked(),
//                mBtnItalic.isChecked());
    }

    public void applyDynamicTypeface(String selectedFont, boolean flgBold, boolean flgItalic) {
//        typeface(this, mTypefaceMap.get(selectedFont));
//        ActionBarHelper.setTitle(getSupportActionBar(), typeface(getString(R.string.app_name), mTypefaceMap.get(selectedFont),
//                getTypefaceStyle(flgBold, flgItalic)));

        // Std typeface style set for ordinary textview
        chatEditText1.setTypeface(null, getTypefaceStyle(flgBold, flgItalic));

        // Apply custom typeface
        typeface(chatEditText1, mTypefaceMap.get(selectedFont));
        typeface(txt_preview, mTypefaceMap.get(selectedFont));


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

    public void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, RESULT_VIDEO_CAP);
    }

    public void pickVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(intent, RESULT_PICK_VIDEO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", requestCode + " + " + resultCode);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            final int THUMBNAIL_HEIGHT = 75;//48
            final int THUMBNAIL_WIDTH = 75;//66
            Float width  = new Float(bitmap.getWidth());
            Float height = new Float(bitmap.getHeight());
            Float ratio = width/height;
            bitmap = Bitmap.createScaledBitmap(bitmap, (int) (THUMBNAIL_HEIGHT * ratio), THUMBNAIL_HEIGHT, false);

            ChatMessage message = new ChatMessage();
            message.setMessageStatus(Status.SENT);
            message.setmImage(bitmap);
            message.setUserType(UserType.OTHER);
            message.setMessageTime(new Date().getTime());
            message.setTypeStyle(nameFont);
            message.setmType(ChatMessage.MSG_TYPE_CAMERA);
            message.setTypeColor(typeColor);
            sendMessage(message);

        } else if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(selectedImage);
                bitmap = BitmapFactory.decodeStream(imageStream);

                final int THUMBNAIL_HEIGHT = 75;//48
                final int THUMBNAIL_WIDTH = 75;//66
                Float width  = new Float(bitmap.getWidth());
                Float height = new Float(bitmap.getHeight());
                Float ratio = width/height;
                bitmap = Bitmap.createScaledBitmap(bitmap, (int) (THUMBNAIL_HEIGHT * ratio), THUMBNAIL_HEIGHT, false);

                Log.e("dfgk", bitmap + "");
                ChatMessage message = new ChatMessage();
                message.setMessageStatus(Status.SENT);
                message.setmImage(bitmap);
                message.setUserType(UserType.OTHER);
                message.setMessageTime(new Date().getTime());
                message.setTypeStyle(nameFont);
                message.setmType(ChatMessage.MSG_TYPE_PHOTO);
                message.setTypeColor(typeColor);
                sendMessage(message);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else if (requestCode == RESULT_PICK_VIDEO) {

            mFileURI = data.getData();
            if (mFileURI != null) {
                String vdoThumb = ChatUtil.getThumbnailPathForLocalFile(getActivity(), mFileURI);
                String dataJson = "";
                if (vdoThumb != null)
                    dataJson = "{'thumb':'" + vdoThumb + "'}";


                String path = ChatUtil.getRealPathFromURIVideo(getActivity(), mFileURI);
                File clip = new File(path);

                ChatMessage message = new ChatMessage();
                message.setMessageStatus(Status.SENT);
                message.setMessageText("");
                message.setmImage(bitmap);
                message.setUserType(UserType.OTHER);
                message.setMessageTime(new Date().getTime());
                message.setTypeStyle(nameFont);
                message.setmType(ChatMessage.MSG_TYPE_VIDEO);
                message.setTypeColor(typeColor);
                sendMessage(message);

            }

        } else if (requestCode == RESULT_VIDEO_CAP) {

            mFileURI = data.getData();

            if (mFileURI != null) {
                String vdoThumb = ChatUtil.getThumbnailPathForLocalFile(getActivity(), mFileURI);
                String dataJson = "";
                if (vdoThumb != null)
                    dataJson = "{'thumb':'" + vdoThumb + "'}";


                String path = ChatUtil.getRealPathFromURIVideo(getActivity(), mFileURI);
                File clip = new File(path);
                Log.e("cccccc", clip + "");


            }

        }
    }


    private void sendMessage(final ChatMessage chatMessage) {
//        if (chatMessage.getMessageText().trim().length() == 0)
//            return;
        //final ChatMessage message = new ChatMessage();

        chatMessages.add(chatMessage);


        if (listAdapter != null)
            listAdapter.notifyDataSetChanged();


        // Mark message as delivered after one second

        final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

        exec.schedule(new Runnable() {
            @Override
            public void run() {
                //chatMessage.setUserType(UserType.SELF);
                chatMessages.add(chatMessage);

                MainActivityChat.this.runOnUiThread(new Runnable() {
                    public void run() {
                        listAdapter.notifyDataSetChanged();
                    }
                });


            }
        }, 1, TimeUnit.SECONDS);

    }

    public void buildVideoDialog() {
        final CharSequence[] items = {"Record Video", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setTitle("Add Video!");
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


}
