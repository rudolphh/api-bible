package com.example.apibible.bible.adapters;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apibible.R;
import com.example.apibible.bible.models.Bible;
import com.example.apibible.util.ItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BibleAdapter extends RecyclerView.Adapter<BibleAdapter.BibleHolder>
        implements ItemTouchHelperAdapter {

    private static RecyclerView recyclerView;
    private static RecyclerViewClickListener mListener;

    private List<Bible> bibles = new ArrayList<>();
    private Context context;

    public BibleAdapter(RecyclerViewClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public BibleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View bibleView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bible_item, parent, false);

        recyclerView = parent.findViewById(R.id.recycler_view);

        Log.i("createViewHolder", "create view holder called");

        return new BibleHolder(bibleView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BibleHolder holder, int position) {

        Bible currentBible = bibles.get(position);
        boolean isFavorite = currentBible.getAdditionalProperties().get("FavoriteChecked").equals(true);
        Log.i("Current FavoriteChecked", "Current FavoriteChecked for " + currentBible.getName() + " - " + isFavorite);

        holder.tb_fav.setChecked(isFavorite);

        holder.tb_fav.setOnClickListener(view -> {
            currentBible.setAdditionalProperty("FavoriteChecked", true);
            Log.i("FavoriteChecked", "On Bible : " + currentBible.getName() + " - true");
        });

        if(holder instanceof BibleHolder) {
            holder.tvName.setText(currentBible.getName());
            holder.tvAbbrev.setText(currentBible.getAbbreviation());
            holder.tvDesc.setText(currentBible.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return bibles.size();
    }

    public void setBibles(List<Bible> bibles){
        this.bibles = bibles;
        for(Bible bible : bibles){
            bible.setAdditionalProperty("FavoriteChecked", false);
        }
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

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    static class BibleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View bibleView;
        private RecyclerViewClickListener mListener;

        private ToggleButton tb_fav;
        private TextView tvName;
        private TextView tvAbbrev;
        private TextView tvDesc;

        public BibleHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;

            bibleView = itemView;// handle on the bible cardView item
            bibleView.setOnClickListener(this);

            tb_fav = bibleView.findViewById(R.id.tb_fav);
            //tb_fav.setOnClickListener(this);
            tvName = bibleView.findViewById(R.id.tv_name);
            tvAbbrev = bibleView.findViewById(R.id.tv_abbrev);
            tvDesc = bibleView.findViewById(R.id.tv_desc);

        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }
}
