package com.callor.movie.service.impl;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.movie.adapter.MovieViewAdapter;
import com.callor.movie.config.NaverAPI;
import com.callor.movie.model.NaverMovieDTO;
import com.callor.movie.model.NaverParent;
import com.callor.movie.service.NaverMovieService;
import com.callor.movie.service.RetrofitAPIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverMovieServiceImplV1 implements NaverMovieService {
    private RecyclerView recyclerView;
    public NaverMovieServiceImplV1(RecyclerView recyclerView) {

        this.recyclerView = recyclerView;
    }


    @Override
    public NaverMovieDTO getMovies(String search) {
        Log.d("Naver Service","Start");
        Call<NaverParent> retrofitReturn = RetrofitAPIClient.getApiClient()
                .getMoive(
                        NaverAPI.CLIENT_ID,
                        NaverAPI.CLIENT_SECRET,
                        search,
                        1,
                        10);

        retrofitReturn.enqueue(new Callback<NaverParent>() {
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                int resCode = response.code();
                if(resCode < 300) {
                    Log.d("네이버 응답데이터 : ",
                            response.body().toString());
                    NaverParent naverParent = response.body();

                    List<NaverMovieDTO> movieList  = naverParent.items;
                    MovieViewAdapter bookViewAdapter
                            = new MovieViewAdapter(movieList);

                    Log.d("Adapter",bookViewAdapter.toString());
                    Log.d("recyclerView",recyclerView.toString());
                    recyclerView.setAdapter(bookViewAdapter);

                    RecyclerView.LayoutManager layoutManager
                            = new LinearLayoutManager(recyclerView.getContext());
                    recyclerView.setLayoutManager(layoutManager);

                } else {
                    Log.d("오류 발생:", response.toString());
                }
            }
            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });
        return null;
    }
}

