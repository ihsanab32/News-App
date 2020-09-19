package com.ihsan.xd.newapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ihsan.xd.newapp.R;
import com.ihsan.xd.newapp.model.Articles;
import com.ihsan.xd.newapp.untils.listener.ItemClickListener;
import com.ihsan.xd.newapp.view.activities.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     :  17/09/2020.
 * ------------------------------
 * This class for
 */
@SuppressLint({"InflateParams", "SetTextI18n"})
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context context;
    private List<Articles> articles;

    public NewsAdapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layoutParams);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Articles itemArticles = articles.get(position);
        holder.bindTo(itemArticles);
        holder.setClickListener(view -> {
            Intent i = new Intent(context, DetailActivity.class);
            i.putExtra("imgNews", itemArticles.getUrlToImage());
            i.putExtra("titleNews", itemArticles.getTitle());
            if(itemArticles.getContent() == null){
                i.putExtra("contentNews", "No Description");
            }
            else{
                i.putExtra("contentNews", itemArticles.getContent());
            }
            i.putExtra("dateNews", itemArticles.getPublishedAt().substring(0,10)+" "+itemArticles.getPublishedAt().substring(11,16));
            if(itemArticles.getAuthor() == null){
                i.putExtra("authorNews", "Unknown Author");
            }
            else{
                i.putExtra("authorNews", itemArticles.getAuthor());
            }
            i.putExtra("sourceNews", itemArticles.getUrl());
            context.startActivity(i);
        });

    }

    public void refresh(List<Articles> fill) {
        articles = new ArrayList<>();
        articles.addAll(fill);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemClickListener itemClickListener;
        @BindView(R.id.title_news)
        TextView title;
        @BindView(R.id.date_news)
        TextView date;
        @BindView(R.id.author_news)
        TextView author;
        @BindView(R.id.img_news)
        AppCompatImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        void setClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        void bindTo(Articles articles) {
            title.setText(articles.getTitle());
            date.setText(articles.getPublishedAt());
            author.setText(articles.getAuthor());
            Glide.with(context)
                    .load(articles.getUrlToImage())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(image);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v);
        }
    }

    public void addNews(List<Articles> item) {
        articles.addAll(item);
        notifyDataSetChanged();
    }
}
