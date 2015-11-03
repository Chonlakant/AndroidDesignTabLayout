package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dpizarro.autolabel.library.AutoLabelUI;
import com.dpizarro.autolabel.library.Label;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.isming.tools.cvfilter.library.ImageData;
import natuan.org.androiddesigntablayout.PathManager;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.adapter.MyRecyclerAdapter;
import natuan.org.androiddesigntablayout.model.Person;

public class FragmentCreateGroup extends Fragment {
    private AutoLabelUI mAutoLabel;
    private List<Person> mPersonList;
    private MyRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    ImageView img_edit_profile;
    Toolbar toolbar;
    private File photoPath;
    private static final int PHOTO_SIZE_WIDTH = 100;
    private static final int PHOTO_SIZE_HEIGHT = 100;
    private static final int REQUEST_CHOOSE_PHOTO = 2;
    Menu menu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_a_new_group, container, false);
        img_edit_profile = (ImageView) view.findViewById(R.id.img_edit_profile);
        img_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });
        findViews(view);
        setListeners();
        setRecyclerView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState!=null){
            List<Person> people = savedInstanceState.getParcelableArrayList("statePeople");
            if(people != null){
                mPersonList = people;
                adapter.setPeople(people);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    private void itemListClicked(int position) {
        Person person = mPersonList.get(position);
        boolean isSelected = person.isSelected();
        boolean success;
        if (isSelected) {
            success = mAutoLabel.removeLabel(position);
        } else {
            success = mAutoLabel.addLabel(person.getName(), position);
        }
        if (success) {
            adapter.setItemSelected(position, !isSelected);
        }
    }

    private void setListeners() {
        mAutoLabel.setOnLabelsCompletedListener(new AutoLabelUI.OnLabelsCompletedListener() {
            @Override
            public void onLabelsCompleted() {
                Toast.makeText(getActivity(), "Completed!", Toast.LENGTH_SHORT).show();
            }
        });

        mAutoLabel.setOnRemoveLabelListener(new AutoLabelUI.OnRemoveLabelListener() {
            @Override
            public void onRemoveLabel(View view, int position) {
                adapter.setItemSelected(position, false);
            }
        });

        mAutoLabel.setOnLabelsEmptyListener(new AutoLabelUI.OnLabelsEmptyListener() {
            @Override
            public void onLabelsEmpty() {
                Toast.makeText(getActivity(), "EMPTY!", Toast.LENGTH_SHORT).show();
            }
        });

        mAutoLabel.setOnLabelClickListener(new AutoLabelUI.OnLabelClickListener() {
            @Override
            public void onClickLabel(View v) {
                Toast.makeText(getActivity(), ((Label) v).getText() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void findViews(View view) {
        mAutoLabel = (AutoLabelUI) view.findViewById(R.id.label_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        mAutoLabel.setBackgroundResource(R.drawable.round_corner_background);
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        mPersonList = new ArrayList<>();

        //Populate list
        List<String> names = Arrays.asList(getResources().getStringArray(R.array.names));
        List<String> ages = Arrays.asList(getResources().getStringArray(R.array.ages));
        TypedArray photos = getResources().obtainTypedArray(R.array.photos);

        for(int i = 0; i<names.size(); i++){
            mPersonList.add(new Person(names.get(i), ages.get(i), photos.getResourceId(i, -1), false));
        }

        photos.recycle();

        adapter = new MyRecyclerAdapter(mPersonList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                itemListClicked(position);
            }
        });
    }
    private void choosePhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                "image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        // intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", PHOTO_SIZE_WIDTH);
        intent.putExtra("outputY", PHOTO_SIZE_HEIGHT);
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHOOSE_PHOTO) {

            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                photoPath = new File(PathManager.getPath(getActivity(), selectedImageUri));
                showTakenPicture(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private void showTakenPicture(Bitmap bitmap) throws IOException {

            bitmap = ImageData.decodeSampledBitmapFromBitmap(photoPath.getPath(), 400, 600);

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("statePeople",
                (ArrayList<? extends Parcelable>) adapter.getPeople());

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        //toolbar.inflateMenu(R.menu.menu_create_group);
        toolbar.setTitle("Create a new group");
        super.onCreateOptionsMenu(menu, inflater);
        menu.setGroupVisible(0, false);
        inflater.inflate(R.menu.menu_create_group,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:

                return true;


        }
        return super.onOptionsItemSelected(item);
    }

}