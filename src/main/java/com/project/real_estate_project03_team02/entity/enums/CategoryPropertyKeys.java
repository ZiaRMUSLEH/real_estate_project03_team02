package com.project.real_estate_project03_team02.entity.enums;

public enum CategoryPropertyKeys {

    FLOOR("Floor"),
    BEDROOM("Bedroom"),
    BATHROOM("Bathroom"),
    GARAGE("Garage"),
    YEAR_OF_BUILT("Year of Built"),
    SIZE("Size");

    private final String keyName;

    CategoryPropertyKeys(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }

}
