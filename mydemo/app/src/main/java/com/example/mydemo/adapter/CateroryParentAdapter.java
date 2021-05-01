package com.example.mydemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydemo.R;
import com.example.mydemo.Util.ItemOffsetDecoration;
import com.example.mydemo.activity.MainActivity;
import com.example.mydemo.model.Categories;
import com.example.mydemo.model.StoreResponse;

import java.util.List;

public class CateroryParentAdapter extends RecyclerView.Adapter<CateroryParentAdapter.CaterparentViewHolder> {
    Context context;
    List<Categories> list;
    CateroryChildAdapter cateroryChildAdapter;

    public CateroryParentAdapter(Context context, List<Categories> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CaterparentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parentcategoryitemview, parent, false);
        return new CaterparentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaterparentViewHolder holder, int position) {

        holder.tv_catergoryname.setText(list.get(position).Category_name.concat(" (" + list.get(position).catItems.size()) + ")");
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(context, R.dimen.margn5dp);
        holder.rv_catergorychild.addItemDecoration(itemDecoration);
        holder.rv_catergorychild.setLayoutManager(new LinearLayoutManager(context));
        cateroryChildAdapter = new CateroryChildAdapter(context, list.get(position).catItems);
        holder.rv_catergorychild.setAdapter(cateroryChildAdapter);

        holder.tv_catergoryname.setOnClickListener(v -> {
            if (holder.rv_catergorychild.getVisibility() == View.VISIBLE)
            {
                holder.tv_catergoryname.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_keyboard_arrow_up_24,0);
                holder.rv_catergorychild.setVisibility(View.GONE);
            }
            else {
                holder.tv_catergoryname.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_keyboard_arrow_down_24,0);
                holder.rv_catergorychild.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CaterparentViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv_catergorychild;
        TextView tv_catergoryname;

        public CaterparentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_catergoryname = itemView.findViewById(R.id.tv_catergoryname);
            rv_catergorychild = itemView.findViewById(R.id.rv_catergorychild);
        }
    }


}
