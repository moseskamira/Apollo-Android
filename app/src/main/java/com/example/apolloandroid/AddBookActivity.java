package com.example.apolloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apolloandroid.connector.ApolloConnector;

import org.jetbrains.annotations.NotNull;

public class AddBookActivity extends AppCompatActivity {
    EditText bookTitleEt;
    EditText bookIsbnEt;
    EditText bookPageCountEt;
    EditText bookAuthorEt;
    Button saveBookBtn;

    String bookTitle;
    String bookIsbn;
    int bookPageCount;
    String bookAuthorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        bookTitleEt = findViewById(R.id.title_ed);
        bookIsbnEt = findViewById(R.id.isbn_ed);
        bookPageCountEt = findViewById(R.id.page_count_ed);
        bookAuthorEt = findViewById(R.id.author_ed);
        saveBookBtn = findViewById(R.id.add_book_btn);
        if (saveBookBtn != null) {
            saveBookBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerBook();
                }
            });
        }

    }

    private void registerBook() {
        bookTitle = bookTitleEt.getText().toString();
        bookIsbn = bookIsbnEt.getText().toString();
        bookPageCount = Integer.parseInt(bookPageCountEt.getText().toString());
        bookAuthorId = bookAuthorEt.getText().toString();

        postBookDetails(bookTitle, bookIsbn, bookPageCount, bookAuthorId);
    }

    private void postBookDetails(String bT, String bI, int bPgC, String ba) {
        ApolloConnector.setupApollo().mutate(new PostBookDataMutation(bT, bI, bPgC, ba)).enqueue(
                new ApolloCall.Callback<PostBookDataMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<PostBookDataMutation.Data> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AddBookActivity.this, "Book Added Successfully !"
                                , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddBookActivity.this, MainActivity.class));
                    }
                });

            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Thread thread = new Thread(){
                    public void run(){
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(AddBookActivity.this, "Failed To Add Book"
                                        , Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };
                thread.start();
            }
        });

    }

}
