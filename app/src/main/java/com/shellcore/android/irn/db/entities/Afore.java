package com.shellcore.android.irn.db.entities;

import android.util.Log;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.shellcore.android.irn.db.IRNDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cesar on 02/08/2017.
 */

@Table(database = IRNDatabase.class)
public class Afore extends BaseModel {

    @PrimaryKey
    private int idHojaIRN;

    @Column
    private String descripcionCorta;

    @Column
    private int numeroHoja;

    @Column
    private double rendimientoNeto;

    @Column
    private String fechaStrFinVigencia;

    @Column
    private String fechaStrInicioVigencia;

    public int getIdHojaIRN() {
        return idHojaIRN;
    }

    public void setIdHojaIRN(int idHojaIRN) {
        this.idHojaIRN = idHojaIRN;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public int getNumeroHoja() {
        return numeroHoja;
    }

    public void setNumeroHoja(int numeroHoja) {
        this.numeroHoja = numeroHoja;
    }

    public double getRendimientoNeto() {
        return rendimientoNeto;
    }

    public void setRendimientoNeto(double rendimientoNeto) {
        this.rendimientoNeto = rendimientoNeto;
    }

    public String getFechaStrFinVigencia() {
        return fechaStrFinVigencia;
    }

    public void setFechaStrFinVigencia(String fechaStrFinVigencia) {
        this.fechaStrFinVigencia = fechaStrFinVigencia;
    }

    public String getFechaStrInicioVigencia() {
        return fechaStrInicioVigencia;
    }

    public void setFechaStrInicioVigencia(String fechaStrInicioVigencia) {
        this.fechaStrInicioVigencia = fechaStrInicioVigencia;
    }

    public boolean isvalid() {
        Date actual = new Date();
        Date vigencia = getFechaFinVigencia();

        if (actual.compareTo(vigencia) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    private Date getFechaFinVigencia() {
        Date vigencia = null;
        try {
            vigencia = new SimpleDateFormat("dd-MM-yyyy").parse(fechaStrFinVigencia);
        } catch (ParseException e) {
            Log.e("AFORE DATE PARSE", e.getLocalizedMessage());
        } finally {
            return vigencia;
        }
    }
}
