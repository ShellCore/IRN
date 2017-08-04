package com.shellcore.android.irn.api;

import com.shellcore.android.irn.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cesar on 02/08/2017.
 */

public class IRNClient {

    private static final String BASE_URL = BuildConfig.SERVER_URL;

    private Retrofit retrofit;

    public IRNClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public IRNService getIrnService() {
        return retrofit.create(IRNService.class);
    }
}
