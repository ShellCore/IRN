package com.shellcore.android.irn.main;

import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.shellcore.android.irn.api.IRNRequest;
import com.shellcore.android.irn.api.IRNResponse;
import com.shellcore.android.irn.api.IRNService;
import com.shellcore.android.irn.db.entities.Afore;
import com.shellcore.android.irn.libs.base.EventBus;
import com.shellcore.android.irn.main.events.MainEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cesar on 02/08/2017.
 */

public class MainRepositoryImpl implements MainRepository {

    private EventBus eventBus;
    private IRNService service;

    private List<Afore> afores;

    public MainRepositoryImpl(EventBus eventBus, IRNService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void checkIrnValidation() {
        FlowCursorList<Afore> storedAfores = new FlowCursorList.Builder<Afore>(Afore.class)
                .cacheModels(true)
                .build();
        if (storedAfores.isEmpty()) {
            post(MainEvent.QUERY_ERROR);
        } else {
            Afore afore = storedAfores.getItem(0);
            if (afore.isvalid()) {
                post(MainEvent.VALIDATION_SUCCESS);
            } else {
                post(MainEvent.VALIDATION_ERROR);
            }
        }

        storedAfores.close();
    }

    @Override
    public void updateIrnList() {
        int[] edades = {30, 40, 50, 60};
        afores = new ArrayList<>();
        try {
            for (int edad : edades) {
                updateIrnByAge(edad);
            }
        } catch (Exception e) {
            post(MainEvent.DOWNLOAD_ERROR, e.getMessage());
        }

    }

    private void updateIrnByAge(int edad) throws Exception {
        final String[] errorMessage = {null};
        final IRNRequest request = new IRNRequest();
        request.setEdad(edad);
        request.setFecha(new Date().getTime());

        Call<IRNResponse> call = service.getIRNTables(request);
        call.enqueue(new Callback<IRNResponse>() {
            @Override
            public void onResponse(Call<IRNResponse> call, Response<IRNResponse> response) {

                if (response.isSuccessful()) {
                    IRNResponse irnResponse = response.body();
                    List<Afore> list = irnResponse.getResponse().getRendimientoNeto();
                    if (list.size() > 0) {
                        replaceIRNList(list);
                    } else {
                        post(MainEvent.DOWNLOAD_ERROR, response.message());
                    }
                } else {
                    post(MainEvent.DOWNLOAD_ERROR, response.message());
                }
            }

            @Override
            public void onFailure(Call<IRNResponse> call, Throwable t) {
                post(MainEvent.DOWNLOAD_ERROR, t.getLocalizedMessage());
            }
        });
    }

    private void replaceIRNList(List<Afore> list) {
        for (Afore afore : list) {
            afore.save();
        }
        if (list.get(0).isvalid()) {
            post(MainEvent.SUCCESS);
        } else {
            post(MainEvent.VALIDATION_ERROR);
        }
    }

    private void post(int type, String error) {
        MainEvent event = new MainEvent();
        event.setType(type);
        event.setError(error);

        eventBus.post(event);
    }

    private void post(int type) {
        post(type, null);
    }
}
