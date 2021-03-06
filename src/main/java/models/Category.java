package models;

public enum Category {

    HOUSEHOLD("Household"),
    ELECTRONIC("Electronic"),
    SPORTS("Sports"),
    CLOTHING("Clothing"),
    OTHER("Other"),
    PETS("Pets");

    private String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
