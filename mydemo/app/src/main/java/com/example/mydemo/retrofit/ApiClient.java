package com.example.mydemo.retrofit;


import com.example.mydemo.Util.Constant;
import com.example.mydemo.model.CouponRequest;
import com.example.mydemo.model.CouponResponse;
import com.example.mydemo.model.StoreResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiClient {
    private RetrofitInterface apiInterface;
    private static ApiClient apiClient;
    private Gson gson;


    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request.Builder ongoing = chain.request().newBuilder();
                    ongoing.addHeader("Content-Type", "application/json");
                    return chain.proceed(ongoing.build());
                })
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(360, TimeUnit.SECONDS)
                .readTimeout(360, TimeUnit.SECONDS)
                .build();
    }


    public ApiClient() {
        this.gson = new GsonBuilder()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASR_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
        this.apiInterface = retrofit.create(RetrofitInterface.class);
    }

    public static ApiClient getInstance() {
        if (apiClient == null) {
            setInstance(new ApiClient());
        }
        return apiClient;
    }

    public static void setInstance(ApiClient apiClient) {
        ApiClient.apiClient = apiClient;
    }


    public void getStoreData(Callback<StoreResponse> callback) {
        Call<StoreResponse> call = this.apiInterface.getStoreResponse();
        call.enqueue(callback);
    }


    public void getCouponList(CouponRequest couponRequest, Callback<List<CouponResponse>> callback) {
        Call<List<CouponResponse>> call = this.apiInterface.getcouponlist(couponRequest);
        call.enqueue(callback);
    }



}


