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

import com.example.apibible.R;
import com.example.apibible.bible.viewmodels.BiblesViewModel;

import java.util.Objects;

public class ChaptersFragment extends Fragment {

    RecyclerView recyclerView;
    String bibleId;
    String bookId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View chaptersFragmentUIView = inflater.inflate(R.layout.chapters_fragment, container, false);
        setupRecyclerViewWithin(chaptersFragmentUIView);
        retrieveBibleAndBook(getArguments());

        return chaptersFragmentUIView;
    }

    private void setupRecyclerViewWithin(View uiView){
        recyclerView = uiView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void retrieveBibleAndBook(Bundle arguments){
        bibleId = arguments.getString("BibleId");
        bookId = arguments.getString("BookId");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        BiblesViewModel biblesViewModel = new ViewModelProvider.AndroidViewModelFactory(Objects.requireNonNull(
                getActivity()).getApplication()).create(BiblesViewModel.class);

        ChapterAdapter.ChapterViewClickListener listener = createChapterViewClickListener();
        ChapterAdapter chapterAdapter = new ChapterAdapter(listener);
        recyclerView.setAdapter(chapterAdapter);

        biblesViewModel.getAllBibleBookChapters(bibleId, bookId).observe(this, chapterAdapter::setChapters);
    }

    private ChapterAdapter.ChapterViewClickListener createChapterViewClickListener(){

        return (chapter) -> {

            // TODO: Load ChaptersFragment
//            Fragment fragment = new BooksFragment();
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.container, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
        };
    }

}