package com.example.midterm3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String id,imgUrl,h,d;

    private news n ;
    private ArrayList<news> newsList;
    ListView listView;
    String url = "https://alasartothepoint.alasartechnologies.com/listItem.php?id=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.newsList);

        newsList = new ArrayList<>();
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String img ="https://reqres.in/img/faces/2-image.jpg";
        String head=this.h;
    }
    void run() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(myResponse);
                            JSONArray jsonArray = json.getJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jObject = jsonArray.getJSONObject(i);
                                id= jObject.getString("id");
                                imgUrl = jObject.getString("url");
                                h = jObject.getString("heading");
                                d = jObject.getString("description");
                                String ref = jObject.getString("reference");
                                n = new news(Integer.parseInt(id),imgUrl,d,h,ref);
                                newsList.add(n);
                            }
                            Adapter adapter = new Adapter(newsList,getApplicationContext());
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}