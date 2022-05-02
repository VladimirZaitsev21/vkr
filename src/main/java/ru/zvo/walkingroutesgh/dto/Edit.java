package ru.zvo.walkingroutesgh.dto;

/**
 * Describes the correction of the name and description of the attraction that a local historian can make.
 *
 * @author Vladimir Zaitsev
 */
public class Edit {

    /**
     * id of current Edit
     */
    private long id;

    /**
     * The user who made the edit
     */
    private User user;

    /**
     * The sight that the edit refers to
     */
    private Sight sight;

    /**
     * Proposed name of the sight
     */
    private String sightName;

    /**
     * Proposed description name of the sight
     */
    private String sightDescription;

    /**
     * Constructor to create Edit.
     *
     * @param user The user who made the edit
     * @param sight The sight that the edit refers to
     * @param sightName Proposed name of the sight
     * @param sightDescription Proposed description name of the sight
     */
    public Edit(User user, Sight sight, String sightName, String sightDescription) {
        this.user = user;
        this.sight = sight;
        this.sightName = sightName;
        this.sightDescription = sightDescription;
    }

    /**
     * Constructor to create Edit.
     *
     * @param id id of current Edit
     * @param user The user who made the edit
     * @param sight The sight that the edit refers to
     * @param sightName Proposed name of the sight
     * @param sightDescription Proposed description name of the sight
     */
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
