package com.putuguna.androidphplogin.apiservices;

import com.putuguna.androidphplogin.models.InsertFoodResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by putuguna on 1/24/2017.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("food/InsertFood.php")
    Call<InsertFoodResponseModel> insertFood(@Field("foodname") String foodName, @Field("foodqty") String foodQty);
}
