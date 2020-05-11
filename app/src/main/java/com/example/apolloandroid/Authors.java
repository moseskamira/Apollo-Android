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
import com.example.apolloandroid.adapters.AuthorsAdapter;
import com.example.apolloandroid.models.MyAuthor;
import com.example.apolloandroid.connector.ApolloConnector;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Authors extends AppCompatActivity {
    ArrayList<MyAuthor> authorsList;
    RecyclerView authorsRecyclerView;
    AuthorsAdapter myAuthorsAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);
        authorsRecyclerView = findViewById(R.id.authors_recycler_view);
        authorsList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        getAllAuthors();
    }

    private void getAllAuthors() {
        progressDialog.setMessage("Fetching Authors");
        progressDialog.show();
        ApolloConnector.setupApollo().query(FindAvailableAuthorsQuery
                .builder()
                .build())
                .enqueue(new ApolloCall.Callback<FindAvailableAuthorsQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<FindAvailableAuthorsQuery.Data> response) {

                        assert response.data() != null;
                        if (response.data().findAllAuthors.size() > 0) {
                            progressDialog.dismiss();
                            for (int i = 0; i < response.data().findAllAuthors.size(); i++) {
                                MyAuthor myAuthor = new MyAuthor();
                                myAuthor.setFirstName(response.data().findAllAuthors.get(i).firstName);
                                myAuthor.setLastName(response.data().findAllAuthors.get(i).lastName);
                                authorsList.add(myAuthor);
                            }

                            if (!authorsList.isEmpty()) {
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                       initializeRecyclerView(authorsList);
                                    }
                                });
                            } else {
                                Log.d("EMPTY", "LIST");
                            }

                        } else {
                            Log.d("NOOBJECT", "FETCHED");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        e.printStackTrace();

                    }
                });
    }

    private void initializeRecyclerView(ArrayList<MyAuthor> myAuthorsList) {
        authorsRecyclerView.setHasFixedSize(true);
        myAuthorsAdapter = new AuthorsAdapter(getApplicationContext(), myAuthorsList);
        authorsRecyclerView.setAdapter(myAuthorsAdapter);
        authorsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }
}




