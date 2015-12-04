package natuan.org.androiddesigntablayout.fragments.fragmentTap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import natuan.org.androiddesigntablayout.BaseFragment;
import natuan.org.androiddesigntablayout.MainApplication;
import natuan.org.androiddesigntablayout.PhotoUtils;
import natuan.org.androiddesigntablayout.PrefManager;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.RoundedTransformation;
import natuan.org.androiddesigntablayout.Utils;
import natuan.org.androiddesigntablayout.activity.ActivityChatSetting;
import natuan.org.androiddesigntablayout.activity.BaseActivity;
import natuan.org.androiddesigntablayout.event.GetUserProfileEvent;
import natuan.org.androiddesigntablayout.event.GetUserProfileSuccessEvent;
import natuan.org.androiddesigntablayout.event.upload.UpdateAvatarEvent;
import natuan.org.androiddesigntablayout.event.upload.UpdateCoverEvent;
import natuan.org.androiddesigntablayout.handler.ApiBus;
import natuan.org.androiddesigntablayout.handler.PostUploadService;
import natuan.org.androiddesigntablayout.model.UploadAvatarCallback;
import natuan.org.androiddesigntablayout.model.UploadCoverCallback;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class FragmentEditName extends BaseFragment {
    Toolbar toolbar;
    TextView txt_qr;
    public PrefManager prefManager;
    public static final int REQUEST_GALLERY = 1;
    public static final int REQUEST_CAMERA = 2;
    public String url = "";
    public String userId = "";
    public EditText input_name;
    public EditText input_status;
    public EditText input_username;
    public EditText input_phone;
    public TextView txt_name;
    public ImageView imge_avatra;
    boolean isAvatar = true;
    ImageView cameraOverlay;
    ImageView cameraOverlay2;
    Button btn_home;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefManager = MainApplication.get(getActivity()).getPrefManager();

        userId = prefManager.userId().getOr("7");
        ApiBus.getInstance().post(new GetUserProfileEvent(userId));
        Log.e("userId", userId);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_name, container, false);
        input_name = (EditText) rootView.findViewById(R.id.input_name);
        input_username = (EditText) rootView.findViewById(R.id.input_username);
        input_status = (EditText) rootView.findViewById(R.id.input_status);
        input_phone = (EditText) rootView.findViewById(R.id.input_phone);
        btn_home = (Button) rootView.findViewById(R.id.btn_home);
        //  txt_name = (TextView) rootView.findViewById(R.id.txt_name);
        imge_avatra = (ImageView) rootView.findViewById(R.id.imge_avatra);


        txt_qr = (TextView) rootView.findViewById(R.id.textView18);
        txt_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentMoreQqCode fragment = new FragmentMoreQqCode();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.flContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        imge_avatra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAvatar = true;
                selectAvatar();
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfile(userId);
            }
        });

        return rootView;
    }

    public void selectAvatar() {
        final CharSequence[] items = {"Choose from Gallery", "Take from Camera",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (isAvatar)
            builder.setTitle("Update avatar");
        else
            builder.setTitle("Update cover");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Choose from Gallery")) {
                    pickImage();
                } else if (items[item].equals("Take from Camera")) {
                    pickCamera();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void pickImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_GALLERY);
    }

    public void pickCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode
            , Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", requestCode + " + " + resultCode);
        if (requestCode == REQUEST_GALLERY && resultCode == getActivity().RESULT_OK) {

            String selectedImagePath = PhotoUtils.getImagePath(data, getActivity());

            //Bitmap bmp = PhotoUtils.createScaledBitmap(selectedImagePath,200,200)
            Picasso.with(getActivity())
                    .load(selectedImagePath)
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 2))
                    .into(imge_avatra);

            File file = new File(selectedImagePath);
            if (isAvatar) {
                //uploadFileRetrofit(file, userId,"avatar");
            } else {
                //uploadFileRetrofit(file, userId,"cover");
            }


        } else if (requestCode == REQUEST_CAMERA && resultCode == getActivity().RESULT_OK) {
            //Uri selectedImageUri = data.getData();
            String selectedImagePath = PhotoUtils.getImagePath(data, getActivity());


            Picasso.with(getActivity())
                    .load(selectedImagePath)
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 2))
                    .into(imge_avatra);

            File file = new File(selectedImagePath);
            if (isAvatar) {
                //uploadFileRetrofit(file, userId,"avatar");
            } else {
                //uploadFileRetrofit(file, userId,"cover");
            }
        }
    }

    PostUploadService buildUploadApi() {
        String BASE_URL = "https://www.vdomax.com";

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)

                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        //request.addQueryParam("p1", "var1");
                        //request.addQueryParam("p2", "");
                    }
                })
                .build()
                .create(PostUploadService.class);
    }

    @Subscribe
    public void onGetUserProfile(GetUserProfileSuccessEvent event) {
        Log.e("OK chonlakant", event.getUser().getAvatarUrl() + "");
        Log.e("OK userName", event.getUser().getName());
        // txt_name.setText(Html.fromHtml(event.getUser().getName()));

//        Picasso.with(getActivity())
//                .load(event.getUser().getCoverUrl())
//                .fit().centerCrop()
//                .into(cover);
//
        Picasso.with(getActivity())
                .load(event.getUser().getAvatarUrl())
                .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 2))
                .into(imge_avatra);

        input_name.setText(event.getUser().getName());
        input_username.setText(event.getUser().getName());
        input_phone.setText("-");
        input_status.setText("Ok");

        userId = event.getUser().getId();
        url = "http://api.candychat.net/user/update/" + userId + "";

    }

    private void uploadProfile(String userId) {
        Charset chars = Charset.forName("UTF-8");
        String url = "http://api.candychat.net/user/update/" + userId;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", input_name.getText());
        params.put("name", input_username.getText());
        params.put("about", input_status.getText());
        params.put("email", input_phone.getText());
        params.put("birthday[]","");
        params.put("birthday[]", "");
        params.put("birthday[]", "");

        params.put("gender", "male");
        params.put("current_city", "thai");

        params.put("hometown", "Bangkok");
        params.put("timezone", "Bangkok");

        AQuery aq = new AQuery(getActivity());
        aq.ajax(url, params, JSONObject.class, this, "updateProfile");
    }

    public void updateProfile(String url, JSONObject jo, AjaxStatus status)
            throws JSONException {
        Log.e("hahaha", jo.toString(4));
        Utils.showToast("Update complete!");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        toolbar.inflateMenu(R.menu.menu_save);
        toolbar.setTitle("Edit name");
        menu.setGroupVisible(1,false);
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_Save:
                Toast.makeText(getContext(),"ddd",Toast.LENGTH_SHORT).show();
                uploadProfile(userId);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

}