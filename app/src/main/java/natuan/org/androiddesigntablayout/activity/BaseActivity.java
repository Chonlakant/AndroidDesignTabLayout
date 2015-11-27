/*
 * Copyright 2014 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package natuan.org.androiddesigntablayout.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;

import com.alexvasilkov.events.Events;

import butterknife.ButterKnife;
import natuan.org.androiddesigntablayout.R;
import natuan.org.androiddesigntablayout.handler.ApiBus;


public abstract class BaseActivity extends AppCompatActivity {


    private Context mContext;
    private Toolbar toolbar;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);

        //setupToolbar();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (getSupportActionBar() != null && toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        if (shouldInstallDrawer()) {
            //setupDrawer();
        }
        setupToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    protected void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);

        }
    }
    public Toolbar getToolbar() {
        return toolbar;
    }

    protected boolean shouldInstallDrawer() {
        return true;
    }

    /*
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    */

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mContext = this;

    }

    @Override
    protected void onResume() {
        super.onResume();
        ApiBus.getInstance().register(this);
        //getToolbar().setTitle("VDOMAX");
        //getToolbar().setTitle("@" + VMApplication.get(mContext).getPrefManager().username());
    }

    @Override
    protected void onPause() {
        super.onPause();
        ApiBus.getInstance().unregister(this);
    }

    protected int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    protected int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
    }


//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        Events.register(this);
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Events.unregister(this);
//    }


}
