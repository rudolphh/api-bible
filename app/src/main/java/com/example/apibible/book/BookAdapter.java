package com.example.apibible.book;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apibible.R;
import com.example.apibible.book.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    private static RecyclerViewClickListener mListener;

    private List<Book> books = new ArrayList<>();

    public BookAdapter(RecyclerViewClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View bookView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);

        Log.i("createViewHolder", "create view holder called");

        return new BookHolder(bookView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {

        Book currentBook = books.get(position);

        holder.tvName.setText(currentBook.getName());
        holder.tvAbbrev.setText(currentBook.getAbbreviation());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> books){
        this.books = books;
        notifyDataSetChanged();
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    static class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View bookView;
        private RecyclerViewClickListener mListener;

        private TextView tvName;
        private TextView tvAbbrev;

        public BookHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;

            bookView = itemView;// handle on the bible cardView item
            bookView.setOnClickListener(this);

            tvName = bookView.findViewById(R.id.tv_name);
            tvAbbrev = bookView.findViewById(R.id.tv_abbrev);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }
}
