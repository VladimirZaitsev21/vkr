package ru.zvo.walkingroutesgh.dto;

import java.util.Map;

public class EcoPlace {

    private long osmId;

    private double impact;

    private Map<String, String> tags;

    public EcoPlace(long osmId, double impact, Map<String, String> tags) {
        this.osmId = osmId;
        this.impact = impact;
        this.tags = tags;
    }

    public long getOsmId() {
        return osmId;
    }

    public void setOsmId(long osmId) {
        this.osmId = osmId;
    }

    public double getImpact() {
        return impact;
    }

    public void setImpact(double impact) {
        this.impact = impact;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "EcoPlace{" +
                "osmId=" + osmId +
                ", impact=" + impact +
                ", tags=" + tags +
                '}';
    }
}
