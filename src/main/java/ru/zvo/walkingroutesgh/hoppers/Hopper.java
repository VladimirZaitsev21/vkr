package ru.zvo.walkingroutesgh.hoppers;

import com.graphhopper.json.geo.JsonFeatureCollection;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.WeightingFactory;
import ru.zvo.walkingroutesgh.weightings.CustomWeightingFactory;

public class Hopper extends GraphHopperOSM {

    public Hopper() {
        super();
    }

    public Hopper(JsonFeatureCollection landmarkSplittingFeatureCollection) {
        super(landmarkSplittingFeatureCollection);
    }

    @Override
    protected WeightingFactory createWeightingFactory() {
        return new CustomWeightingFactory(super.getEncodingManager().getEncoder("foot"));
    }
}

