package com.test.cogniwide.ui.screensecond.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.cogniwide.BuildConfig;
import com.test.cogniwide.R;
import com.test.cogniwide.ui.screensecond.MovieListResultModel;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private ArrayList<MovieListResultModel> movieListResultList;
    private Context mContext;

    public MovieListAdapter(Context mContext, ArrayList<MovieListResultModel> movieListResultList) {
        this.movieListResultList = movieListResultList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_movies_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        MovieListResultModel moviePojo = movieListResultList.get(position);

        holder.tv_movie_name.setText(moviePojo.getTitle());

        Glide.with(mContext)
                .load(BuildConfig.IMG_URL + moviePojo.getPosterPath())
                .into(holder.iv_movie);
    }

    @Override
    public int getItemCount() {
        return movieListResultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_movie_name;
        public ImageView iv_movie;

        public MyViewHolder(View view) {
            super(view);
            tv_movie_name = view.findViewById(R.id.tv_movie_name);
            iv_movie = view.findViewById(R.id.iv_movie);
        }
    }
}

