package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.RoundedTransformation;
import natuan.org.androiddesigntablayout.activity.ActivityChatSetting;
import natuan.org.androiddesigntablayout.activity.ActivitySetting;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.AdapterMore;
import natuan.org.androiddesigntablayout.fragments.fragmentTattooStore.TattooStoreFragment;

public class FragmentMore extends Fragment {
    Toolbar toolbar;
    AdapterMore mAdpater;
    GridView gridView;
    ImageView imag_setting, imag_profile;
    LinearLayout click;
    int[] res = {R.drawable.d_b, R.drawable.adduer_b, R.drawable.setting_b, R.drawable.st_b, R.drawable.noti_b, R.drawable.sp_b, R.drawable.id_b, R.drawable.hp_b, R.drawable.sing_up_b};
    String[] title = {"Sticker shop", "Add friends", "Settings", "Official accounts", "Notices", "Tell friend", "Tips & Tricks", "Help & Support", "Put doodle"};

    public static FragmentMore getInstance(String message) {
        FragmentMore mainFragment = new FragmentMore();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridView);
        imag_profile = (ImageView) rootView.findViewById(R.id.imag_profile);
        click = (LinearLayout) rootView.findViewById(R.id.click);
        imag_setting = (ImageView) rootView.findViewById(R.id.imag_setting);
        mAdpater = new AdapterMore(getActivity(), title, res);
        gridView.setAdapter(mAdpater);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {


                    TattooStoreFragment fragment = new TattooStoreFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.flContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if (position == 1) {
                    Toast.makeText(getActivity(), "qqq", Toast.LENGTH_SHORT).show();
                    FragmentAddFriends fragment = new FragmentAddFriends();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.flContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if (position == 2) {
                    Intent i = new Intent(getActivity(), ActivitySetting.class);
                    startActivity(i);
                }


                if (position == 3) {
                    FragmentOfficeAccount fragment = new FragmentOfficeAccount();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.flContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
        imag_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ActivityChatSetting.class);
                startActivity(i);

            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentEditName fragment = new FragmentEditName();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.flContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        imag_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentEditName fragment = new FragmentEditName();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.flContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        Picasso.with(getActivity())
                .load("http://pe2.isanook.com/sp/0/ud/7/37796/353091-01.jpg")
                .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 4))
                .into(imag_profile);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // toolbar.inflateMenu(R.menu.menu_main_recent_chat);
        toolbar.setTitle("Add Frinds");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_add:
//                Toast.makeText(getActivity(), "Add", Toast.LENGTH_SHORT).show();
//                FragmentAddFriends fragment = new FragmentAddFriends();
//                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.add(R.id.flContainer, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//                return true;


        }
        return super.onOptionsItemSelected(item);
    }

}