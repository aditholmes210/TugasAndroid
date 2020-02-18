package com.aditas.merchant.Activity;

import android.os.AsyncTask;

import com.aditas.merchant.entity.Category;
import com.aditas.merchant.entity.Merchant;
import com.aditas.merchant.entity.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ApiTask extends AsyncTask<String, Void, ArrayList<Product>> {
    WeakReference<Adapt> adaptRef;

    public ApiTask(Adapt adapt){
        this.adaptRef = new WeakReference<>(adapt);
    }

    @Override
    protected ArrayList<Product> doInBackground(String... url){
        ArrayList<Product> prods = new ArrayList<>();
        String json = loadJSON(url[0]);
        prods = descJSON(json);
        return prods;
    }

    @Override
    protected void onPostExecute(ArrayList<Product> prods){
        Adapt adpt = adaptRef.get();
        adpt.addData(prods);
        adpt.notifyDataSetChanged();
    }
    
    private String loadJSON(String urlParam) {
        String json = null;
        //Network Calls
        try{
            HttpURLConnection connection = null;
            URL url = new URL(urlParam); //urlParam ex: http://google.com
            try{
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream is = connection.getInputStream(); // obj input dari stream file
                InputStreamReader isr = new InputStreamReader(is); //obj input utk string/char
                BufferedReader read = new BufferedReader(isr); //obj read utk inutan
                
                StringBuffer sb = new StringBuffer(); //empty string buffer
                String eachLine;
                while ((eachLine = read.readLine()) != null){
                    sb.append(eachLine); //fill the string buffer with each line from read
                }
                json = sb.toString(); //convert sb to string and assign to json
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if(connection != null){
                    connection.disconnect();
                }
            }
        } catch (MalformedURLException e){
            e.printStackTrace();
            return e.getMessage();
        }
        return json;
    }

    private ArrayList<Product> descJSON(String json) {
        ArrayList<Product> products = new ArrayList<>();
        String jsonParam;
        String json = jsonParam;

        try {

            JSONObject jsonData = new JSONObject(json);

            JSONArray data = jsonData.getJSONArray("data");

            for (int i=0;i < data.length();i++){

                Product productObj;

                JSONObject product = data.getJSONObject(i);

                long productId = product.getLong("productId");

                String productName = product.getString("productName");

                String productSlug = product.getString("productSlug");

                String productImage = product.getString("productImage");

                int productQty = product.getInt("productQty");

                JSONObject merchant = product.getJSONObject("merchant");

                JSONObject category = product.getJSONObject("category");

​

                long categoryId = category.getLong("categoryId");

                String categoryName = category.getString("categoryName");

​

                Category categoryObj = new Category(categoryId,categoryName);

​

                long merchantId = merchant.getLong("merchantId");

                String merchantName = merchant.getString("merchantName");

                String merchantSlug = merchant.getString("merchantSlug");

​

                Merchant merchantObj = new Merchant(merchantId,merchantName,merchantSlug);

​

                productObj = new Product(productId,productName,productSlug,productImage,productQty,merchantObj,categoryObj);

                products.add(productObj);

            }

​

​

        }

        catch (JSONException err){

            err.printStackTrace();

​

        }
    }



    
}
