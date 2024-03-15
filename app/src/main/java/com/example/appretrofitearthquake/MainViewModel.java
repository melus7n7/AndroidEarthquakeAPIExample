package com.example.appretrofitearthquake;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private MainRepository repository = new MainRepository();
    private final MutableLiveData<List<Earthquake>> earthquakesList = new MutableLiveData<>();

    public LiveData<List<Earthquake>> getEarthquakesList () {
        return earthquakesList;
    }

    public void getEarthquakesRepository(){
        repository.getEarthquakes(earthquakeList -> {
            earthquakesList.setValue(earthquakeList);
        });
    }
/*
    public void getEarthquakesFromApi(){
        ApiClient.Services serviceEarthquake = ApiClient.getInstance().getServices();
        serviceEarthquake.getEarthquakes().enqueue(new Callback<EarthquakeJSONResponse>() {
            @Override
            public void onResponse(Call<EarthquakeJSONResponse> call, Response<EarthquakeJSONResponse> response) {
                List<Earthquake> earthquakes = getEarthquakesWithMoshi(response.body());
                earthquakesList.setValue(earthquakes);
            }

            @Override
            public void onFailure(Call<EarthquakeJSONResponse> call, Throwable t) {
                Log.i("Falló", "Hubo un error");
            }
        });
    }

    private List<Earthquake> parseEarthquakes(String body){
        ArrayList<Earthquake> earthquakesList = new ArrayList<>();
        try {
            JSONObject jsonResponse = new JSONObject(body);
            JSONArray featuresArray = jsonResponse.getJSONArray("features");
            for (int i = 0; i < featuresArray.length(); i++) {
                JSONObject jsonFeature = featuresArray.getJSONObject(i);
                String id = jsonFeature.getString("id");

                JSONObject jsonProperties = jsonFeature.getJSONObject("properties");
                double mag = jsonProperties.getDouble("mag");
                String place = jsonProperties.getString("place");
                long time = jsonProperties.getLong("time");

                JSONObject jsonGeometry = jsonFeature.getJSONObject("geometry");
                JSONArray jsonCoordinates = jsonGeometry.getJSONArray("coordinates");
                double longitude = jsonCoordinates.getDouble(0);
                double latitude = jsonCoordinates.getDouble(1);

                earthquakesList.add(new Earthquake(id, place, mag, time, longitude, latitude));
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return earthquakesList;
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

 */
}
