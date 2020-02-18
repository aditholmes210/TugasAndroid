package com.aditas.merchant.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aditas.merchant.R;
import com.aditas.merchant.entity.Product;

import static android.nfc.NfcAdapter.EXTRA_DATA;

public class Detail extends AppCompatActivity {
    private ImageView image;
    private TextView name, qty, cat, merch;
    private ProgressBar progImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Product prodt = getIntent().getParcelableExtra(EXTRA_DATA);
        if (prodt != null){
            String

        }
    }
}
