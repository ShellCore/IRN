package com.shellcore.android.irn.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Cesar on 02/08/2017.
 */

public interface IRNService {

    @POST("catalog/irn")
    Call<IRNResponse> getIRNTables(@Body IRNRequest request);
}
