package com.example.eventzone;

public class Category
{
    private String TITTLE,CATEGORY,DESCRIPTION,LOCATION,DATE,PRICE,PHONE,imageUri;
    public Category()
    {

    }

    public Category(String TITTLE, String CATEGORY, String DESCRIPTION, String LOCATION, String DATE, String PRICE, String PHONE, String imageUri) {
        this.TITTLE = TITTLE;
        this.CATEGORY = CATEGORY;
        this.DESCRIPTION = DESCRIPTION;
        this.LOCATION = LOCATION;
        this.DATE = DATE;
        this.PRICE = PRICE;
        this.PHONE = PHONE;
        this.imageUri=imageUri;
    }

    public String getTITTLE() {
        return TITTLE;
    }

    public void setTITTLE(String TITTLE) {
        this.TITTLE = TITTLE;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getPRICE() {
        return PRICE;
    }


    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
