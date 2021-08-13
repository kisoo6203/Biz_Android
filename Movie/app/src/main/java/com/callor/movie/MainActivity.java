package com.callor.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.SearchView;

import com.callor.movie.service.NaverMovieService;
import com.callor.movie.service.impl.NaverMovieServiceImplV1;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar main_toolbar = findViewById(R.id.main_app_toolbar);
        setSupportActionBar(main_toolbar);

        recyclerView = findViewById(R.id.movie_list_view);
        NaverMovieService naverMovieService
                = new NaverMovieServiceImplV1(recyclerView);
        naverMovieService.getMovies("horror");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu,menu);

        SearchView searchView
                = (SearchView) menu
                .findItem(R.id.app_bar_search)
                .getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("영화 검색");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("검색버튼 클릭 : ", query);
                NaverMovieService naverMovieService
                        = new NaverMovieServiceImplV1(recyclerView);
                naverMovieService.getMovies(query.trim());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("현재 입력하는 문자열 :" ,newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}