package natuan.org.androiddesigntablayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ActivityViewPhotoChat extends AppCompatActivity {
    ImageView imageView9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo_chat);
        String imag = getIntent().getStringExtra("image");

//        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("image");
//        Log.e("dddd34",bitmap+"");

//        imageView9 = (ImageView) findViewById(R.id.imageView9);
//
//        imageView9.setImageBitmap(bitmap);


    }

}
