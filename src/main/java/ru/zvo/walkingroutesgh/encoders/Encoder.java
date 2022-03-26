package ru.zvo.walkingroutesgh.encoders;

import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.ev.DecimalEncodedValue;
import com.graphhopper.routing.ev.EncodedValue;
import com.graphhopper.routing.ev.UnsignedDecimalEncodedValue;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.FootFlagEncoder;
import com.graphhopper.storage.IntsRef;
import com.graphhopper.util.EdgeIteratorState;

import static com.graphhopper.routing.util.EncodingManager.getKey;

import java.util.List;

public class Encoder extends FootFlagEncoder {

    private DecimalEncodedValue sightsEncodedValue;

    @Override
    public void createEncodedValues(List<EncodedValue> registerNewEncodedValue, String prefix, int index) {
        super.createEncodedValues(registerNewEncodedValue, prefix, index);
        registerNewEncodedValue.add(sightsEncodedValue = new UnsignedDecimalEncodedValue(getKey(prefix, "sights_priority"), 10, 0.1, false));
    }


    @Override
    public EncodingManager.Access getAccess(ReaderWay way) {
        return super.getAccess(way);
    }

    @Override
    public IntsRef handleWayTags(IntsRef edgeFlags, ReaderWay way, EncodingManager.Access access) {
        IntsRef flags = super.handleWayTags(edgeFlags, way, access);
        sightsEncodedValue.setDecimal(false, flags, Double.parseDouble(way.getTag("sight_param")));
        return flags;
    }

    @Override
    public void applyWayTags(ReaderWay way, EdgeIteratorState edge) {
        String sight_param = way.getTag("sight_param");
        edge.set(sightsEncodedValue, Double.parseDouble(sight_param));
        super.applyWayTags(way, edge);
    }

}
