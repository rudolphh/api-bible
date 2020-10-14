package com.example.apibible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.apibible.util.SimpleItemTouchHelperCallback;

// #2379be - primary
//

public class MainActivity extends AppCompatActivity {

    private BibleViewModel bibleViewModel;


    ////////////////////// onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set up recyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // set up adapter
        BibleAdapter bibleAdapter = new BibleAdapter();
        recyclerView.setAdapter(bibleAdapter);

        // get viewModel instance
        bibleViewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(BibleViewModel.class);

        bibleViewModel.getAllBibles().observe(this, bibleList -> {
            bibleAdapter.setBibles(bibleList);
        });

        // set up touch helper for moving and swiping
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(bibleAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);



//        // get instance for making api requests
//        apiBibleRequest = new ApiBibleRequest(getApplicationContext());
//        setBibleList();

    }// end onCreate


}