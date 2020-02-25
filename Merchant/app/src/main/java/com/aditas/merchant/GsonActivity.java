package com.aditas.merchant;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aditas.merchant.Activity.Adapt;
import com.aditas.merchant.entity.Mhs;
import com.aditas.merchant.entity.Product;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);


//        //Gson gson = new Gson(); //v1
//        //Buat expose
//            Gson gson = new GsonBuilder() //buat ngasih pilihan ke Gson
//                    .excludeFieldsWithoutExposeAnnotation() //buat ngehide data yg gapake @Expose
//                    .create(); //v2
//        //Serialisasi
//        /*Mhs mhs = new Mhs("Aditya",
//                "Sulaikan",
//                25,
//                "IT"
//        );*/
//        //String json = gson.toJson(mhs); //mskin data ke json
//        //Toast.makeText(this, json, Toast.LENGTH_LONG).show();
//        //Log.d("Merchant", json);
//
//        //Deserialisasi
//        String jon = "{\"jur\":\"IT\",\"namaBlkg\":\"Sulaikan\",\"namaDpn\":\"Aditya\",\"umur\":25}";
//        Mhs mhz = gson.fromJson(jon, Mhs.class);

    }
}
