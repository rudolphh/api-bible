package com.example.apibible.bible;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apibible.R;
import com.example.apibible.util.SimpleItemTouchHelperCallback;

public class BiblesFragment extends Fragment {

    private BiblesViewModel biblesViewModel;
    private RecyclerView recyclerView;
    private View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.bibles_fragment, container, false);
        // set up recyclerView
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // set up adapter
        BibleAdapter bibleAdapter = new BibleAdapter();
        recyclerView.setAdapter(bibleAdapter);

        // get viewModel instance
        biblesViewModel = new ViewModelProvider.AndroidViewModelFactory(
                getActivity().getApplication()).create(BiblesViewModel.class);

        biblesViewModel.getAllBibles().observe(this, bibleList -> {
            bibleAdapter.setBibles(bibleList);
        });

        // set up touch helper for moving and swiping
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(bibleAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

}