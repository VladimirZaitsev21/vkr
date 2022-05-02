package ru.zvo.walkingroutesgh.weightings;

import com.graphhopper.config.Profile;
import com.graphhopper.routing.WeightingFactory;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.util.PMap;

public class SightWeightingFactory implements WeightingFactory {

    private FlagEncoder flagEncoder;

    public SightWeightingFactory(FlagEncoder flagEncoder) {
        this.flagEncoder = flagEncoder;
    }

    @Override
    public Weighting createWeighting(Profile profile, PMap hints, boolean disableTurnCosts) {
        return new SightWeighting(flagEncoder, hints);
    }
}
