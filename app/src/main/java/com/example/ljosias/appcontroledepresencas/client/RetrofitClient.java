package com.example.ljosias.appcontroledepresencas.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ljosias on 02/06/2017.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;

//    private static final String  baseUrl =  "http://172.17.72.45:8090/" ;
    private static final String  baseUrl =  "http://192.168.0.12:8090/" ;

    public static Retrofit getClient() {
        if (retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
