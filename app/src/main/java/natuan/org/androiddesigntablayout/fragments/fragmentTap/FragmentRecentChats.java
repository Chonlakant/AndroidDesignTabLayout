package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import natuan.org.androiddesigntablayout.BaseFragment;
import natuan.org.androiddesigntablayout.MainApplication;
import natuan.org.androiddesigntablayout.PrefManager;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.AdapterRecentChats;
import natuan.org.androiddesigntablayout.event.GetRecentChatEvent;
import natuan.org.androiddesigntablayout.event.GetRecentChatSuccess;
import natuan.org.androiddesigntablayout.event.SomeEvent;
import natuan.org.androiddesigntablayout.event.SuccessEvent;
import natuan.org.androiddesigntablayout.handler.ApiBus;
import natuan.org.androiddesigntablayout.model.Conversation;
import natuan.org.androiddesigntablayout.model.ListChatCoverstion;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.model.postss;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;

/**
 * Created by Tuan on 6/18/2015.
 */
public class FragmentRecentChats extends BaseFragment {
    Toolbar toolbar;
    ListView listView;
    AdapterRecentChats adapterRecentChats;
    ArrayList<Conversation> list = new ArrayList<>();
    MainPresenter mMainPresenter;
    Boolean isCheck = false;
    private Bundle bundleState;
    PrefManager prefManager;

    EditText input_username;

    public static FragmentRecentChats getInstance(String message) {
        FragmentRecentChats mainFragment = new FragmentRecentChats();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        ApiBus.getInstance().postQueue(new GetRecentChatEvent(2868));

    }

    @Override
    public void onDestroyView() {
        bundleState = new Bundle();
        bundleState.putParcelable("posts", Parcels.wrap(list));
        super.onDestroyView();
    }

    boolean checkData = false;

    @Subscribe
    public void onGetRecentChatSuccess(GetRecentChatSuccess event) {
        Log.e("fffff",event.response.getContent().size()+"");
        List<ListChatCoverstion.ContentEntity> recentChatList = event.response.getContent();
        for (int i = 0; i < recentChatList.size(); i++) {
            ListChatCoverstion.ContentEntity recentChat = recentChatList.get(i);

            List<ListChatCoverstion.ContentEntity.ConversationMembersEntity> conversationMembers = recentChat.getConversationMembers();

            String friendName = "";
            String friendAvatar = "";
            int friendId = 0;

            for (int j = 0; j < conversationMembers.size(); j++) {
                ListChatCoverstion.ContentEntity.ConversationMembersEntity member = conversationMembers.get(j);
                int mUserId = Integer.parseInt("6");
                if (member.getUserId() != mUserId) {
                    friendId = member.getUserId();
                    friendName = member.getName();
                    friendAvatar =  MainApplication.IMAGE_ENDPOINT + member.getAvatar() + "." + member.getExtension();
                }
            }

            if (recentChat.getMemberType().equals("INDIVIDUAL")) {
                Conversation item = new Conversation(recentChat.getConversationId(), friendId, recentChat.getLastMessage(), friendName, friendAvatar, recentChat.getLastHistoryDatetime());
                list.add(item);
            }

        }

        adapterRecentChats.notifyDataSetChanged();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recent_chats, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SWZ721BR.ttf");
        ApiBus.getInstance().postQueue(new GetRecentChatEvent(2868));
        input_username = (EditText) rootView.findViewById(R.id.input_username);
        input_username.setTypeface(type);

        listView = (ListView) rootView.findViewById(R.id.listView);
        Log.e("SizeGetMoview", list.size() + "");
        adapterRecentChats = new AdapterRecentChats(getActivity(), list);
        listView.setAdapter(adapterRecentChats);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("ChatRoom")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        }).create().show();
            }
        });

        return rootView;
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        SpannableString title = new SpannableString(getResources().getString(R.string.recentchat));
        title.setSpan(Typeface.createFromAsset(getActivity().getAssets(), "fonts/SWZ721BR.ttf"), 0, title.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        toolbar.inflateMenu(R.menu.menu_main_recent_chat);
        toolbar.setTitle(title);
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Settings:


                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //initDataTabHost();
        //callSocket();
        bundleState = new Bundle();
        bundleState.putParcelable("posts", Parcels.wrap(list));
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterRecentChats.notifyDataSetChanged();
    }
}
