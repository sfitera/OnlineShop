package org.dreamteam.onlineshop.model.enums;

public enum Category {
    ART("Art"),
    BIOGRAPHY("Biography"),
    CHILDREN("Children"),
    COOKING("Cooking"),
    DETECTIVE("Detective"),
    EDUCATION("Education"),
    FANTASY("Fantasy"),
    HISTORY("History"),
    HORROR("Horror"),
    MYSTERY("Mystery"),
    POETRY("Poetry"),
    ROMANCE("Romance"),
    SCIENCE("Science"),
    SCIFI("Scifi"),
    TRAVEL("Travel");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}
