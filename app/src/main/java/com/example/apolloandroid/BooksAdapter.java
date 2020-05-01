package com.example.apolloandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {
    private Context mContext;
    private ArrayList<MyBook> booksList;

    BooksAdapter(Context context, ArrayList<MyBook> mBooksList) {
        this.mContext = context;
        this.booksList = mBooksList;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View booksView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_books_view,
                parent, false);
        RecyclerView.LayoutParams booksLayoutParams = new RecyclerView.LayoutParams(ViewGroup.
                LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        booksView.setLayoutParams(booksLayoutParams);
        return new BooksViewHolder(booksView);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
        holder.bookTitle.setText(booksList.get(position).getTitle());
        holder.bookIsbn.setText(booksList.get(position).getIsbn());
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    class BooksViewHolder extends RecyclerView.ViewHolder{
        TextView bookTitle;
        TextView bookIsbn;

        BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.book_title);
            bookIsbn = itemView.findViewById(R.id.book_isbn);
        }
    }
}
