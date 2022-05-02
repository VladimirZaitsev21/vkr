package ru.zvo.walkingroutesgh.dao;

import org.springframework.stereotype.Repository;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dto.Edit;
import ru.zvo.walkingroutesgh.dto.Sight;
import ru.zvo.walkingroutesgh.dto.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides the ability to manipulate edits in the repository (database)
 *
 * @author Vladimir Zaitsev
 */
@Repository
public class EditsDAO {

    /**
     * Connection to the database
     */
    private Connection connection;

    /**
     * Class which provides the ability to manipulate users in the repository
     *
     * @see UserDAO
     */
    private UserDAO userDAO;

    /**
     * Class which provides the ability to manipulate sights in the repository
     *
     * @see SightsDAO
     */
    private SightsDAO sightsDAO;

    /**
     * No-arguments EditsDAO constructor
     */
    public EditsDAO() {
        userDAO = SpringContext.getBean(UserDAO.class);
        sightsDAO = SpringContext.getBean(SightsDAO.class);
        connection = SpringContext.getBean(Connection.class);
    }

    /**
     * Saves provided edit in the database
     * @param edit edit to save
     */
    public void save(Edit edit) {
        if (getEditById(edit.getId()) != null) {
            System.out.println("getEditById(edit.getId()) != null");
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "update edits set name = ?, set description = ? where edit_id = ?;"
            )) {
                preparedStatement.setString(1, edit.getSightName());
                preparedStatement.setString(2, edit.getSightDescription());
                preparedStatement.setLong(3, edit.getId());
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("else");
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert  into edits(user_id, sight_id, description, sight_name)" +
                            "values (?, ?, ?, ?);"
            )) {
                System.out.println("PARAMS: " + edit.getUser().getId() + ", " + edit.getSight().getOsmId() + ", " + edit.getSightDescription() + ", " + edit.getSightName());
                preparedStatement.setLong(1, edit.getUser().getId());
                preparedStatement.setLong(2, edit.getSight().getOsmId());
                preparedStatement.setString(3, edit.getSightDescription());
                preparedStatement.setString(4, edit.getSightName());
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the edit found by id
     *
     * @param id id to search by
     * @return edit found by id
     */
    public Edit getEditById(long id) {
        Edit edit = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "select edit_id, user_id, sight_id, description, sight_name from edits where edit_id = ?;")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = userDAO.getUserById(resultSet.getLong(2));
                Sight sight = sightsDAO.getSightById(resultSet.getLong(3));
                edit = new Edit(user, sight, resultSet.getString(5), resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return edit;
    }

    /**
     * Returns edits made by the specified user
     *
     * @param user user whose edits are need to be received
     * @return edits made by the specified user
     */
    public List<Edit> getUserEdits(User user) {
        List<Edit> edits = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "select edit_id, user_id, sight_id, description, sight_name from edits where user_id = ?;")) {
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sight sight = sightsDAO.getSightById(resultSet.getLong(3));
                edits.add(new Edit(resultSet.getLong(1), user, sight, resultSet.getString(5), resultSet.getString(4)));
            }
        } catch (SQLException e) {

        }
        return edits;
    }

    /**
     * Deletes edit with specified id
     *
     * @param id id of the edit that should be deleted
     */
    public void deleteEdit(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from edits where edit_id = ?;")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all edits stored in database
     *
     * @return all edits stored in database
     */
    public List<Edit> getAllEdits() {
        List<Edit> edits = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select edit_id, user_id, sight_id, description, sight_name from edits;");
            while (resultSet.next()) {
                User user = userDAO.getUserById(resultSet.getLong(2));
                Sight sight = sightsDAO.getSightById(resultSet.getLong(3));
                edits.add(new Edit(resultSet.getLong(1), user, sight, resultSet.getString(5), resultSet.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return edits;
    }

}
