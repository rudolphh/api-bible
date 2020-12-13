package com.example.apibible.book;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apibible.R;
import com.example.apibible.bible.viewmodels.BiblesViewModel;
import com.example.apibible.chapter.ChaptersFragment;

import java.util.Objects;

public class BooksFragment extends Fragment {

    RecyclerView recyclerView;
    String bibleId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View booksFragmentUIView = inflater.inflate(R.layout.books_fragment, container, false);
        setupRecyclerViewWithin(booksFragmentUIView);
        retrieveBible(getArguments());

        return booksFragmentUIView;
    }

    private void setupRecyclerViewWithin(View uiView){
        recyclerView = uiView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void retrieveBible(Bundle arguments){
        bibleId = arguments.getString("BibleId");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        BiblesViewModel biblesViewModel = new ViewModelProvider.AndroidViewModelFactory(Objects.requireNonNull(
                getActivity()).getApplication()).create(BiblesViewModel.class);

        BookAdapter.BookViewClickListener listener = createBookViewClickListener();
        BookAdapter bookAdapter = new BookAdapter(listener);
        recyclerView.setAdapter(new BookAdapter(listener));

        biblesViewModel.getAllBibleBooks(bibleId).observe(this, bookAdapter::setBooks);
    }

    private BookAdapter.BookViewClickListener createBookViewClickListener(){

        return (book) -> {
            Fragment chaptersFragment = setupChapterFragmentWithArguments(book.getId());
            replaceBooksFragmentWith(chaptersFragment);
        };
    }

    private Fragment setupChapterFragmentWithArguments(String bookId){
        Fragment fragment = new ChaptersFragment();
        Bundle args = new Bundle();

        args.putString("BibleId", bibleId);
        args.putString("BookId", bookId);
        fragment.setArguments(args);

        return fragment;
    }

    private void replaceBooksFragmentWith(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(BooksFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

}