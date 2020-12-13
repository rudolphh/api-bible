package com.example.apibible.chapter;

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
import com.example.apibible.chapter.ChapterAdapter;

import java.util.Objects;

public class ChaptersFragment extends Fragment {

    LayoutInflater layoutInflater;
    ViewGroup viewGroupContainer;

    View chaptersFragment;
    RecyclerView recyclerView;
    String bookId;

    BiblesViewModel biblesViewModel;
    ChapterAdapter.RecyclerViewClickListener listener;
    ChapterAdapter chapterAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.layoutInflater = inflater;
        this.viewGroupContainer = container;

        inflateFragmentLayout(R.layout.chapters_fragment);
        setupRecyclerView(R.id.recycler_view);
        retrieveBookId(); 

        return chaptersFragment;
    }

    private void inflateFragmentLayout(int resource){
        chaptersFragment = layoutInflater.inflate(resource, viewGroupContainer, false);
    }

    private void setupRecyclerView(int resource){
        recyclerView = chaptersFragment.findViewById(resource);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void retrieveBookId(){
        bookId = Objects.requireNonNull(getArguments()).getString("BookId");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        instantiateBiblesViewModel();
        createChapterHolderItemClickListener();
        instantiateChapterAdapter();
        setRecyclerViewAdapter();
        setAllBibleBooksInChapterAdapter();
    }

    private void instantiateBiblesViewModel(){
        biblesViewModel = new ViewModelProvider.AndroidViewModelFactory(
                Objects.requireNonNull(getActivity()).getApplication()).create(BiblesViewModel.class);
    }

    private void createChapterHolderItemClickListener(){

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

    private void instantiateChapterAdapter(){
        chapterAdapter = new ChapterAdapter(listener);
    }

    private void setRecyclerViewAdapter(){
        recyclerView.setAdapter(chapterAdapter);
    }

    private void setAllBibleBooksInChapterAdapter(){
        //biblesViewModel.getAllBibleBooks(bookId).observe(this, chapterAdapter::setChapters);

    }
}