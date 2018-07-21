package com.arobs.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by mihail.gorgos on 19.07.2018.
 */
public class PayloadModel<T> implements Serializable {

    public final static String STATUS_SUCCESS = "success";
    public final static String STATUS_WARNING = "warning";
    public final static String STATUS_ERROR = "error";

    private String status;
    private String message;
    private T[] payload;

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

    public T[] getPayload() {
        return payload;
    }

    public void setPayload(T[] payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "PayloadModel{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", payload=" + Arrays.toString(payload) +
                '}';
    }
}
