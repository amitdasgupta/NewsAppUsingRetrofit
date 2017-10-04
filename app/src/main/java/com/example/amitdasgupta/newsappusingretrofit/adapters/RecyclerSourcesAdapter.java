package com.example.amitdasgupta.newsappusingretrofit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amitdasgupta.newsappusingretrofit.R;
import com.example.amitdasgupta.newsappusingretrofit.model.Sources;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by AMIT DAS GUPTA on 30-09-2017.
 */

public class RecyclerSourcesAdapter extends RecyclerView.Adapter<RecyclerSourcesAdapter.MyViewHolder>{
    List<Sources> sources;
    Context context;

    public RecyclerSourcesAdapter() {
    }

    public RecyclerSourcesAdapter(List<Sources> sources, Context context) {

        this.sources = sources;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_sources,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
holder.name.setText(sources.get(position).getName());
        holder.id.setText(sources.get(position).getId());
        Glide.with(context).load(sources.get(position).getUrlsToLogos().getSmall()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView name,id;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=(CircleImageView) itemView.findViewById(R.id.newssourceicon);
            name=(TextView)itemView.findViewById(R.id.newschannelname);
            id=(TextView)itemView.findViewById(R.id.newchannelid);
        }
    }
}
