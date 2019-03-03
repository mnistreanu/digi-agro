package com.arobs.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mihail.gorgos on 19.07.2018.
 */
public class PayloadModel<T> implements Serializable {

    public final static String STATUS_SUCCESS = "success";
    public final static String STATUS_WARNING = "warning";
    public final static String STATUS_ERROR = "error";

    private String status;
    private String message;
    private List<T> payload;

    public PayloadModel() {
        this.message = "";
    }

    public PayloadModel(String status) {
        this.status = status;
        this.message = "";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getStatusSuccess() {
        return STATUS_SUCCESS;
    }

    public List<T> getPayload() {
        return payload;
    }

    public void setPayload(List<T> payload) {
        this.payload = payload;
    }
}
