package ru.zvo.walkingroutesgh.dto;

public enum Role {

    HISTORIAN("Краеведы"),
    MODERATOR("Модераторы"),
    ADMIN("Администраторы");

    Role(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
