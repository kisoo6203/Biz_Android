package com.callor.movie;

import com.callor.movie.model.NaverParent;

import retrofit2.Call;

public interface NaverRetrofit {

    public Call<NaverParent> getMovies(
            String clientId,
            String clientSecret,

    )

}
