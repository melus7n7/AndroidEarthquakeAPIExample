package com.example.appretrofitearthquake;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    public interface DownloadEqsListener{
        void onEqsDownloaded(List<Earthquake> eqList);
    }
    private List<Earthquake> getEarthquakesWithMoshi(EarthquakeJSONResponse body) {
        ArrayList<Earthquake> eqList = new ArrayList<>();
        List<Feature> features = body.getFeatures();
        for (Feature feature: features) {
            String id = feature.getId();
            double magnitude = feature.getProperty().getMagnitude();
            String place = feature.getProperty().getPlace();
            long time = feature.getProperty().getTime();
            double longitude = feature.getGeometry().getLongitude();
            double latitude = feature.getGeometry().getLatitude();
            Earthquake earthquake = new Earthquake(id, place, magnitude, time,
                    latitude, longitude);
            eqList.add(earthquake);
        }
        return eqList;
    }
    public void getEarthquakes(DownloadEqsListener downloadEqsListener){
        ApiClient.Services service = ApiClient.getInstance().getServices();
        service.getEarthquakes().enqueue(new Callback<EarthquakeJSONResponse>() {
            @Override
            public void onResponse(Call<EarthquakeJSONResponse > call,
                                   Response<EarthquakeJSONResponse > response) {
                List<Earthquake> earthquakeList = getEarthquakesWithMoshi(response.body());
                downloadEqsListener.onEqsDownloaded(earthquakeList);
            }
            @Override
            public void onFailure(Call<EarthquakeJSONResponse> call, Throwable t) {
                Log.i("Falló", "Hubo un fallo");
            }
        });
    }
}
