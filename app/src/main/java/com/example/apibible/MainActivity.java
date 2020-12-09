package com.example.apibible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import com.example.apibible.bible.fragments.BiblesFragment;


public class MainActivity extends AppCompatActivity {


    ////////////////////// onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //Instance of fragment
        Fragment newFragment = new BiblesFragment();
        //It will replace the fragment content view to container of main activity
        ft.replace(R.id.container, newFragment);
        //BiblesFragment is added to back stack with it's name as a tag
        ft.addToBackStack(BiblesFragment.class.getSimpleName());
        ft.commitAllowingStateLoss();

    }// end onCreate

}