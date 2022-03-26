package ru.zvo.walkingroutesgh.weightings;

import com.graphhopper.routing.ev.DecimalEncodedValue;
import com.graphhopper.routing.ev.UnsignedDecimalEncodedValue;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.weighting.AbstractWeighting;
import com.graphhopper.routing.weighting.FastestWeighting;
import com.graphhopper.routing.weighting.TurnCostProvider;
import com.graphhopper.util.EdgeIteratorState;
import com.graphhopper.util.PMap;
import ru.zvo.walkingroutesgh.RequestParams;

import java.util.ArrayList;
import java.util.List;

import static com.graphhopper.routing.util.EncodingManager.getKey;

public class CustomWeighting extends FastestWeighting {

    private double superWeight;
    private List<Double> weights = new ArrayList<>();

    public CustomWeighting(FlagEncoder encoder) {
        super(encoder);
    }

    public CustomWeighting(FlagEncoder encoder, TurnCostProvider turnCostProvider) {
        super(encoder, turnCostProvider);
    }

    public CustomWeighting(FlagEncoder encoder, PMap map) {
        super(encoder, map);
        System.out.println(super.getFlagEncoder().toString());
        RequestParams.getInstance().setWeights(weights);
    }

    public CustomWeighting(FlagEncoder encoder, PMap map, TurnCostProvider turnCostProvider) {
        super(encoder, map, turnCostProvider);
    }

    @Override
    public double calcEdgeWeight(EdgeIteratorState edgeState, boolean reverse) {
        double sightParam = edgeState.get(flagEncoder.getDecimalEncodedValue("foot.sights_priority"));
        superWeight = super.calcEdgeWeight(edgeState, reverse);
        weights.add(superWeight);
        if (sightParam > 0.0) {
            sightParam *= 10;
            if (RequestParams.getInstance().getEcoFactor() > 0) {
                sightParam *= RequestParams.getInstance().getEcoFactor();
                return superWeight / sightParam;
            } else {
                return superWeight;
            }
        } else {
            return superWeight;
        }

    }
}
