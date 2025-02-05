package org.dreamteam.onlineshop.model.enums;

public enum Category {
    MYSTERY("Mystery"),
    FANTASY("Fantasy"),
    SCIENCE_FICTION("Science Fiction"),
    BIOGRAPHY("Biography"),
    HISTORY("History"),
    CHILDREN("Children"),
    ROMANCE("Romance"),
    HORROR("Horror"),
    POETRY("Poetry"),
    GRAPHIC_NOVEL("Graphic Novel"),
    RELIGION("Religion"),
    BUSINESS("Business"),
    SCIENCE("Science"),
    COOKING("Cooking"),
    ART("Art"),
    TRAVEL("Travel"),
    EDUCATION("Education");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}
