package com.example.apibible.bible.adapters;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apibible.R;
import com.example.apibible.bible.models.Bible;
import com.example.apibible.util.ItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
        holder.setBible(currentBible);

        // check whether the current bible in the holder is a favorite
        boolean isFavorite = Objects.equals(currentBible.getAdditionalProperties().get("FavoriteChecked"), true);
        Log.i("Current FavoriteChecked", "Current FavoriteChecked for " + currentBible.getName() + " - " + isFavorite);

        // set the holder's favorite star accordingly within the holder
        holder.tb_fav.setChecked(isFavorite);

        // when the favorite star is clicked, set the FavoriteChecked property of the current bible
        holder.tb_fav.setOnClickListener(view -> {
            currentBible.setAdditionalProperty("FavoriteChecked", true);
            Log.i("FavoriteChecked", "On Bible : " + currentBible.getName() + " - true");
        });

        holder.tvName.setText(currentBible.getName());
        holder.tvAbbrev.setText(currentBible.getAbbreviation());
        holder.tvDesc.setText(currentBible.getDescription());
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

    public interface RecyclerViewClickListener {
        void onClick(String bibleId);
    }

    static class BibleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View bibleView;
        private Bible bible;

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
            mListener.onClick(bible.getId());
        }

        public void setBible(Bible aBible){
            this.bible = aBible;
        }

        public Bible getBible(){
            return this.bible;
        }
    }
}
