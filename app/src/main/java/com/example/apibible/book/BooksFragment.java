package com.example.apibible.book;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apibible.R;
import com.example.apibible.bible.viewmodels.BiblesViewModel;

public class BooksFragment extends Fragment {

    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.books_fragment, container, false);

        recyclerView = root.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        BiblesViewModel biblesViewModel = new ViewModelProvider.AndroidViewModelFactory(
                getActivity().getApplication()).create(BiblesViewModel.class);;

        // set up adapter
        BookAdapter.RecyclerViewClickListener listener = (view, position) -> {
            Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_LONG).show();

            // TODO: Load ChaptersFragment
//            Fragment fragment = new BooksFragment();
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.container, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
        };

        BookAdapter bookAdapter = new BookAdapter(listener);
        recyclerView.setAdapter(bookAdapter);

        biblesViewModel.getAllBibleBooks().observe(this, bookList -> {
            bookAdapter.setBooks(bookList);
        });
    }
}