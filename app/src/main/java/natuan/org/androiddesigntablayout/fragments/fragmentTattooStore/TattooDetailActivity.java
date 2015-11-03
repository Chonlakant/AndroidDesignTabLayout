package natuan.org.androiddesigntablayout.fragments.fragmentTattooStore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.TopMovieListView;
import natuan.org.androiddesigntablayout.adapter.TattooStoreDetailAdapter;
import natuan.org.androiddesigntablayout.model.Posts;
import natuan.org.androiddesigntablayout.presenter.MainPresenter;

public class TattooDetailActivity extends ActionBarActivity implements TopMovieListView {


    TattooStoreDetailAdapter adapterTattooStroe;
    ImageView sticker;
    TextView title_vdomax;
    TextView name_sticker;
    TextView date;
    TextView price;
    GridView gridView;
    MainPresenter mainPresenter;
    Posts tattoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tattoo_detail);

        sticker = (ImageView) findViewById(R.id.sticker);
        title_vdomax = (TextView) findViewById(R.id.title_candy_chat);
        name_sticker = (TextView) findViewById(R.id.name_sticker);
        date = (TextView) findViewById(R.id.date);

        mainPresenter  = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadData();

        gridView = (GridView) findViewById(R.id.gridView);
        Intent intent = getIntent();

        Posts person = Parcels.unwrap(intent.getParcelableExtra("tattoo"));




//        if(getIntent() != null) {
//            tattoo = Parcels.unwrap(getIntent().getBundleExtra("bundle").getParcelable("tattoo"));
//
//
//
//        }



    }


    @Override
    public void setArticles(List<Posts> articles) {
        title_vdomax.setText("Candy Chat Co., Ltd.");
        name_sticker.setText("Candy Chat");

        String path = articles.get(0).getImage();

        Picasso.with(getApplicationContext())
                .load(path)
                .into(sticker);

        adapterTattooStroe = new TattooStoreDetailAdapter(getApplicationContext(),articles);
        gridView.setAdapter(adapterTattooStroe);
    }
}

