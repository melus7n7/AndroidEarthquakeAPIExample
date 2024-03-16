package com.example.appretrofitearthquake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.appretrofitearthquake.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        EarthquakeAdapter adapter = new EarthquakeAdapter();
        binding.rcEarthquakes.setLayoutManager(new LinearLayoutManager(this));
        binding.rcEarthquakes.setAdapter(adapter);

        //Cada vez que cambie la lista
        viewModel.getEarthquakesList().observe(this, list->{
            adapter.submitList(list);
        });

        viewModel.getEarthquakesRepository();
    }
}