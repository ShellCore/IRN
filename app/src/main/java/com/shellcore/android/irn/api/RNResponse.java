package com.shellcore.android.irn.api;

import com.google.gson.annotations.Expose;
import com.shellcore.android.irn.db.entities.Afore;

import java.util.List;

/**
 * Created by Cesar on 02/08/2017.
 */

public class RNResponse {

    @Expose
    private List<Afore> rendimientoNeto;

    public List<Afore> getRendimientoNeto() {
        return rendimientoNeto;
    }

    public void setRendimientoNeto(List<Afore> rendimientoNeto) {
        this.rendimientoNeto = rendimientoNeto;
    }
}
