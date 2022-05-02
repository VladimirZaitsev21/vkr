package ru.zvo.walkingroutesgh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.zvo.walkingroutesgh.PathCalculator;
import ru.zvo.walkingroutesgh.dao.EcoPlacesDAO;
import ru.zvo.walkingroutesgh.dao.SightsDAO;
import ru.zvo.walkingroutesgh.dto.EcoPlace;
import ru.zvo.walkingroutesgh.dto.RouteDTO;
import ru.zvo.walkingroutesgh.dto.Sight;

import java.util.*;

@Controller
public class RouteController {

    private PathCalculator pathCalculator;
    private SightsDAO sightsDAO;
    private EcoPlacesDAO ecoPlacesDAO;

    public RouteController(PathCalculator pathCalculator, SightsDAO sightsDAO, EcoPlacesDAO ecoPlacesDAO) {
        this.pathCalculator = pathCalculator;
        this.sightsDAO = sightsDAO;
        this.ecoPlacesDAO = ecoPlacesDAO;
    }

    @GetMapping("/sights_path_dto")
    @ResponseBody
    public RouteDTO getSightsPath(@RequestParam(name = "fromLat") double fromLatitude,
                                  @RequestParam(name = "fromLng") double fromLongitude,
                                  @RequestParam(name = "toLat") double toLatitude,
                                  @RequestParam(name = "toLng") double toLongitude,
                                  @RequestParam(name = "ratio") double ratio) {
        System.out.println("RATIO: "+ ratio);
        RouteDTO routeDTO = pathCalculator.getSightsPath(fromLatitude, fromLongitude, toLatitude, toLongitude, ratio);
        return routeDTO;
    }

    @GetMapping("/eco_path_dto")
    @ResponseBody
    public RouteDTO getEcoPath(@RequestParam(name = "fromLat") double fromLatitude,
                               @RequestParam(name = "fromLng") double fromLongitude,
                               @RequestParam(name = "toLat") double toLatitude,
                               @RequestParam(name = "toLng") double toLongitude,
                               @RequestParam(name = "ratio") double ratio) {
//        List<EcoPlace> ecoPlaces = ecoPlacesDAO.getAllEcoPlaces();
//        for (EcoPlace ecoPlace: ecoPlaces) {
//            String leisure = ecoPlace.getTags().get("leisure");
//            if (leisure != null) {
//                if (leisure.equals("park")) {
//                    ecoPlace.setImpact(50);
//                }
//                if (leisure.equals("garden")) {
//                    ecoPlace.setImpact(40);
//                }
//                if (leisure.equals("nature_reserve")) {
//                    ecoPlace.setImpact(30);
//                }
//            }
//            String amenity = ecoPlace.getTags().get("amenity");
//            if (amenity != null) {
//                if (amenity.equals("industrial")) {
//                    ecoPlace.setImpact(-20);
//                }
//                if (amenity.equals("fuel")) {
//                    ecoPlace.setImpact(-10);
//                }
//            }
//        }
//        ecoPlaces.forEach(System.out::println);
//        ecoPlaces.forEach(ecoPlacesDAO::save);
        return pathCalculator.getEcoPath(fromLatitude, fromLongitude, toLatitude, toLongitude, ratio);
    }

    @GetMapping("/standard_path_dto")
    @ResponseBody
    public RouteDTO getStandardPath(@RequestParam(name = "fromLat") double fromLatitude,
                                    @RequestParam(name = "fromLng") double fromLongitude,
                                    @RequestParam(name = "toLat") double toLatitude,
                                    @RequestParam(name = "toLng") double toLongitude) {
        return pathCalculator.getStandardPath(fromLatitude, fromLongitude, toLatitude, toLongitude);
    }

    @GetMapping("/sights")
    @ResponseBody
    public List<Sight> getSights() {
        System.out.println("SIGHTS REQ");
        return sightsDAO.getAllSights();
    }
}
