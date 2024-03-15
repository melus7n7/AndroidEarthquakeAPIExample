package com.example.appretrofitearthquake;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

public class ApiClient {
    public interface Services{
        @GET("all_hour.geojson")
        Call<EarthquakeJSONResponse> getEarthquakes();
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    private Services serviceEarthquake;
    private static final ApiClient apiClient = new ApiClient();
    private ApiClient(){

    }
    public static ApiClient getInstance(){
        return apiClient;
    }
    public Services getServices(){
        if(serviceEarthquake == null){
            serviceEarthquake = retrofit.create(Services.class);
        }
        return serviceEarthquake;
    }
}
