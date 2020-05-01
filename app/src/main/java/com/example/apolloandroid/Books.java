package com.example.apolloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Books extends AppCompatActivity {

    ArrayList<FindAvailableBooksQuery.FindAllBook> booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        getAllAvailableBooks();
    }




    private void getAllAvailableBooks() {
        booksList = new ArrayList<>();
        booksList.clear();

        ApolloConnector.setupApollo().query(
                FindAvailableBooksQuery
                        .builder()
                        .build())
                .enqueue(
                        new ApolloCall.Callback<FindAvailableBooksQuery.Data>() {
                            @Override
                            public void onResponse(@NotNull Response<FindAvailableBooksQuery.Data> response) {
                                if (response.data().findAllBooks.size() > 0) {
                                    for (int j=0; j < response.data().findAllBooks.size(); j++) {
                                        booksList.add(response.data().findAllBooks.get(j));
                                        Log.d("MYBOOKTITLE", booksList.get(0).title);
                                    }
                                }

                            }

                            @Override
                            public void onFailure(@NotNull ApolloException e) {

                            }
                        }


                );

    }


}
