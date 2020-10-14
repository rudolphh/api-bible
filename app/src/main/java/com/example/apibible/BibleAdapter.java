package com.example.apibible;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apibible.bible.Bible;
import com.example.apibible.util.ItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BibleAdapter extends RecyclerView.Adapter<BibleAdapter.BibleHolder>
        implements ItemTouchHelperAdapter {

    private static RecyclerView recyclerView;

    private List<Bible> bibles = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public BibleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View bibleView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bible_item, parent, false);


        recyclerView = parent.findViewById(R.id.recycler_view);

        return new BibleHolder(bibleView);
    }

    @Override
    public void onBindViewHolder(@NonNull BibleHolder holder, int position) {

        Bible currentBible = bibles.get(position);

        holder.tvName.setText(currentBible.getName());
        holder.tvAbbrev.setText(currentBible.getAbbreviation());
        holder.tvDesc.setText(currentBible.getDescription());

        holder.iv_fav.setOnClickListener(view -> {
            onItemMove(position, 0);
            notifyItemRangeChanged(0, position+1);

            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            assert layoutManager != null;
            layoutManager.scrollToPositionWithOffset(0, 0);

            Log.i("position", String.valueOf(position));
        });

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

    @Override
    public void onItemDismiss(int position) {
        bibles.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(bibles, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(bibles, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }



    static class BibleHolder extends RecyclerView.ViewHolder{

        private View bibleView;

        private ImageView iv_fav;
        private TextView tvName;
        private TextView tvAbbrev;
        private TextView tvDesc;

        public BibleHolder(@NonNull View itemView) {
            super(itemView);

            bibleView = itemView;// handle on the bible cardView item

            iv_fav = bibleView.findViewById(R.id.iv_fav);
            tvName = bibleView.findViewById(R.id.tv_name);
            tvAbbrev = bibleView.findViewById(R.id.tv_abbrev);
            tvDesc = bibleView.findViewById(R.id.tv_desc);

        }

    }
}
