package com.example.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydemo.R;
import com.example.mydemo.model.CouponResponse;

import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponViewHolder> {

    List<CouponResponse> list;
    Context context;

    public CouponAdapter(List<CouponResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.couponitemview, parent, false);
        return new CouponViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponViewHolder holder, int position) {
        holder.tv_discountdiscription.setText(list.get(position).description);
        holder.tv_subdiscountdiscription.setText(list.get(position).sub_description.split("&")[0]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CouponViewHolder extends RecyclerView.ViewHolder {
        final TextView tv_discountdiscription, tv_subdiscountdiscription;

        public CouponViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_discountdiscription = itemView.findViewById(R.id.tv_discountdiscription);
            tv_subdiscountdiscription = itemView.findViewById(R.id.tv_subdiscountdiscription);
        }
    }
}
