package natuan.org.androiddesigntablayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.norbsoft.typefacehelper.TypefaceCollection;

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
    private ArrayList<ChatMessage> chatMessages;
    private ImageView enterChatView1, emojiButton;
    private ChatListAdapter listAdapter;
    private EmojiView emojiView;
    private SizeNotifierRelativeLayout sizeNotifierRelativeLayout;
    private boolean showingEmoji;
    private int keyboardHeight;
    private boolean keyboardVisible;
    private WindowManager.LayoutParams windowLayoutParams;
    PrefManager prefManager;

    Dialog dialog;
    TextView txt_preview;

    public List<String> fontList;

    protected static final String TAG = "ShiftPicker";

    private LineColorPicker horizontalPicker;
    String[] pallete = new String[]{"#b8c847", "#67bb43", "#41b691",
            "#4182b6", "#4149b6", "#7641b6", "#b741a7", "#c54657", "#d1694a"};
    LinearLayout chat_font;
    AdapterRecyclerviewFont mAdapter;
    private static final String STATE_SELECTED_FONT = "STATE_SELECTED_FONT";

    private static final String TYPEFACE_DEFAULT = "System default";
    private static final String TYPEFACE_ACTIONMAN = "Action man";
    private static final String TYPEFACE_ARCHRIVAL = "Arch Rival";
    private static final String TYPEFACE_JUICE = "Juice";
    private static final String TYPEFACE_UBUNTU = "Ubuntu";

    public Map<String, TypefaceCollection> mTypefaceMap;

    RecyclerView rvContacts;
    private Spinner mTypefaceSpinner;
    private ToggleButton mBtnItalic;
    private ToggleButton mBtnBold;


    private EditText.OnKeyListener keyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            // If the event is a key-down event on the "enter" button
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                // Perform action on key press

                EditText editText = (EditText) v;

                if (v == chatEditText1) {
                    sendMessage(editText.getText().toString(), UserType.OTHER);
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

            if (v == enterChatView1) {
                sendMessage(chatEditText1.getText().toString(), UserType.OTHER);
                chat_font.setVisibility(View.GONE);
            }

            chatEditText1.setText("");

        }
    };

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);
        prefManager = MainApplication.getPrefManager();
        txt_preview = (TextView) findViewById(R.id.txt_preview);
        AndroidUtilities.statusBarHeight = getStatusBarHeight();
        chat_font = (LinearLayout) findViewById(R.id.chat_font);
        chatMessages = new ArrayList<>();

        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvContacts.setLayoutManager(layoutManager);
        chatListView = (ListView) findViewById(R.id.chat_list_view);

        chatEditText1 = (EditText) findViewById(R.id.chat_edit_text1);
        enterChatView1 = (ImageView) findViewById(R.id.enter_chat1);

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
        int color = horizontalPicker.getColor();

        updateColor(color);

        OnColorChangedListener onChangeListener = new OnColorChangedListener() {

            @Override
            public void onColorChanged(int c) {
                Log.d(TAG, "Selected color " + Integer.toHexString(c));
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
        mTypefaceMap = new HashMap<String, TypefaceCollection>(5);
        mTypefaceMap.put(TYPEFACE_DEFAULT, myApp.getSystemDefaultTypeface());
        mTypefaceMap.put(TYPEFACE_ACTIONMAN, myApp.getActionManTypeface());
        mTypefaceMap.put(TYPEFACE_ARCHRIVAL, myApp.getArchRivalTypeface());
        mTypefaceMap.put(TYPEFACE_JUICE, myApp.getJuiceTypeface());
        mTypefaceMap.put(TYPEFACE_UBUNTU, myApp.getUbuntuTypeface());

        fontList = new ArrayList<String>(mTypefaceMap.keySet().size());
        fontList.addAll(mTypefaceMap.keySet());

        mAdapter = new AdapterRecyclerviewFont(getActivity(), fontList);
        rvContacts.setAdapter(mAdapter);

        mAdapter.SetOnItemVideiosClickListener(new AdapterRecyclerviewFont.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                applyDynamicTypeface(fontList.get(position), mBtnBold.isChecked(), mBtnItalic.isChecked());
                Log.e("font55", fontList.get(position) + "");
                prefManager.font().put(fontList.get(position));
                prefManager.commit();

            }
        });


    }


    private void sendMessage(final String messageText, final UserType userType) {
        if (messageText.trim().length() == 0)
            return;

        final ChatMessage message = new ChatMessage();
        message.setMessageStatus(Status.SENT);
        message.setMessageText(messageText);
        message.setUserType(userType);
        message.setMessageTime(new Date().getTime());
        chatMessages.add(message);

        if (listAdapter != null)
            listAdapter.notifyDataSetChanged();

        // Mark message as delivered after one second

        final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

        exec.schedule(new Runnable() {
            @Override
            public void run() {
                message.setMessageStatus(Status.DELIVERED);

                final ChatMessage message = new ChatMessage();
                message.setMessageStatus(Status.SENT);
                message.setMessageText(messageText);
                message.setUserType(UserType.SELF);
                message.setMessageTime(new Date().getTime());
                chatMessages.add(message);

                MainActivityChat.this.runOnUiThread(new Runnable() {
                    public void run() {
                        listAdapter.notifyDataSetChanged();
                    }
                });


            }
        }, 1, TimeUnit.SECONDS);

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

                return true;
            case R.id.action_color:
//                 dialog = new Dialog(getActivity(), R.style.FullHeightDialog);
//                dialog.setContentView(R.layout.dialog_custom_multiple_typefaces);
//                dialog.show();

                chat_font.setVisibility(View.VISIBLE);


                return true;


        }
        return super.onOptionsItemSelected(item);
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

    public  void applyDynamicTypeface(String selectedFont, boolean flgBold, boolean flgItalic) {
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


}
