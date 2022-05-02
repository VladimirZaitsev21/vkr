package ru.zvo.walkingroutesgh.encoders;

import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.ev.DecimalEncodedValue;
import com.graphhopper.routing.ev.EncodedValue;
import com.graphhopper.routing.ev.UnsignedDecimalEncodedValue;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.FootFlagEncoder;
import com.graphhopper.storage.IntsRef;
import com.graphhopper.util.EdgeIteratorState;

import java.util.List;

import static com.graphhopper.routing.util.EncodingManager.getKey;

public class EcoEncoder extends FootFlagEncoder {

//    private DecimalEncodedValue sightsEncodedValue;
    private DecimalEncodedValue ecoEncodedValue;

    @Override
    public void createEncodedValues(List<EncodedValue> registerNewEncodedValue, String prefix, int index) {
        super.createEncodedValues(registerNewEncodedValue, prefix, index);
//        registerNewEncodedValue.add(sightsEncodedValue = new UnsignedDecimalEncodedValue(getKey(prefix, "sights_priority"), 10, 0.1, false));
        registerNewEncodedValue.add(ecoEncodedValue = new UnsignedDecimalEncodedValue(getKey(prefix, "eco_priority"), 10, 0.1, false));
    }


    @Override
    public EncodingManager.Access getAccess(ReaderWay way) {
        return super.getAccess(way);
    }

    @Override
    public IntsRef handleWayTags(IntsRef edgeFlags, ReaderWay way, EncodingManager.Access access) {
        IntsRef flags = super.handleWayTags(edgeFlags, way, access);
//        sightsEncodedValue.setDecimal(false, flags, Double.parseDouble(way.getTag("sight_param")));
        ecoEncodedValue.setDecimal(false, flags, Double.parseDouble(way.getTag("eco_param")) + 10);
        return flags;
    }

    @Override
    public void applyWayTags(ReaderWay way, EdgeIteratorState edge) {
//        String sight_param = way.getTag("sight_param");
        String eco_param = way.getTag("eco_param");

//        edge.set(sightsEncodedValue, Double.parseDouble(sight_param));
        edge.set(ecoEncodedValue, Double.parseDouble(eco_param) + 10);

        super.applyWayTags(way, edge);
    }
}
