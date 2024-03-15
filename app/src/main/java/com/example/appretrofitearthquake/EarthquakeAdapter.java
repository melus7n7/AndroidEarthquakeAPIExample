package com.example.appretrofitearthquake;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appretrofitearthquake.databinding.ItemEarthquakeBinding;

public class EarthquakeAdapter extends ListAdapter<Earthquake, EarthquakeAdapter.EarthquakeViewHolder> {

    private static final DiffUtil.ItemCallback<Earthquake> DIFF_CALLBACK = new DiffUtil.ItemCallback<Earthquake>() {
        @Override
        public boolean areItemsTheSame(@NonNull Earthquake oldItem, @NonNull Earthquake newItem) {
            return (oldItem.getId().equals(newItem.getId()));
        }

        @Override
        public boolean areContentsTheSame(@NonNull Earthquake oldItem, @NonNull Earthquake newItem) {
            return (oldItem.equals(newItem));
        }
    };
    protected EarthquakeAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public EarthquakeAdapter.EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEarthquakeBinding binding = ItemEarthquakeBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new EarthquakeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeAdapter.EarthquakeViewHolder holder, int position) {
        Earthquake earthquake = getItem(position);
        holder.bindEarthquake(earthquake);

    }

    static class EarthquakeViewHolder extends RecyclerView.ViewHolder {
        ItemEarthquakeBinding binding;
        public EarthquakeViewHolder(@NonNull ItemEarthquakeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindEarthquake(Earthquake earthquake){
            binding.setEarthquake(earthquake);
            binding.txtEarthquakeMag.setText(String.valueOf(earthquake.getMagnitude()));
            binding.txtEarthquakeTime.setText(String.valueOf(earthquake.getTime()));
            binding.txtEarthquakeLat.setText(String.valueOf(earthquake.getLatitude()));
            binding.txtEarthquakeLong.setText(String.valueOf(earthquake.getLongitude()));
        }
    }
}
