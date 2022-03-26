package ru.zvo.walkingroutesgh.dao;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.stereotype.Repository;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dto.Edit;
import ru.zvo.walkingroutesgh.dto.Sight;
import ru.zvo.walkingroutesgh.dto.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EditsDAO {

    private Connection connection;
    private UserDAO userDAO;
    private SightsDAO sightsDAO;

    public EditsDAO() {
        userDAO = SpringContext.getBean(UserDAO.class);
        sightsDAO = SpringContext.getBean(SightsDAO.class);
        connection = SpringContext.getBean(Connection.class);
    }

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

    public void deleteEdit(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from edits where edit_id = ?;")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
