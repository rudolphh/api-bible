package com.example.apibible.bible.fragments;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.Toast;

import com.example.apibible.R;
import com.example.apibible.bible.adapters.BibleAdapter;
import com.example.apibible.bible.viewmodels.BiblesViewModel;
import com.example.apibible.book.BooksFragment;
import com.example.apibible.util.SimpleItemTouchHelperCallback;

import java.util.Objects;

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
        BibleAdapter.RecyclerViewClickListener listener = (view, position, bibleId) -> {
            Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_LONG).show();

            Fragment fragment = new BooksFragment();
            Bundle args = new Bundle();
            args.putString("BibleId", bibleId);
            fragment.setArguments(args);

            FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  // popExit
                    );
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        };

        BibleAdapter bibleAdapter = new BibleAdapter(listener);
        recyclerView.setAdapter(bibleAdapter);

        // get viewModel instance
        biblesViewModel = new ViewModelProvider.AndroidViewModelFactory(
                Objects.requireNonNull(getActivity()).getApplication()).create(BiblesViewModel.class);

        biblesViewModel.getAllBibles().observe(this, bibleAdapter::setBibles);

        // set up touch helper for moving and swiping
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(bibleAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

}