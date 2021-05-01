package com.example.mydemo.retrofit;

import com.example.mydemo.model.CouponRequest;
import com.example.mydemo.model.CouponResponse;
import com.example.mydemo.model.StoreResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @GET("restaurant/3026")
    Call<StoreResponse> getStoreResponse();

    @POST("/user/offers/couponlist")
    Call<List<CouponResponse>> getcouponlist(@Body CouponRequest couponRequest);



}
