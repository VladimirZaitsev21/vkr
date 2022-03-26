package ru.zvo.walkingroutesgh.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RouteDTO {
    private Long id;

    @JsonIgnore
    private List<Point> points;
    private double distance;
    private long time;
    private int rating;

    public RouteDTO() {
    }

    public RouteDTO(List<Point> points, double distance, long time, int rating) {
        this.points = points;
        this.distance = distance;
        this.time = time;
        this.rating = rating;
    }

    public RouteDTO(double[][] points, double distance, long time) {
        this.distance = distance;
        this.time = time;
        this.points = Arrays.stream(points).map(x -> new Point(x[0], x[1], this)).collect(Collectors.toList());
    }

    public RouteDTO(Long id, List<Point> points, double distance, long time) {
        this.id = id;
        this.points = points;
        this.distance = distance;
        this.time = time;
    }

    public double[][] getPointsAsArray() {
        double[][] arrayPoints = new double[points.size()][2];
        for (int i = 0; i < points.size(); i++) {
            arrayPoints[i][0] = points.get(i).getLatitude();
            arrayPoints[i][1] = points.get(i).getLongitude();
        }
        return arrayPoints;
    }

    @JsonSetter
    public void setPointsAsArray(double[][] arrayPoints) {
        points = new ArrayList<>();
        for (double[] arrayPoint : arrayPoints) {
            points.add(new Point(arrayPoint[0], arrayPoint[1], this));
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteDTO routeDTO = (RouteDTO) o;
        return Double.compare(routeDTO.distance, distance) == 0 && time == routeDTO.time && rating == routeDTO.rating && Objects.equals(id, routeDTO.id) && Objects.equals(points, routeDTO.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, points, distance, time, rating);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        points.forEach(x -> stringBuilder.append(x.toString()).append("\n"));
        return "RouteDTO{" +
                "id=" + id +
                ", points=" + stringBuilder.toString() +
                ", distance=" + distance +
                ", time=" + time +
                ", rating=" + rating +
                '}';
    }
}
