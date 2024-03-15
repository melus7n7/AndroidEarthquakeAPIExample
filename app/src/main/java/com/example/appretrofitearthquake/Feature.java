package com.example.appretrofitearthquake;

import com.squareup.moshi.Json;


public class Feature {
    private String id;
    @Json(name="properties")
    private Property property;
    private Geometry geometry;
    public String getId() {
        return id;
    }
    public Property getProperty() {
        return property;
    }
    public Geometry getGeometry() {
        return geometry;
    }
}
