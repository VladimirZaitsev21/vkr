package ru.zvo.walkingroutesgh.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
public class Point {

    private Long id;

    private double latitude;

    private double longitude;

    @JsonIgnore
    private RouteDTO ownerRoute;

    public Point() {
    }

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Point(double latitude, double longitude, RouteDTO ownerRoute) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.ownerRoute = ownerRoute;
    }

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
