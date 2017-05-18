package com.example.mathis.cleanapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mathis.cleanapplication.R;
import com.example.mathis.cleanapplication.fragments.ListUserFragment;
import com.example.mathis.cleanapplication.presenters.ListUserPresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListUserPresenter listUserPresenter = new ListUserPresenter(this);
    }
}
