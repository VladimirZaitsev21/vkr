package ru.zvo.walkingroutesgh.weightings;

import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.weighting.FastestWeighting;
import com.graphhopper.routing.weighting.TurnCostProvider;
import com.graphhopper.util.EdgeIteratorState;
import com.graphhopper.util.PMap;
import ru.zvo.walkingroutesgh.RequestParams;

public class EcoWeighting extends FastestWeighting {

    public EcoWeighting(FlagEncoder encoder) {
        super(encoder);
    }

    public EcoWeighting(FlagEncoder encoder, TurnCostProvider turnCostProvider) {
        super(encoder, turnCostProvider);
    }

    public EcoWeighting(FlagEncoder encoder, PMap map, TurnCostProvider turnCostProvider) {
        super(encoder, map, turnCostProvider);
    }

    public EcoWeighting(FlagEncoder encoder, PMap map) {
        super(encoder, map);
    }

    @Override
    public double calcEdgeWeight(EdgeIteratorState edgeState, boolean reverse) {
        double ecoParam = edgeState.get(flagEncoder.getDecimalEncodedValue("foot.eco_priority"));
        double superWeight = super.calcEdgeWeight(edgeState, reverse);
        if (ecoParam > 0.0) {
            ecoParam *= 50;
            if (RequestParams.getInstance().getEcoFactor() > 0) {
                ecoParam *= RequestParams.getInstance().getEcoFactor();
            }
            return superWeight / ecoParam;
        } else {
            return superWeight * 2;
        }
    }
}
