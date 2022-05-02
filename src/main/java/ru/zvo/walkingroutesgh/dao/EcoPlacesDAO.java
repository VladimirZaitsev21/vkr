package ru.zvo.walkingroutesgh.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.postgis.PGgeometry;
import org.springframework.stereotype.Repository;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dto.EcoPlace;
import ru.zvo.walkingroutesgh.dto.OsmType;
import ru.zvo.walkingroutesgh.dto.Sight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EcoPlacesDAO {

    private Connection connection;

    public EcoPlacesDAO() {
        connection = SpringContext.getBean(Connection.class);
    }

    public List<EcoPlace> getAllEcoPlaces() {
        List<EcoPlace> ecoPlaces = new ArrayList<>();
        try (Statement s = connection.createStatement();
             ResultSet r = s.executeQuery("select osm_id, impact, tags from eco_affecting_places")) {
            ObjectMapper mapper = new ObjectMapper();
            while (r.next()) {
                Map<String, String> tags = mapper.readValue(r.getString(3), new TypeReference<Map<String, String>>() {});
                ecoPlaces.add(new EcoPlace(r.getLong(1), r.getDouble(2), tags));
            }
        } catch (Exception e) {

        }
        return ecoPlaces;
    }

    public void save(EcoPlace ecoPlace) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("update eco_affecting_places set impact = ? where osm_id = ?;")) {
            preparedStatement.setDouble(1, ecoPlace.getImpact());
            preparedStatement.setLong(2, ecoPlace.getOsmId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
