package com.example.apibible.chapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apibible.book.models.Book;
import com.example.apibible.chapter.models.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterHolder> {

    private static ChapterAdapter.RecyclerViewClickListener mListener;

    private List<Chapter> chapters = new ArrayList<>();

    public ChapterAdapter(ChapterAdapter.RecyclerViewClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public ChapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setChapters(List<Chapter> chapters){
        this.chapters = chapters;
        notifyDataSetChanged();
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    static class ChapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ChapterHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
