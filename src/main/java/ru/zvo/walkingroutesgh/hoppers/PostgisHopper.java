package ru.zvo.walkingroutesgh.hoppers;

import com.graphhopper.reader.postgis.GraphHopperPostgis;
import com.graphhopper.routing.WeightingFactory;
import com.graphhopper.routing.util.FootFlagEncoder;
import ru.zvo.walkingroutesgh.RequestParams;
import ru.zvo.walkingroutesgh.weightings.CustomWeightingFactory;

public class PostgisHopper extends GraphHopperPostgis {

    @Override
    protected WeightingFactory createWeightingFactory() {
        return new CustomWeightingFactory(super.getEncodingManager().getEncoder("foot"));
    }

}
