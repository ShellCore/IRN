package com.shellcore.android.irn.api;

import com.google.gson.annotations.Expose;

/**
 * Created by Cesar on 02/08/2017.
 */

public class IRNResponse {

    @Expose
    private int status;
    @Expose
    private String statusText;
    @Expose
    private boolean result;
    @Expose
    private int code;
    @Expose
    private RNResponse response;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RNResponse getResponse() {
        return response;
    }

    public void setResponse(RNResponse response) {
        this.response = response;
    }
}
