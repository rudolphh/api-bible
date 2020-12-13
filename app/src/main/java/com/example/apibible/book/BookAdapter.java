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

    private static BookViewClickListener mListener;
    private List<Book> books = new ArrayList<>();

    public BookAdapter(BookViewClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View bookView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);

        Log.i("createViewHolder", "create view holder called");

        return new BookHolder(bookView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {

        Book currentBook = books.get(position);
        holder.setBook(currentBook);

        holder.tvName.setText(currentBook.getName());
        holder.tvAbbrev.setText(currentBook.getAbbreviation());
        holder.tvDesc.setText(currentBook.getNameLong());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> books){
        this.books = books;
        notifyDataSetChanged();
    }

    public interface BookViewClickListener {
        void onClick(Book book);
    }

    static class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Book book;

        private TextView tvName;
        private TextView tvAbbrev;
        private TextView tvDesc;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            tvName = itemView.findViewById(R.id.tv_name);
            tvAbbrev = itemView.findViewById(R.id.tv_abbrev);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(this.book);
        }

        public void setBook(Book book) {
            this.book = book;
        }

    }
}
