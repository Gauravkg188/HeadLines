package com.kg.Headlines.room;

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

import com.kg.Headlines.Detailed;
import com.kg.Headlines.R;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ViewHolder> {

    Context context;
    List<SavedArticle> articles=new ArrayList<>();

    public SavedAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new SavedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final SavedArticle a=articles.get(position);
        String imageUrl=a.getImageUrl();

        Picasso.with(context).load(imageUrl).into(holder.imageView);

        holder.Title.setText(a.getTitle());
        holder.Source.setText(a.getAuthor());
        holder.Date.setText("\u2022"+dateTime(a.getDate()));
        holder.checkFav.setImageResource(R.drawable.ic_baseline_favorite_24);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent=new Intent(context, Detailed.class);

                intent.putExtra("url",a.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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

    public void setArticles(List<SavedArticle> articles)
    {
        this.articles=articles;
        notifyDataSetChanged();

    }

    public SavedArticle getArticle(int pos)
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
}
