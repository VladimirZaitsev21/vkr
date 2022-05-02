package ru.zvo.walkingroutesgh.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

/**
 * Describes a geographical point on the terrain.
 *
 * @author Vladimir Zaitsev
 */
public class Point {

    /**
     * id of the point in OpenStreetMap notation
     */
    private Long id;

    /**
     * Latitude coordinate in EPSG:4326 projection
     */
    private double latitude;

    /**
     * Longitude coordinate in EPSG:4326 projection
     */
    private double longitude;

    /**
     * Route that owns this point
     */
    @JsonIgnore
    private RouteDTO ownerRoute;

    /**
     * Default constructor
     */
    public Point() {
    }

    /**
     * Longitude and latitude point contsructor
     * @param latitude Latitude coordinate in EPSG:4326 projection
     * @param longitude Longitude coordinate in EPSG:4326 projection
     */
    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Point constructor with RouteDTO
     * @param latitude Latitude coordinate in EPSG:4326 projection
     * @param longitude Longitude coordinate in EPSG:4326 projection
     * @param ownerRoute Route that owns this point
     */
    public Point(double latitude, double longitude, RouteDTO ownerRoute) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.ownerRoute = ownerRoute;
    }

    /**
     * All properties point constructor
     * @param id id of the point in OpenStreetMap notation
     * @param latitude Latitude coordinate in EPSG:4326 projection
     * @param longitude Longitude coordinate in EPSG:4326 projection
     * @param ownerRoute Route that owns this point
     */
    public Point(Long id, double latitude, double longitude, RouteDTO ownerRoute) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ownerRoute = ownerRoute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public RouteDTO getOwnerRoute() {
        return ownerRoute;
    }

    public void setOwnerRoute(RouteDTO ownerRoute) {
        this.ownerRoute = ownerRoute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.latitude, latitude) == 0 && Double.compare(point.longitude, longitude) == 0 && Objects.equals(id, point.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
