package com.example.eventzone;

public class Upload {
    String imageName;
    String imgUri;
    public Upload()
    {

    }

    public Upload(String imageName, String imgUri) {
        this.imageName = imageName;
        this.imgUri = imgUri;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }
}
