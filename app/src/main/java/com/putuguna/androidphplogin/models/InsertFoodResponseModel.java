package com.putuguna.androidphplogin.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by putuguna on 1/24/2017.
 */

public class InsertFoodResponseModel {
    @SerializedName("success")
    private int status;
    @SerializedName("message")
    private String message;

    public InsertFoodResponseModel(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public InsertFoodResponseModel() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
