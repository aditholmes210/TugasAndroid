package com.aditas.latihan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.tv_data);
        desJSON();
    }

    public void desJSON(){
        String json = loadJSON();
        try{
            JSONObject obj = new JSONObject(json);
            JSONObject person = obj.getJSONObject("person");
            String nama = person.getString("name");
            int age = person.getInt("age");
            String gender = person.getString("gender");
            String msg = "name : " +nama+"\n"+"age : " +age+"\n"+"gender : "+gender+"\n";
            text.setText(msg);  //text diambil dari TextView

            JSONArray address = person.getJSONArray("address");
            text.append("address : "+"\n");
            for(int i = 0; i < address.length(); i++){
                JSONObject add = address.getJSONObject(i);
                String addName = add.getString("addressName");
                String addDetail = add.getString("detail");
                String addCity = add.getString("city");
                text.append(" "+addName+" : "+addDetail+" "+addCity+"\n");
            }
        } catch(JSONException e){
            e.printStackTrace();
        }
    }
    private String loadJSON(){
        String json = null;
        try {
            //InputStream is = getActivity().getAssets().open("yourfilename.json"); //from asset
            InputStream is = getResources().openRawResource(R.raw.data);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
