package com.aditas.merchant;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.aditas.merchant.entity.Mhs;
import com.google.gson.Gson;

public class GsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        Gson gson = new Gson();

        //Serialisasi
        Mhs mhs = new Mhs("Aditya",
                "Sulaikan",
                25,
                "IT"
        );
        //String json = gson.toJson(mhs); //mskin data ke json
        //Toast.makeText(this, json, Toast.LENGTH_LONG).show();
        //Log.d("Merchant", json);

        //Deserialisasi
        String jon = "{\"jur\":\"IT\",\"namaBlkg\":\"Sulaikan\",\"namaDpn\":\"Aditya\",\"umur\":25}";
        Mhs mhz = gson.fromJson(jon, Mhs.class);
    }
}
