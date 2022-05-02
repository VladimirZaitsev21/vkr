package ru.zvo.walkingroutesgh.dto;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.postgis.Polygon;

import java.util.Arrays;
import java.util.Map;

/**
 * Describes an sight that may interest a pedestrian
 *
 * @author Vladimir Zaitsev
 */
public class Sight {

    /**
     * Type in OSM notation
     *
     * @see OsmType
     */
    private OsmType osmType;

    /**
     * id of this sight
     */
    private long osmId;

    /**
     * The name of the sight
     */
    private String name;

    /**
     * Points that make up the sight
     */
    private double[][] points;

    /**
     * Tags provided by OSM
     */
    private Map<String, String> tags;

    /**
     * The description of the sight
     */
    private String description;

    /**
     * The influence that sight have on the weight of the edge of the navigation graph
     */
    private double impact;

    /**
     * Sight constructor with parameters
     * @param osmType Type in OSM notation
     * @param osmId id of this sight
     * @param geometry Geometric representation of the sight
     * @param name The name of the sight
     * @param tags Tags provided by OSM
     * @param description The description of the sight
     * @param impact The influence that sight have on the weight of the edge of the navigation graph
     */
    public Sight(OsmType osmType, long osmId, PGgeometry geometry, String name, Map<String, String> tags, String description, double impact) {
        this.osmType = osmType;
        this.osmId = osmId;
        switch (geometry.getGeoType()) {
            case Geometry.POLYGON:
                Polygon pl = (Polygon) geometry.getGeometry();
                int n = pl.numPoints();
                points = new double[n][2];
                for (int i = 0; i < n; i++) {
                    points[i][0] = pl.getPoint(i).y;
                    points[i][1] = pl.getPoint(i).x;
                }
                break;
            case Geometry.POINT:
                Point point = (Point) geometry.getGeometry();
                points = new double[1][2];
                points[0][0] = point.y;
                points[0][1] = point.x;
                break;
        }
        this.name = name;
        this.tags = tags;
        this.description = description;
        this.impact = impact;
    }

    public OsmType getOsmType() {
        return osmType;
    }

    public void setOsmType(OsmType osmType) {
        this.osmType = osmType;
    }

    public long getOsmId() {
        return osmId;
    }

    public void setOsmId(long osmId) {
        this.osmId = osmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[][] getPoints() {
        return points;
    }

    public void setPoints(double[][] points) {
        this.points = points;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getImpact() {
        return impact;
    }

    public void setImpact(double impact) {
        this.impact = impact;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", points=[");
        for (int i = 0; i < points.length; i++) {
            sb.append("[lat=");
            sb.append(points[i][0]);
            sb.append(",lng=");
            sb.append(points[i][1]);
            sb.append("], ");
        }
        sb.append("]");
        return "Sight{" +
                "osmType=" + osmType +
                ", osmId=" + osmId +
                ", name='" + name + '\'' +
                sb +
                ", tags=" + tags +
                ", description='" + description + '\'' +
                ", impact=" + impact +
                '}';
    }
}
