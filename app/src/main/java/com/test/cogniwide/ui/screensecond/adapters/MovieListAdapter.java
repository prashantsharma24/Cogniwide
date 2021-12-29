package com.test.cogniwide.ui.screensecond.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.cogniwide.data.model.MovieListDetailResponseModel;
import com.test.cogniwide.databinding.RvMoviesListBinding;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private List<MovieListDetailResponseModel> movieListResultList;
    private Context mContext;

    public MovieListAdapter(Context mContext, List<MovieListDetailResponseModel> movieListResultList) {
        this.movieListResultList = movieListResultList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RvMoviesListBinding productRowBinding = RvMoviesListBinding.inflate(layoutInflater, parent, false);

        return new MyViewHolder(productRowBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final MovieListDetailResponseModel moviePojo = movieListResultList.get(position);

        holder.rvMoviesListBinding.setMovieListModel(moviePojo);
        holder.rvMoviesListBinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return movieListResultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RvMoviesListBinding rvMoviesListBinding;

        public MyViewHolder(@NonNull RvMoviesListBinding rvMoviesListBinding) {
            super(rvMoviesListBinding.getRoot());
            this.rvMoviesListBinding = rvMoviesListBinding;
        }
    }
}

