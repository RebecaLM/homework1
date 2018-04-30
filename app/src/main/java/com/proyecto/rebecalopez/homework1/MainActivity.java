package com.proyecto.rebecalopez.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.proyecto.rebecalopez.homework1.data.WeatherAdapter;
import com.proyecto.rebecalopez.homework1.data.entities.Weather;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getName();

    private ArrayList<com.proyecto.rebecalopez.homework1.data.entities.List> weatherList = new ArrayList<>();
    private WeatherAdapter weatherAdapter = new WeatherAdapter(weatherList);
    private RecyclerView recyclerView;

    private interface MockyService {
        String BASE_URL = "http://api.openweathermap.org/";

        @GET("/data/2.5/forecast")
        Call<Weather> retrieveWeather(@Query("id")String id, @Query("APPID")String APPID);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.a_main_recyclerv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(weatherAdapter);

        doRetrofitRequest("524901","12c5b045f871ba0fd36925bb18435bc6");
    }

    private void doRetrofitRequest(String id, String APPID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MockyService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MockyService mockyService = retrofit.create(MockyService.class);

        mockyService.retrieveWeather(id,APPID).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weatherResponse = response.body();
                Log.d(TAG, "onResponse: " + weatherResponse);

                if(weatherResponse != null) {
                    weatherList.clear();
                    weatherList.addAll(weatherResponse.getList());
                    weatherAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
