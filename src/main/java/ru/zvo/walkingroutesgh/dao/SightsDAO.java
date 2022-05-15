package ru.zvo.walkingroutesgh.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import org.postgis.PGgeometry;
import org.postgresql.util.PGobject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dto.OsmType;
import ru.zvo.walkingroutesgh.dto.Sight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Provides the ability to manipulate sights in the repository (database)
 *
 * @author Vladimir Zaitsev
 */
@Repository
public class SightsDAO {

    /**
     * Connection to the database
     */
    private Connection connection;

    /**
     * No-args constructor
     */
    public SightsDAO() {
        connection = SpringContext.getBean(Connection.class);
        try {
            ((org.postgresql.PGConnection) connection).addDataType("geometry", (Class<? extends PGobject>) Class.forName("org.postgis.PGgeometry"));
            ((org.postgresql.PGConnection) connection).addDataType("box3d", (Class<? extends PGobject>) Class.forName("org.postgis.PGbox3d"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all sights stored in database
     *
     * @return all sights stored in database
     */
    public List<Sight> getAllSights() {
        List<Sight> sights = new ArrayList<>();
        try (Statement s = connection.createStatement();
             ResultSet r = s.executeQuery("select geom, osm_id, osm_type, name, description, impact, tags from sights")) {
            ObjectMapper mapper = new ObjectMapper();
            while (r.next()) {
                PGgeometry geom = (PGgeometry) r.getObject(1);
                Map<String, String> tags = mapper.readValue(r.getString(7), new TypeReference<Map<String, String>>() {});
                sights.add(new Sight(OsmType.valueOf(r.getString(3)),
                        r.getLong(2),
                        geom,
                        r.getString(4),
                        tags,
                        r.getString(5),
                        r.getDouble(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sights;
    }

    /**
     * Stores specified sight into the database
     *
     * @param sightToSave sight that should be saved
     */
    public void saveSight(Sight sightToSave) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("update sights set name = ?, description = ? where osm_id = ?;")) {
            preparedStatement.setString(1, sightToSave.getName());
            preparedStatement.setString(2, sightToSave.getDescription());
            preparedStatement.setLong(3, sightToSave.getOsmId());
            preparedStatement.execute();
        } catch (SQLException e) {

        }
    }

    /**
     * Returns the sight found by specified id
     *
     * @param id id to search by
     * @return sight found by id
     */
    public Sight getSightById(long id) {
        Sight sight = null;
        try (PreparedStatement s = connection.prepareStatement("select geom, osm_id, osm_type, name, description, impact, tags from sights where osm_id = ?");) {
            s.setLong(1, id);
            ResultSet r = s.executeQuery();
            ObjectMapper mapper = new ObjectMapper();
            while (r.next()) {
                PGgeometry geom = (PGgeometry) r.getObject(1);
                Map<String, String> tags = mapper.readValue(r.getString(7), new TypeReference<Map<String, String>>() {});
                sight = new Sight(OsmType.valueOf(r.getString(3)),
                        r.getLong(2),
                        geom,
                        r.getString(4),
                        tags,
                        r.getString(5),
                        r.getDouble(6));
            }
        } catch (Exception e) {

        }
        return sight;
    }
}
