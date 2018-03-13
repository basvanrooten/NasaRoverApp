package vanrooten.bas.nasaroverapp.domain;

import java.io.Serializable;

/**
 * Created by Bas van Rooten on 13/03/2018.
 * AVANS HOGESCHOOL 2125132
 */

public class Picture implements Serializable {


    // Declaring Variables
    private int pictureID;
    private int pictureSol;
    private String pictureURL;
    private String pictureEarthDate;
    private int cameraID;
    private String cameraShortName;
    private String cameraFullName;


    public Picture(int pictureID) {

        // Constructing the Picture object with ID only
        this.pictureID = pictureID;
    }


    // Getters
    public int getPictureID() {
        return pictureID;
    }

    public int getPictureSol() {
        return pictureSol;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public String getPictureEarthDate() {
        return pictureEarthDate;
    }

    public int getCameraID() {
        return cameraID;
    }

    public String getCameraShortName() {
        return cameraShortName;
    }

    public String getCameraFullName() {
        return cameraFullName;
    }


    // Setters
    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public void setPictureSol(int pictureSol) {
        this.pictureSol = pictureSol;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public void setPictureEarthDate(String pictureEarthDate) {
        this.pictureEarthDate = pictureEarthDate;
    }

    public void setCameraID(int cameraID) {
        this.cameraID = cameraID;
    }

    public void setCameraShortName(String cameraShortName) {
        this.cameraShortName = cameraShortName;
    }

    public void setCameraFullName(String cameraFullName) {
        this.cameraFullName = cameraFullName;
    }
}
