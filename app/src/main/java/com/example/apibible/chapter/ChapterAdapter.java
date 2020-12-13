package com.example.apibible.chapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apibible.R;
import com.example.apibible.chapter.models.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterHolder> {

    private static ChapterAdapter.ChapterViewClickListener mListener;
    private List<Chapter> chapters = new ArrayList<>();

    public ChapterAdapter(ChapterAdapter.ChapterViewClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public ChapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chapterView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chapter_item, parent, false);

        Log.i("createViewHolder", "create view holder called");

        return new ChapterAdapter.ChapterHolder(chapterView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterHolder holder, int position) {
        Chapter currentChapter = chapters.get(position);
        holder.setChapter(currentChapter);

        holder.tvName.setText(currentChapter.getData().getReference());
        holder.tvAbbrev.setText(currentChapter.getData().getBookId());
        holder.tvDesc.setText(currentChapter.getData().getNumber());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setChapters(List<Chapter> chapters){
        this.chapters = chapters;
        notifyDataSetChanged();
    }

    public interface ChapterViewClickListener {
        void onClick(Chapter chapter);
    }

    static class ChapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Chapter chapter;
        private TextView tvName;
        private TextView tvAbbrev;
        private TextView tvDesc;
        
        public ChapterHolder(@NonNull View chapterItemView) {
            super(chapterItemView);
            chapterItemView.setOnClickListener(this);

            tvName = chapterItemView.findViewById(R.id.tv_name);
            tvAbbrev = chapterItemView.findViewById(R.id.tv_abbrev);
            tvDesc = chapterItemView.findViewById(R.id.tv_desc);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(this.chapter);
        }
        
        public void setChapter(Chapter chapter){
            this.chapter = chapter;
        }
    }
}
