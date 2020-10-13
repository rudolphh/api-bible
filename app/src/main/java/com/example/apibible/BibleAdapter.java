package com.example.apibible;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apibible.bible.Bible;

import java.util.ArrayList;
import java.util.List;

public class BibleAdapter extends RecyclerView.Adapter<BibleAdapter.BibleHolder> {

    private List<Bible> bibles = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public BibleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View bibleView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bible_item, parent, false);

        return new BibleHolder(bibleView);
    }

    @Override
    public void onBindViewHolder(@NonNull BibleHolder holder, int position) {

        Bible currentBible = bibles.get(position);

        holder.tvName.setText(currentBible.getName());
        holder.tvAbbrev.setText(currentBible.getAbbreviation());
        holder.tvDesc.setText(currentBible.getDescription());

        // when a bible cardView is clicked, go to the books activity (or fragment)
        holder.bibleView.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return bibles.size();
    }

    public void setBibles(List<Bible> bibles){
        this.bibles = bibles;
        notifyDataSetChanged();
    }

    static class BibleHolder extends RecyclerView.ViewHolder{

        private View bibleView;

        private TextView tvName;
        private TextView tvAbbrev;
        private TextView tvDesc;

        public BibleHolder(@NonNull View itemView) {
            super(itemView);

            bibleView = itemView;// handle on the bible cardView item

            tvName = bibleView.findViewById(R.id.tv_name);
            tvAbbrev = bibleView.findViewById(R.id.tv_abbrev);
            tvDesc = bibleView.findViewById(R.id.tv_desc);

        }

    }
}
