package com.example.apolloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllAuthors();
    }


    private void getAllAuthors() {

        ApolloConnector.setupApollo().query(
                FindAllAuthorsQuery
                        .builder()
                        .build())
                .enqueue(new ApolloCall.Callback<FindAllAuthorsQuery.Data>() {


                    @Override
                    public void onResponse(@NotNull Response<FindAllAuthorsQuery.Data> response) {
                        Log.d("MYAUTHOR ", response.data().findAllAuthors.get(1).firstName);

                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {

                    }


    });
}}
