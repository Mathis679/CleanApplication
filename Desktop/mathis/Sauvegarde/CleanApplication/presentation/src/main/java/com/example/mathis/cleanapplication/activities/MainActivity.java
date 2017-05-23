package com.example.mathis.cleanapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mathis.cleanapplication.R;
import com.example.mathis.cleanapplication.fragments.ListUserFragment;
import com.example.mathis.cleanapplication.presenters.ListUserPresenter;

public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.refresh);
        ListUserPresenter listUserPresenter = new ListUserPresenter(this);
        listUserPresenter.displayFragment();
    }
}
