package com.aditas.merchant.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditas.merchant.R;
import com.aditas.merchant.entity.Category;
import com.aditas.merchant.entity.Merchant;
import com.aditas.merchant.entity.Product;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMarket;
    private Adapt adpt;
    private List<Product> prds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMarket = findViewById(R.id.rv_market);
        adpt = new Adapt();
        rvMarket.setAdapter(adpt);
        rvMarket.setLayoutManager(new GridLayoutManager(this, 2));


        desJSON();
        adpt.setProd(prds);
    }

    private void desJSON(){
        String json = getJSON();
        if(!json.equals("")){
            try{
                JSONObject obj = new JSONObject(json);
                JSONArray arr = obj.getJSONArray("data");

                for(int i=0; i<arr.length(); i++){
                    JSONObject prod = arr.getJSONObject(i);
                    JSONObject merch = prod.getJSONObject("merchant");
                    JSONObject catg = prod.getJSONObject("productCategory");

                    int id = prod.getInt("productId");
                    String name = prod.getString("ProductName");
                    String slug = prod.getString("productSlug");
                    int qty = prod.getInt("productQty");
                    String image = prod.getString("productImage");

                    Merchant merchs = createMerchant(merch);
                    Category cat = createCategory(catg);
                    Product prd = new Product(id, qty, name, slug, image, merchs, cat);
                    prds.add(prd);
                }
            } catch(JSONException e){
                e.printStackTrace();
            }
        }
    }

    private String getJSON() {
        String json = " ";
        try{
            InputStream is = getResources().openRawResource(R.raw.data);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");
        } catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }


    private Merchant createMerchant(JSONObject merch) {
        try{
            int id = merch.getInt("merchantID");
            String name = merch.getString("merchantName");
            String slug = merch.getString("merchantSlug");
            return new Merchant(id, name, slug);
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }
    private Category createCategory(JSONObject cat){
        try{
            int id = cat.getInt("categoryID");
            String name = cat.getString("categoryName");
            return new Category(id, name);
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
