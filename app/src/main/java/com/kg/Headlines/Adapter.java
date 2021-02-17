package com.kg.Headlines;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.kg.Headlines.NewsArticle.Article;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    Context context;
    ArrayList<Article> articles;
    clickListener listener;


    public Adapter(Context context,clickListener clickListener) {
        this.context = context;
        this.listener=clickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);


    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Article a=articles.get(position);
        String imageUrl=a.getUrlToImage();

        Picasso.with(context).load(imageUrl).into(holder.imageView);

        holder.Title.setText(a.getTitle());
        holder.Source.setText(a.getSource().getName());
        holder.Date.setText("\u2022"+dateTime(a.getPublishedAt()));




        holder.checkFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.checkFav.setImageResource(R.drawable.ic_baseline_favorite_24);
                listener.onclick(a);




            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent=new Intent(context,Detailed.class);

                intent.putExtra("url",a.getUrl());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Title,Source,Date;
        ImageView imageView,checkFav;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.title);
            Source = itemView.findViewById(R.id.source);
            Date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardView);
            checkFav=itemView.findViewById(R.id.checkFav);


        }
    }

    public void setArticles(ArrayList<Article> articles)
    {
        this.articles=articles;
    }

   public Article getArticle(int pos)
   {
       return articles.get(pos);
   }


    public String dateTime(String t){
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
        String time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date date = simpleDateFormat.parse(t);
            time = prettyTime.format(date);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return time;

    }

    public String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }

    public interface clickListener{
        void onclick(Article article);


    }

    public void setOnClickListener(clickListener listener)
    {
        this.listener=listener;
    }


}