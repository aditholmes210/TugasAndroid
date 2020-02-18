package com.aditas.merchant.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aditas.merchant.R;
import com.aditas.merchant.entity.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import static android.nfc.NfcAdapter.EXTRA_DATA;

public class Detail extends AppCompatActivity {
    private ImageView image;
    private TextView name, qty, cat, merch;
    private ProgressBar progImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        Product prodt = getIntent().getParcelableExtra(EXTRA_DATA);
        if (prodt != null){
            String _img = prodt.getImage();
            String _name = prodt.getName();
            String _qty = String.valueOf(prodt.getQty());
            String _cat = prodt.getCatg().getName();
            String _merch = prodt.getMerch().getName();

            //setImage(_img);
            name.setText(_name);
            qty.setText(_qty);
            cat.setText(_cat);
            merch.setText(_merch);
        }
    }

    private void setImage(String url){
        Picasso.get()
                .load(url)
                .error(R.drawable.ic_launcher_background)
                .fit()
                .into(image, new Callback() {
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

    private void init(){
        image = findViewById(R.id.img_detail);
        name = findViewById(R.id.tv_detail_name);
        qty = findViewById(R.id.tv_detail_qty);
        cat = findViewById(R.id.tv_detail_category);
        merch = findViewById(R.id.tv_merch_name);
        progImg = findViewById(R.id.prog_detail);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
