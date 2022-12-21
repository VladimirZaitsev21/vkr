package ru.zvo.walkingroutesgh;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.util.PointList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PathCalculatorTest {

    @Mock
    private GraphHopper sightsHopper;
    @Mock
    private GraphHopper standardHopper;
    @Mock
    private GraphHopper ecoHopper;
    @Mock
    private FlagEncoder sightsEncoder;
    @Mock
    private FlagEncoder standardEncoder;
    @Mock
    private FlagEncoder ecoEncoder;
    private PathCalculator instance;

    @BeforeEach
    public void initInstance() {
        instance = new PathCalculator(sightsHopper, standardHopper, ecoHopper, sightsEncoder, standardEncoder, ecoEncoder);
    }

    @Test
    public void calculateShortestDistanceTest_ok() {
        double[][] firstPoints = {
                {54.6241561570502, 39.6994643688384},
                {54.62651896387008, 39.71587396523075}
        };
        double[][] secondPoints = {
                {54.626532933709015, 39.71641599498129},
                {54.62543394390203, 39.71596465510483}
        };
        assertEquals(34.96476926053491, instance.calculateShortestDistance(firstPoints, secondPoints));
    }

    @Test
    public void calculateShortestDistanceTest_ok2() {
        double[][] firstPoints = {
                {54.6241561570502, 39.6994643688384},
                {54.62651896387008, 39.71587396523075},
                {54.62543394390203, 39.71596465510483}
        };
        double[][] secondPoints = {
                {54.626532933709015, 39.71641599498129},
        };
        assertEquals(34.96476926053491, instance.calculateShortestDistance(firstPoints, secondPoints));
    }

    @Test
    public void calculateShortestDistanceTest_ok3() {
        double[][] firstPoints = {
                {54.6241561570502, 39.6994643688384},
        };
        double[][] secondPoints = {
                {54.626532933709015, 39.71641599498129},
                {54.62651896387008, 39.71587396523075},
                {54.62543394390203, 39.71596465510483}
        };
        assertEquals(1072.84897946056, instance.calculateShortestDistance(firstPoints, secondPoints));
    }

    @Test
    public void calculateShortestDistanceTest_fail() {
        double[][] firstPoints = {};
        double[][] secondPoints = {
                {54.626532933709015, 39.71641599498129},
                {54.62543394390203, 39.71596465510483}
        };
        assertEquals(-1, instance.calculateShortestDistance(firstPoints, secondPoints));
    }

    @Test
    public void calculateShortestDistanceTest_fail2() {
        double[][] firstPoints = {
                {54.626532933709015, 39.71641599498129},
                {54.62543394390203, 39.71596465510483}
        };
        double[][] secondPoints = {};
        assertEquals(-1, instance.calculateShortestDistance(firstPoints, secondPoints));
    }

    @Test
    public void getStandardPathTest() {
        var responsePath = new ResponsePath();
        var response = new GHResponse();
        responsePath.setDistance(3470.7698438911216);
        responsePath.setTime(2498916);
        var pointList = new PointList();
        pointList.add(54.63064431692954, 39.701655884235834);
        pointList.add(54.630105859714135, 39.70083291905);
        pointList.add(54.63005892105532, 39.70094784425827);
        pointList.add(54.631208918196165, 39.702719964892864);
        pointList.add(54.63133986215308, 39.70294590375452);
        responsePath.setPoints(pointList);
        response.add(responsePath);

        when(standardHopper.route(new GHRequest(
                54.63064431692954, 39.701655884235834,
                54.61725201136825, 39.737202121011045
        ))).thenReturn(response);

        var route = instance.getStandardPath(
                54.63064431692954, 39.701655884235834,
                54.61725201136825, 39.737202121011045
        );

        assertEquals(3470.7698438911216, route.getDistance());
        assertEquals(2498916, route.getTime());

    }

}