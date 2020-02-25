package com.aditas.merchant.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditas.merchant.ListProd;
import com.aditas.merchant.R;
import com.aditas.merchant.entity.Category;
import com.aditas.merchant.entity.Merchant;
import com.aditas.merchant.entity.Product;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMarket;
    private Adapt adpt;
    private List<Product> prds = new ArrayList<>();
    private RequestQueue queue;
    //private String url = "http://210.210.154.65:4444/api/products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMarket = findViewById(R.id.rv_market);
        adpt = new Adapt();
        rvMarket.setAdapter(adpt);
        rvMarket.setLayoutManager(new GridLayoutManager(this, 2));
        //new NetTask(adpt).execute(url);
        queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://210.210.154.65:4444/api/products";

        StringRequest prodReq;
        prodReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Handle response
                Gson data = new Gson();
                ListProd lp = data.fromJson(response, ListProd.class);
                adpt.setProd(lp.getProdz());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", error.getMessage());
            }
        });

        /*JsonObjectRequest prodReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //handle response
                try {
                    ArrayList<Product> prodz = new ArrayList<>();
                    JSONArray data = response.getJSONArray("data");
                    for(int i = 0; i < data.length(); i++){
                        Gson gson = new Gson();
                        Product prod = gson.fromJson(data.getJSONObject(i).toString(), Product.class);
                        prodz.add(prod);
                    }
                    adpt.setProd(prodz);
                    Toast.makeText(getApplicationContext(), String.valueOf(adpt.getItemCount()), Toast.LENGTH_LONG).show();
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley error", error.getMessage());
            }
        });*/
        queue.add(prodReq);

        //desJSON();
        //adpt.setProd(prds);
    }

    /*private void desJSON(){
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
    }*/

        /*private String getJSON () {
            String json = " ";
            try {
                InputStream is = getResources().openRawResource(R.raw.data);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
                }
                 catch (IOException e) {
                 e.printStackTrace();
                 }
                return json;
        }*/

            /*private static Merchant createMerchant (JSONObject merch){
                try {
                    int id = merch.getInt("merchantId");
                    String name = merch.getString("merchantName");
                    String slug = merch.getString("merchantSlug");
                    return new Merchant(id, name, slug);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            private static Category createCategory (JSONObject cat){
                try {
                    int id = cat.getInt("categoryId");
                    String name = cat.getString("categoryName");
                    return new Category(id, name);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }*/

            /*private static class NetTask extends AsyncTask<String, Void, String>{
                private WeakReference<Adapt> adapt;

                NetTask(Adapt adapt) {
                    this.adapt = new WeakReference<>(adapt);
                }

                @Override
                protected String doInBackground(String... strings){
                    String json = null;
                    try{
                        URL url = new URL(strings[0]);
                        HttpURLConnection conn = null;
                        try{
                            conn = (HttpURLConnection) url.openConnection();
                            conn.connect();

                            InputStream is = conn.getInputStream();
                            StringBuilder sb = new StringBuilder();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));

                            String each;
                            while((each = br.readLine()) != null){
                                sb.append(each);
                            }
                            json = sb.toString();
                        }
                         catch (IOException e) {
                            e.printStackTrace();
                         }
                        finally{
                            if(conn != null){ conn.disconnect(); }
                        }
                    }
                    catch (MalformedURLException e){
                        e.printStackTrace();
                    }
                    return json;
                }

                @Override
                protected void onPostExecute(String s){
                    if(!s.equals("")){
                        //try{
                            Gson jsonConv = new Gson();
                            ListProd prz = jsonConv.fromJson(s, ListProd.class);
                            Log.i("json :" ,s);
                            Adapt adapter = adapt.get();
                            adapter.setProd(prz.getProdz());
                            adapter.notifyDataSetChanged();
                            /*
                            JSONObject obj = new JSONObject(s);
                            JSONArray arr = obj.getJSONArray("data");

                            for(int i=0; i<arr.length(); i++){
                                JSONObject prod = arr.getJSONObject(i);
                                JSONObject merc = prod.getJSONObject("merchant");
                                JSONObject catg = prod.getJSONObject("category");

                                int id = prod.getInt("productId");
                                int qty = prod.getInt("productQty");
                                String name = prod.getString("productName");
                                String slug = prod.getString("productSlug");
                                String image = prod.getString("productImage");

                                Merchant mer = createMerchant(merc);
                                Category cts = createCategory(catg);
                                Product prdt = new Product(id, qty, name, slug, image, mer,cts);
                                prd.get().add(prdt);
                            }

                        //}
                         //catch (JSONException e){ e.printStackTrace(); }
                    }
                }
            }*/
}
