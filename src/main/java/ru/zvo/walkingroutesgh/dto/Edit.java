package ru.zvo.walkingroutesgh.dto;

public class Edit {

    private long id;

    private User user;

    private Sight sight;

    private String sightName;

    private String sightDescription;

    public Edit(User user, Sight sight, String sightName, String sightDescription) {
        this.user = user;
        this.sight = sight;
        this.sightName = sightName;
        this.sightDescription = sightDescription;
    }

    public Edit(long id, User user, Sight sight, String sightName, String sightDescription) {
        this.id = id;
        this.user = user;
        this.sight = sight;
        this.sightName = sightName;
        this.sightDescription = sightDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sight getSight() {
        return sight;
    }

    public void setSight(Sight sight) {
        this.sight = sight;
    }

    public String getSightName() {
        return sightName;
    }

    public void setSightName(String sightName) {
        this.sightName = sightName;
    }

    public String getSightDescription() {
        return sightDescription;
    }

    public void setSightDescription(String sightDescription) {
        this.sightDescription = sightDescription;
    }

    @Override
    public String toString() {
        return "Edit{" +
                "id=" + id +
                ", user=" + user +
                ", sight=" + sight +
                ", sightName='" + sightName + '\'' +
                ", sightDescription='" + sightDescription + '\'' +
                '}';
    }
}
