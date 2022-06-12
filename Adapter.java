package com.example.midterm3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<news> {

    ArrayList<news> newsList;
    Context context;
    int resource;
    public Adapter(ArrayList<news> data, Context context){
        super (context,R.layout.list_item,data);
        this.context=context;
        this.newsList=data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int newsIndex = position;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        ImageView newsImage = convertView.findViewById(R.id.newsImage);
        TextView heading = convertView.findViewById(R.id.heading);
        TextView desc = convertView.findViewById(R.id.desc);

        Button sharebtn = convertView.findViewById(R.id.share);
        news newNews = getItem(position);
        sharebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,newNews.getRef());
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent,null);
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(shareIntent);
            }

        });

        newsImage.setImageResource(newNews.getId());
        heading.setText(newNews.getHeading());
        desc.setText(newNews.getDesc());
        Picasso.get().load(newNews.getUrl()).into(newsImage);
        return convertView;
    }
}
