package com.example.eventzone;

public class Event {
    private String tittles ,categories,descriptions,locations,prices,dates,phones;

    public Event(String tittles, String categories, String descriptions, String locations, String dates, String prices ,String phones) {
        this.tittles = tittles;
        this.categories = categories;
        this.descriptions = descriptions;
        this.locations = locations;
        this.prices = prices;
        this.dates = dates;
        this.phones = phones;
    }

    public String getTittles() {
        return tittles;
    }

    public void setTittles(String tittles) {
        this.tittles = tittles;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }
}
