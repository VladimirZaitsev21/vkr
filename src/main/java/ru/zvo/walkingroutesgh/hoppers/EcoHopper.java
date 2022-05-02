package ru.zvo.walkingroutesgh.hoppers;

import com.graphhopper.reader.postgis.GraphHopperPostgis;
import com.graphhopper.routing.WeightingFactory;
import ru.zvo.walkingroutesgh.weightings.EcoWeightingFactory;

public class EcoHopper extends GraphHopperPostgis {

    @Override
    protected WeightingFactory createWeightingFactory() {
        return new EcoWeightingFactory(super.getEncodingManager().getEncoder("foot"));
    }

}
