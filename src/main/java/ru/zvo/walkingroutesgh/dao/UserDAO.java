package ru.zvo.walkingroutesgh.dao;

import org.springframework.stereotype.Repository;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dto.Role;
import ru.zvo.walkingroutesgh.dto.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Provides the ability to manipulate sigths in the repository (database)
 *
 * @author Vladimir Zaitsev
 */
@Repository
public class UserDAO {

    /**
     * Connection to the database
     */
    private Connection connection;

    /**
     * No-args constructor
     */
    public UserDAO() {
        connection = SpringContext.getBean(Connection.class);
    }

    /**
     * Stores a user that does not exist in the database
     *
     * @param user user to save
     */
    public void saveNewUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO public.users (login, \"password\", blocked, role_id, online) VALUES (?, ?, ?, (SELECT role_id FROM roles WHERE role_name = ? LIMIT 1), ?);")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, user.isBlocked());
            preparedStatement.setString(4, user.getRole().toString().toLowerCase(Locale.ROOT));
            preparedStatement.setBoolean(5, user.isOnline());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stores a user that exists in the database
     *
     * @param user user to save
     */
    public void saveExistingUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "update users set login = ?, password = ?, role_id = (SELECT role_id FROM roles WHERE role_name = ? LIMIT 1), blocked = ?, online = ? where user_id = ?;")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().toString().toLowerCase(Locale.ROOT));
            preparedStatement.setBoolean(4, user.isBlocked());
            preparedStatement.setBoolean(5, user.isOnline());
            preparedStatement.setLong(6, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes specified user from database
     *
     * @param id id of the user to be deleted
     */
    public void deleteUser(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from users where user_id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets user's status in the database
     *
     * @param login user login
     * @param isBlocked new status
     */
    public void setUserBlocked(String login, boolean isBlocked) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("update users set blocked = ? where login = ?;")) {
            preparedStatement.setBoolean(1, isBlocked);
            preparedStatement.setString(2, login);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all existing in the database users
     *
     * @return all existing in the database users
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select user_id, login, password, role_name, blocked, online from users join roles on users.role_id = roles.role_id;");
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        Role.valueOf(resultSet.getString(4).toUpperCase(Locale.ROOT)),
                        resultSet.getBoolean(5),
                        resultSet.getBoolean(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Returns user with specified login
     *
     * @param login login to search by
     * @return user with specified login
     */
    public User getUserByLogin(String login) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select user_id, login, password, role_name, blocked, online from users " +
                        "join roles on users.role_id = roles.role_id where login = ?;")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        Role.valueOf(resultSet.getString(4).toUpperCase(Locale.ROOT)),
                        resultSet.getBoolean(5),
                        resultSet.getBoolean(6)
                );
            }
        } catch (SQLException e) {

        }
        return user;
    }

    /**
     * Returns user with specified id
     *
     * @param userId id to search by
     * @return user with specified id
     */
    public User getUserById(Long userId) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select user_id, login, password, role_name, blocked, online from users join roles on users.role_id = roles.role_id where users.user_id = ?;")) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        Role.valueOf(resultSet.getString(4).toUpperCase(Locale.ROOT)),
                        resultSet.getBoolean(5),
                        resultSet.getBoolean(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
