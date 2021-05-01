package com.example.mydemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoreResponse {

    public String _id,Location,Cuisines,Name,acquirer,activator,razorpay_account_id,restaurant_address,restaurant_email,discount_type,restaurant_fcm_id,restaurant_category,restaurant_image,restaurant_number,restaurant_type,password;
    public  int ID,__v,commissionPercentage,mdv,mov,peARDiscount,restaurantDiscount,serviceCharge,tables_count,costForTwo,discountPercentage;
    public boolean Live,cardCommission,comingSoon,order_kot,order_progress,order_ring,gst,hasDiscount,hasOrdering,AR;
    public double latitude,longitude;

    @SerializedName("order_progress_time")
    public TimeClass order_progress_time;


    @SerializedName("activation_time")
    public TimeClass activation_time;

    public class TimeClass {
        public String startDate,endDate;
    }

    @SerializedName("Categories")
    public List<Categories> categoriesList;

    /*public class Categories{

        public double Version;
        public boolean Live,hasCategoryDiscount;
        public int categoryDiscountPercentage;
        public String _id,Category_bundle_link,Category_name,category_image;
        @SerializedName("Items")
        public List<CatItems> catItems;

        public class CatItems{
                public String _id,Name,Description,item_type,item_image,model_android;
                public int Price,AcPrice,serving_time;
                public boolean Jain,AR,Live,Vegan,Gluten_free,Best_seller,Chefs_special,Seasonal,taxable;

                @SerializedName("Customisation")
                public List<Custmoization> custmoizationList;

                public class Custmoization{

                }
        }
    }*/
}
