package com.example.apolloandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apolloandroid.adapters.BooksAdapter;
import com.example.apolloandroid.models.MyBook;
import com.example.apolloandroid.connector.ApolloConnector;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Books extends AppCompatActivity {
    ArrayList<MyBook> booksList;
    RecyclerView booksRecyclerView;
    BooksAdapter booksAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        booksList = new ArrayList<>();
        booksRecyclerView = findViewById(R.id.books_recycler_view);
        initializeBooksRecyclerView();
        progressDialog = new ProgressDialog(this);
        getAllAvailableBooks();
    }

    private void initializeBooksRecyclerView() {
        booksRecyclerView.setHasFixedSize(true);
//        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void getAllAvailableBooks() {
        progressDialog.setMessage("Fetching Books");
        progressDialog.show();
        ApolloConnector.setupApollo().query(
                FindAvailableBooksQuery
                        .builder()
                        .build())
                .enqueue(new ApolloCall.Callback<FindAvailableBooksQuery.Data>() {
                            @Override
                            public void onResponse(@NotNull Response<FindAvailableBooksQuery.Data> response) {
                                progressDialog.dismiss();

                                if (response.data().findAllBooks.size() > 0) {
                                    for (int j=0; j < response.data().findAllBooks.size(); j++) {
                                        MyBook myBook = new MyBook();
                                        myBook.setTitle(response.data().findAllBooks.get(j).title());
                                        myBook.setIsbn(response.data().findAllBooks.get(j).isbn());
                                        booksList.add(myBook);
                                    }

                                    if (!booksList.isEmpty()) {
                                        runOnUiThread(new Runnable() {

                                            @Override
                                            public void run() {
                                                booksAdapter = new BooksAdapter(getApplicationContext(), booksList);
                                                booksRecyclerView.setAdapter(booksAdapter);
                                            }
                                        });
                                    }else {
                                        Log.d("EMPTY", "LIST");
                                    }
                                }

                            }

                            @Override
                            public void onFailure(@NotNull ApolloException e) {
                                progressDialog.dismiss();
                                e.printStackTrace();

                            }
                        }
                );
    }



}
