package com.example.apibible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;


// #2379be - primary
//

public class MainActivity extends AppCompatActivity {


    ////////////////////// onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





//        // get instance for making api requests
//        apiBibleRequest = new ApiBibleRequest(getApplicationContext());
//        setBibleList();

    }// end onCreate


}