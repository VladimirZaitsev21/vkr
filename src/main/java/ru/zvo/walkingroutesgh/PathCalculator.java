package ru.zvo.walkingroutesgh;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.routing.AlgorithmOptions;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.weighting.FastestWeighting;
import com.graphhopper.util.Parameters;
import org.springframework.stereotype.Component;
import ru.zvo.walkingroutesgh.dto.RouteDTO;

import java.util.Arrays;

@Component
public class PathCalculator {

    public static final int EARTH_RADIUS = 6_378_137;
    private GraphHopper sightsHopper;
    private GraphHopper standardHopper;
    private FlagEncoder sightsEncoder;
    private FlagEncoder standardEncoder;

    public PathCalculator(GraphHopper sightsHopper, GraphHopper standardHopper, FlagEncoder sightsEncoder, FlagEncoder standardEncoder) {
        this.sightsHopper = sightsHopper;
        this.standardHopper = standardHopper;
        this.sightsEncoder = sightsEncoder;
        this.standardEncoder = standardEncoder;
    }

    public RouteDTO getSightsPath(double fromLat, double fromLng, double toLat, double toLng, double ecoFactor) {
        RequestParams.getInstance().setEcoFactor(ecoFactor);
        GHRequest request = new GHRequest(fromLat, fromLng, toLat, toLng)
                .setAlgorithm(
                        new AlgorithmOptions(
                                Parameters.Algorithms.ASTAR.toString(),
                                new FastestWeighting(
                                        sightsEncoder
                                )
                        ).getAlgorithm()
                )
                .setProfile("my_foot");
        GHResponse response = sightsHopper.route(request);
        ResponsePath responsePath = response.getBest();
        int pointListSize = responsePath.getPoints().getSize();
        double[][] points = new double[pointListSize][2];
        for (int i = 0; i < pointListSize; i++) {
            points[i][0] = responsePath.getPoints().getLat(i);
            points[i][1] = responsePath.getPoints().getLon(i);
        }
        RouteDTO routeDTO = new RouteDTO(points, responsePath.getDistance(), responsePath.getTime());
        return routeDTO;
    }


    public RouteDTO getStandardPath(double fromLat, double fromLng, double toLat, double toLng) {
        GHRequest request = new GHRequest(fromLat, fromLng, toLat, toLng)
                .setAlgorithm(
                        new AlgorithmOptions(
                                Parameters.Algorithms.ASTAR.toString(),
                                new FastestWeighting(
                                        standardEncoder
                                )
                        ).getAlgorithm()
                )
                .setProfile("my_foot");
        GHResponse response = standardHopper.route(request);
        ResponsePath responsePath = response.getBest();
        int pointListSize = responsePath.getPoints().getSize();
        double[][] points = new double[pointListSize][2];
        for (int i = 0; i < pointListSize; i++) {
            points[i][0] = responsePath.getPoints().getLat(i);
            points[i][1] = responsePath.getPoints().getLon(i);
        }
        RouteDTO routeDTO = new RouteDTO(points, responsePath.getDistance(), responsePath.getTime());
        return routeDTO;
    }


    /**
     * Calculates shortest distance between two routes using haversine formula.
     *
     * @param firstRoutePoints - geo points of first route
     * @param secondRoutePoints - geo points of second route
     * @return shortest distance between two routes in meters
     */
    public double calculateShortestDistance(double[][] firstRoutePoints, double[][] secondRoutePoints) {
        double[][] distances = new double[firstRoutePoints.length][secondRoutePoints.length];
        for (int i = 0; i < firstRoutePoints.length; i++) { //цикл по точкам первого массива
            for (int j = 0; j < secondRoutePoints.length; j++) { //цикл для расчета с i-й точкой первого
                distances[i][j] = calculateTwoPointsDistance(   // по всем точкам второго массива
                        firstRoutePoints[i][0],
                        firstRoutePoints[i][1],
                        secondRoutePoints[j][0],
                        secondRoutePoints[j][1]
                );
            }
        }
        return Arrays.stream(distances).flatMapToDouble(Arrays::stream).min().getAsDouble();
    }

    /**
     * Implements haversine formula.
     * @param lat1 latitude of the first point
     * @param lon1 longitude of the first point
     * @param lat2 latitude of the second point
     * @param lon2 longitude of the second point
     * @return distance between two points in meters
     */
    private double calculateTwoPointsDistance(double lat1, double lon1, double lat2, double lon2) {
        double radLon1 = lon1 * Math.PI / 180;
        double radLat1 = lat1 * Math.PI / 180;
        double radLon2 = lon2 * Math.PI / 180;
        double radLat2 = lat2 * Math.PI / 180;
        return 2 * EARTH_RADIUS * Math.asin(
                Math.sqrt(
                        Math.sin((radLat2 - radLat1) / 2) * Math.sin((radLat2 - radLat1) / 2)
                        + Math.cos(radLat1) * Math.cos(radLat2)
                        * Math.sin((radLon2 - radLon1) / 2) * Math.sin((radLon2 - radLon1) / 2)
                )
        );
    }

}
