package ru.zvo.walkingroutesgh.dto;

public class User {

    private long id;

    private String login;

    private String password;

    private Role role;

    private boolean blocked;

    private boolean online;

    public User(String login, String password, Role role, boolean blocked, boolean online) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
        this.online = online;
    }

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
