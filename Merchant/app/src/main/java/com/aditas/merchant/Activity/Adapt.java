package com.aditas.merchant.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aditas.merchant.R;
import com.aditas.merchant.entity.Product;

import java.util.List;

public class Adapt extends RecyclerView.Adapter<Adapt.MainHolder>{
    private List<Product> prod;

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detail, parent, false);
        return new MainHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int pos){
//        if(prod !=null){
//            holder.onBInd(prod.get(pos));
//        }
        holder.prodName.setText(prod.get(pos).getName());
        holder.MerchName.setText(prod.get(pos).getMerch().getName());
        String baseUrl = "http://192.168.6.221:81/storage";
        String url = baseUrl+prod.get(pos).getImage();

        Glide with
    }

    @Override
    public int getItemCount(){
        return prod != null ? prod.size() : 0;
    }
    public void setProd(List<Product> prds){
        this.prod = prds;
        notifyDataSetChanged();
    }


    class MainHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView prodName, MerchName;

        MainHolder(@NonNull View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.img_prod);
            prodName = itemView.findViewById(R.id.tv_prod_name);
            MerchName = itemView.findViewById(R.id.tv_merch_name);
        }
        public void onBInd(Product product) {
            prodName.setText(product.getName());
            MerchName.setText(product.getMerch().getName());
        }
    }
}
