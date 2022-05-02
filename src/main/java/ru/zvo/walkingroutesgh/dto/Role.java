package ru.zvo.walkingroutesgh.dto;

/**
 * Contains possible roles of user
 *
 * @author Vladimir Zaitsev
 */
public enum Role {

    /**
     * Historian role
     */
    HISTORIAN("Краеведы"),

    /**
     * Moderator role
     */
    MODERATOR("Модераторы"),

    /**
     * Administrator role
     */
    ADMIN("Администраторы");

    /**
     * Role constructor
     * @param description
     * Role description
     */
    Role(String description) {
        this.description = description;
    }

    /**
     * Role description
     */
    private String description;

    /**
     * Returns description of current role
     * @return description of current role
     */
    public String getDescription() {
        return description;
    }
}
