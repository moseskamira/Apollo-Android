package com.example.apolloandroid.connector;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;

public class ApolloConnector {

    private static final String BASE_URL = "https://graphql-api-spring-boot.herokuapp.com/graphql";

    public static ApolloClient setupApollo(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        return ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build();
    }


}
