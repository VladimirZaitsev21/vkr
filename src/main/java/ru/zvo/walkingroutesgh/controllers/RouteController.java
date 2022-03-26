package ru.zvo.walkingroutesgh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.zvo.walkingroutesgh.PathCalculator;
import ru.zvo.walkingroutesgh.dao.SightsDAO;
import ru.zvo.walkingroutesgh.dto.RouteDTO;
import ru.zvo.walkingroutesgh.dto.Sight;

import java.util.*;

@Controller
public class RouteController {

    private PathCalculator pathCalculator;
    private SightsDAO sightsDAO;

    public RouteController(PathCalculator pathCalculator, SightsDAO sightsDAO) {
        this.pathCalculator = pathCalculator;
        this.sightsDAO = sightsDAO;
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
        return sightsDAO.getAllSights();
    }
}
