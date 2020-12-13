package com.example.apibible.book;

import android.os.Bundle;

import androidx.annotation.NonNull;
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

import java.util.Objects;

public class BooksFragment extends Fragment {

    LayoutInflater layoutInflater;
    ViewGroup viewGroupContainer;

    View booksFragment;
    RecyclerView recyclerView;
    String bibleId;

    BiblesViewModel biblesViewModel;
    BookAdapter.RecyclerViewClickListener listener;
    BookAdapter bookAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.layoutInflater = inflater;
        this.viewGroupContainer = container;

        inflateFragmentLayout(R.layout.books_fragment);
        setupRecyclerView(R.id.recycler_view);
        retrieveBibleId();

        return booksFragment;
    }

    private void inflateFragmentLayout(int resource){
        booksFragment = layoutInflater.inflate(resource, viewGroupContainer, false);
    }

    private void setupRecyclerView(int resource){
        recyclerView = booksFragment.findViewById(resource);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void retrieveBibleId(){
        bibleId = Objects.requireNonNull(getArguments()).getString("BibleId");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        instantiateBiblesViewModel();
        createBookHolderItemClickListener();
        instantiateBookAdapter();
        setRecyclerViewAdapter();
        setAllBibleBooksInBookAdapter();
    }

    private void instantiateBiblesViewModel(){
        biblesViewModel = new ViewModelProvider.AndroidViewModelFactory(
                Objects.requireNonNull(getActivity()).getApplication()).create(BiblesViewModel.class);
    }

    private void createBookHolderItemClickListener(){

        listener = (view, position) -> {
            Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_LONG).show();

            // TODO: Load ChaptersFragment
//            Fragment fragment = new BooksFragment();
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.container, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
        };
    }

    private void instantiateBookAdapter(){
        bookAdapter = new BookAdapter(listener);
    }

    private void setRecyclerViewAdapter(){
        recyclerView.setAdapter(bookAdapter);
    }

    private void setAllBibleBooksInBookAdapter(){
        biblesViewModel.getAllBibleBooks(bibleId).observe(this, bookAdapter::setBooks);

    }
}