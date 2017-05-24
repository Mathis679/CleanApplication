package com.example.mathis.cleanapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mathis.cleanapplication.R;
import com.example.mathis.cleanapplication.fragments.ListUserFragment;
import com.example.mathis.cleanapplication.presenters.ListUserPresenter;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public FlowingDrawer mDrawer;
    public Button but, butlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.refresh);
//        ListUserPresenter listUserPresenter = new ListUserPresenter(this);
//        listUserPresenter.displayFragment();


        but = (Button) findViewById(R.id.butopen);
        butlist = (Button) findViewById(R.id.butseelist);
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.i("MainActivity", "Drawer STATE_CLOSED");
                    but.setVisibility(View.VISIBLE);
                    butlist.setVisibility(View.VISIBLE);
                }else{
                    Log.i("MainActivity", "Drawer STATE_OPEN");
                    but.setVisibility(View.GONE);
                    butlist.setVisibility(View.GONE);
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
            }
        });



        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openMenu(true);
            }
        });

        butlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                but.setVisibility(View.GONE);
                butlist.setVisibility(View.GONE);
                mDrawer.setVisibility(View.GONE);
                ListUserPresenter listUserPresenter = new ListUserPresenter(MainActivity.this);
                listUserPresenter.displayFragment();
            }
        });
    }
}
