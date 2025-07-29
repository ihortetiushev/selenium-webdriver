package com.epam.seleniumwebdriver.utils;

public enum City {
    Kyiv("Київ"),
    Kharkiv("Харків"),
    Odesa("Одеса");

    private final String cityName;

    City(String cityName) {
        this.cityName = cityName;
    }

    public static City findByCityName(String cityName) {
        City foundCity = null;
        for (City city : City.values()) {
            if (city.getCityName().equals(cityName)) {
                foundCity = city;
                break;
            }
        }
        return foundCity;
    }

    public String getCityName() {
        return cityName;
    }
}
