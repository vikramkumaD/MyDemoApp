package com.example.mydemo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydemo.adapter.CateroryParentAdapter;
import com.example.mydemo.adapter.CouponAdapter;
import com.example.mydemo.model.Categories;
import com.example.mydemo.retrofit.ApiClient;
import com.example.mydemo.model.CouponRequest;
import com.example.mydemo.model.CouponResponse;
import com.example.mydemo.R;
import com.example.mydemo.model.StoreResponse;
import com.example.mydemo.Util.Constant;
import com.example.mydemo.Util.ItemOffsetDecoration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    RecyclerView rv_couponlist, rv_catergory;
    TextView tv_cateroryname, tv_veg, tv_egg, tv_storediscount;
    LinearLayoutManager linearLayoutManager;
    ImageView searchview;
    SearchView cat_searchview;
    LinearLayout constantlinearlayout;
    ImageView img_clearseaerch;
    public final List<Categories> filterlist = new ArrayList<>();
    final List<Categories> categoriesList = new ArrayList<>();
    TextView tv_storetitle;
    ImageView img_back;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_veg = findViewById(R.id.tv_veg);
        tv_storediscount = findViewById(R.id.tv_storediscount);
        tv_storetitle = findViewById(R.id.tv_storetitle);
        searchview = findViewById(R.id.searchview);
        tv_egg = findViewById(R.id.tv_egg);
        img_back = findViewById(R.id.img_back);
        constantlinearlayout = findViewById(R.id.constantlinearlayout);
        tv_cateroryname = findViewById(R.id.tv_cateroryname);
        rv_couponlist = findViewById(R.id.rv_couponlist);
        rv_couponlist.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(MainActivity.this, R.dimen.margn5dp);
        rv_couponlist.addItemDecoration(itemDecoration);
        rv_catergory = findViewById(R.id.rv_catergory);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        rv_catergory.setLayoutManager(linearLayoutManager);
        rv_catergory.addItemDecoration(itemDecoration);
        rv_catergory.setNestedScrollingEnabled(true);
        cat_searchview = findViewById(R.id.cat_searchview);
        cat_searchview.setIconifiedByDefault(false);
        cat_searchview.setIconified(false);
        cat_searchview.clearFocus();
        cat_searchview.setOnQueryTextListener(this);
        img_clearseaerch = findViewById(R.id.img_clearseaerch);
        getStoreData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        img_back.setOnClickListener(v -> finish());
        img_clearseaerch.setOnClickListener(v -> {
            img_clearseaerch.setVisibility(View.GONE);
            constantlinearlayout.setVisibility(View.VISIBLE);
            cat_searchview.setVisibility(View.GONE);
            CateroryParentAdapter cateroryParentAdapter = new CateroryParentAdapter(MainActivity.this, categoriesList);
            rv_catergory.setAdapter(cateroryParentAdapter);

        });

        searchview.setOnClickListener(v -> {
            img_clearseaerch.setVisibility(View.VISIBLE);
            constantlinearlayout.setVisibility(View.GONE);
            cat_searchview.setVisibility(View.VISIBLE);
        });

        tv_egg.setOnClickListener(v -> {

        });
        rv_catergory.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                for(int i=0;i<categoriesList.size();i++)
                {
                    if(i==linearLayoutManager.findFirstVisibleItemPosition())
                    {
                        tv_cateroryname.setText(categoriesList.get(i).Category_name);
                        break;
                    }
                }
            }
        });

    }

    private void getCouponList() {

        CouponRequest couponRequest = new CouponRequest();
        couponRequest.restaurant_category = "NPD";
        couponRequest.user_id = "gXOMAgulWnR2D5o52vgUVB5R1A63";
        ProgressDialog progressDialog = Constant.setupLoading(MainActivity.this);
        progressDialog.show();

        ApiClient.getInstance().getCouponList(couponRequest, new Callback<List<CouponResponse>>() {
            @Override
            public void onResponse(Call<List<CouponResponse>> call, Response<List<CouponResponse>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    CouponAdapter couponAdapter = new CouponAdapter(response.body(), MainActivity.this);
                    rv_couponlist.setAdapter(couponAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<CouponResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void getStoreData() {
        ProgressDialog progressDialog = Constant.setupLoading(MainActivity.this);
        progressDialog.show();

        ApiClient.getInstance().getStoreData(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tv_storetitle.setText(response.body().Name);
                    tv_storetitle.setVisibility(View.VISIBLE);
                    tv_storediscount.setVisibility(View.VISIBLE);
                    tv_storediscount.setText("Flat " + response.body().discountPercentage + "% OFF on complete order");
                    categoriesList.addAll(response.body().categoriesList);
                    CateroryParentAdapter cateroryParentAdapter = new CateroryParentAdapter(MainActivity.this, response.body().categoriesList);
                    rv_catergory.setAdapter(cateroryParentAdapter);
                    getCouponList();
                } else {
                    Toast.makeText(MainActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public List<Categories> getFilter(String data) {
        if (filterlist.size() > 0) {
            filterlist.clear();
        }
        for (int i = 0; i < categoriesList.size(); i++) {
            List<Categories.CatItems> lChildFilter = new ArrayList<>();
            for (Categories.CatItems item : categoriesList.get(i).catItems) {
                if (item.Name.toLowerCase().contains(data.toLowerCase())) {
                    lChildFilter.add(item);
                }
            }
            if (lChildFilter.size() > 0) {
                filterlist.add(new Categories(categoriesList.get(i)._id, categoriesList.get(i).Category_name, categoriesList.get(i).category_image, lChildFilter));
            }
        }
        if (filterlist.size() <= 0) {
            Toast.makeText(MainActivity.this, "No record found!", Toast.LENGTH_SHORT).show();
        }
        return filterlist;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.length() > 0) {
            CateroryParentAdapter cateroryParentAdapter = new CateroryParentAdapter(MainActivity.this, getFilter(newText));
            rv_catergory.setAdapter(cateroryParentAdapter);
        } else {
            CateroryParentAdapter cateroryParentAdapter = new CateroryParentAdapter(MainActivity.this, categoriesList);
            rv_catergory.setAdapter(cateroryParentAdapter);
        }
        return false;
    }
}