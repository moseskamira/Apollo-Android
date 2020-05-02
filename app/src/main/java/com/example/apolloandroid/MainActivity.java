package com.example.apolloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button viewAuthors, viewBooks, addAuthor, addBook;
    Intent navigationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewAuthors = findViewById(R.id.view_authors);
        viewBooks = findViewById(R.id.view_books);
        addAuthor = findViewById(R.id.add_author);
        addBook = findViewById(R.id.add_book);

        viewAuthors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAllAuthorsView();

            }
        });

        viewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotToAllBooksView();
            }
        });

        addAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddAuthorView();

            }
        });

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddBookView();

            }
        });

    }


    private void goToAllAuthorsView() {
       navigationIntent = new Intent(this, Authors.class);
       startActivity(navigationIntent);
    }

    private void gotToAllBooksView() {
        Log.d("YOUCLICKED", "ALLBOOKS");
        navigationIntent = new Intent(this, Books.class);
        startActivity(navigationIntent);
    }

    private void goToAddAuthorView() {
        navigationIntent = new Intent(this, AddAuthorActivity.class);
        startActivity(navigationIntent);

    }

    private void goToAddBookView() {
        navigationIntent = new Intent(this, AddBookActivity.class);
        startActivity(navigationIntent);
    }


}
