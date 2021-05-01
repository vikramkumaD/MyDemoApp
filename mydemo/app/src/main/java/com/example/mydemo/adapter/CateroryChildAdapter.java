package com.example.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydemo.R;
import com.example.mydemo.model.Categories;
import com.example.mydemo.model.StoreResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CateroryChildAdapter extends RecyclerView.Adapter<CateroryChildAdapter.CateroryChildViewHolder> {
    Context context;
    List<Categories.CatItems> list;

    public CateroryChildAdapter(Context context, List<Categories.CatItems> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CateroryChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catergorychilditemview, parent, false);
        return new CateroryChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CateroryChildViewHolder holder, int position) {
        if (list.get(position).Description != null && !list.get(position).Description.equals("")) {
            holder.tv_productdescription.setText(list.get(position).Description);
            holder.tv_productdescription.setVisibility(View.VISIBLE);
        } else {
            holder.tv_productdescription.setVisibility(View.GONE);
        }

        holder.tv_productname.setText(list.get(position).Name);
        holder.tv_productmrp.setText(String.valueOf(list.get(position).AcPrice).concat(" Rs"));
        holder.tv_productprice.setText(String.valueOf(list.get(position).Price).concat(" Rs"));
        if (list.get(position).custmoizationList.size() > 0)
            holder.tv_productcustmize.setVisibility(View.VISIBLE);
        else
            holder.tv_productcustmize.setVisibility(View.GONE);

        Picasso.get().load(list.get(position).model_android).error(R.drawable.ic_launcher_background).into(holder.img_productthumbnail);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CateroryChildViewHolder extends RecyclerView.ViewHolder {
        TextView tv_productcustmize, tv_productdescription, tv_productname, tv_productmrp, tv_productprice;
        ImageView img_productthumbnail;
        Button btn_addproduct;

        public CateroryChildViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_addproduct = itemView.findViewById(R.id.btn_addproduct);
            img_productthumbnail = itemView.findViewById(R.id.img_productthumbnail);
            tv_productcustmize = itemView.findViewById(R.id.tv_productcustmize);
            tv_productdescription = itemView.findViewById(R.id.tv_productdescription);
            tv_productname = itemView.findViewById(R.id.tv_productname);
            tv_productmrp = itemView.findViewById(R.id.tv_productmrp);
            tv_productprice = itemView.findViewById(R.id.tv_productprice);
        }
    }
}
