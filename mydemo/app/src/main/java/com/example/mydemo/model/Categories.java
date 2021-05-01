package com.example.mydemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories{

    public double Version;
    public boolean Live,hasCategoryDiscount;
    public int categoryDiscountPercentage;
    public String _id,Category_bundle_link,Category_name,category_image;

    public Categories(String _id, String category_name, String category_image, List<CatItems> catItems) {
        this._id = _id;
        Category_name = category_name;
        this.category_image = category_image;
        this.catItems = catItems;
    }

    public Categories() {
    }

    @SerializedName("Items")
    public List<Categories.CatItems> catItems;

    public class CatItems{
        public String _id,Name,Description,item_type,item_image,model_android;
        public int Price,AcPrice,serving_time;
        public boolean Jain,AR,Live,Vegan,Gluten_free,Best_seller,Chefs_special,Seasonal,taxable;

        @SerializedName("Customisation")
        public List<Categories.CatItems.Custmoization> custmoizationList;

        public class Custmoization{

        }
    }
}
