package com.aditas.merchant.Activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aditas.merchant.R;
import com.aditas.merchant.entity.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.aditas.merchant.Activity.Detail.EXTRA_DATA;


public class Adapt extends RecyclerView.Adapter<Adapt.MainHolder>{
    private List<Product> prod;

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MainHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int pos){
        if(prod !=null){
            holder.onBInd(prod.get(pos));
        }
//        holder.prodName.setText(prod.get(pos).getName());
//        holder.MerchName.setText(prod.get(pos).getMerch().getName());
//        String baseUrl = "http://192.168.6.221:81/storage";
//        String url = baseUrl+prod.get(pos).getImage();
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
        private Context ctx;
        private ImageView img;
        private TextView prodName, MerchName;
        private ProgressBar progImg;
        private Product prot;

        MainHolder(@NonNull View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.img_prod);
            prodName = itemView.findViewById(R.id.tv_prod_name);
            MerchName = itemView.findViewById(R.id.tv_merch_name);
            progImg = itemView.findViewById(R.id.progress_image);

            ctx = itemView.getContext();
            itemView.setOnClickListener(listen);
        }
        public void onBInd(Product product) {
            this.prot=product;
            prodName.setText(product.getName());
            MerchName.setText(product.getMerch().getName());
            Picasso.get()
                    .load("http://210.210.154.65:4444/storage/"+product.getImage())
                    .error(R.drawable.ic_launcher_background)
                    .fit()
                    .into(img, new Callback() {
                        @Override
                        public void onSuccess() {
                            progImg.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError(Exception e) {
                            progImg.setVisibility(View.INVISIBLE);
                            e.printStackTrace();
                        }
                    });
        }
        View.OnClickListener listen = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(ctx, Detail.class);
                i.putExtra(EXTRA_DATA, prot);
                ctx.startActivity(i);
            }
        };
    }
}
