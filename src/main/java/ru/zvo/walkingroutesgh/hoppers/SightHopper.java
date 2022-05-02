package ru.zvo.walkingroutesgh.hoppers;

import com.graphhopper.reader.postgis.GraphHopperPostgis;
import com.graphhopper.routing.WeightingFactory;
import ru.zvo.walkingroutesgh.weightings.SightWeightingFactory;

public class SightHopper extends GraphHopperPostgis {

    @Override
    protected WeightingFactory createWeightingFactory() {
        return new SightWeightingFactory(super.getEncodingManager().getEncoder("foot"));
    }

}
