package com.callor.movie.adapter;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.movie.R;
import com.callor.movie.model.NaverMovieDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieViewAdapter extends RecyclerView.Adapter {

    private List<NaverMovieDTO> movieList;

    public MovieViewAdapter(List<NaverMovieDTO> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater
                = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(
                R.layout.movie_item_view,
                parent,
                false  );

        MovieItemHolder viewHolder = new MovieItemHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MovieItemHolder movieHolder = (MovieItemHolder) holder;

        NaverMovieDTO movieDTO = movieList.get(position);

        String item_title = movieDTO.getTitle();
        Spanned sp_title = Html.fromHtml(item_title,Html.FROM_HTML_MODE_LEGACY);
        movieHolder.item_title.setText(sp_title);

        String item_direc = movieDTO.getDirector();
        item_direc = String.format("감독 : %s",item_direc);
        Spanned sp_direc = Html.fromHtml(item_direc, Html.FROM_HTML_MODE_LEGACY);
        movieHolder.item_director.setText(sp_direc);

        String item_act = movieDTO.getActor();
        item_act = String.format("배우 : %s",item_act);
        Spanned sp_act = Html.fromHtml(item_act, Html.FROM_HTML_MODE_LEGACY);
        movieHolder.item_actor.setText(sp_act);

        String item_ur = movieDTO.getUserRating();
        item_ur = String.format("평점 : %s",item_ur);
        Spanned sp_ur = Html.fromHtml(item_ur, Html.FROM_HTML_MODE_LEGACY);
        movieHolder.item_userrating.setText(sp_ur);

        if(!movieDTO.getImage().isEmpty()) {
            Picasso.get().load(movieDTO.getImage())
                    .into(movieHolder.item_image);
        }
    }


    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public static class MovieItemHolder extends RecyclerView.ViewHolder {
        public TextView item_title;
        public ImageView item_image;
        public TextView item_director;
        public TextView item_actor;
        public TextView item_userrating;


        public MovieItemHolder(@NonNull View itemView) {
            super(itemView);
            item_title = itemView.findViewById(R.id.movie_item_title);
            item_image = itemView.findViewById(R.id.movie_item_image);
            item_director = itemView.findViewById(R.id.movie_item_direc);
            item_actor = itemView.findViewById(R.id.movie_item_act);
            item_userrating = itemView.findViewById(R.id.movie_item_ur);
        }
    }

}
