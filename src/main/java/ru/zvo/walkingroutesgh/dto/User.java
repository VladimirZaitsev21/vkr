package ru.zvo.walkingroutesgh.dto;

/**
 * Describes user of this system
 *
 * @author Vladimir Zaitsev
 */
public class User {

    /**
     * User id in system
     */
    private long id;

    /**
     * User login
     */
    private String login;

    /**
     * User password
     */
    private String password;

    /**
     * Role of this user
     *
     * @see Role
     */
    private Role role;

    /**
     * Is user blocked in the system
     */
    private boolean blocked;

    /**
     * Is user logged in
     */
    private boolean online;

    /**
     * User constructor with parameters
     *
     * @param login User login
     * @param password User password
     * @param role User role
     * @param blocked Is user blocked in the system
     * @param online Is user logged in
     */
    public User(String login, String password, Role role, boolean blocked, boolean online) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
        this.online = online;
    }

    /**
     * User constructor with parameters
     *
     * @param id User id in system
     * @param login User login
     * @param password User password
     * @param role User role
     * @param blocked Is user blocked in the system
     * @param online Is user logged in
     */
    public User(long id, String login, String password, Role role, boolean blocked, boolean online) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
        this.online = online;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", blocked=" + blocked +
                ", online=" + online +
                '}';
    }
}
