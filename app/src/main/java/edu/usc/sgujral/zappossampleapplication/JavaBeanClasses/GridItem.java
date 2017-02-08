package edu.usc.sgujral.zappossampleapplication.JavaBeanClasses;

/**
 * Created by sunakshigujral on 2/5/17.
 */
public class GridItem {

    private int imageID;
    private String name;

    public GridItem(String name, int imageId){
        this.imageID = imageId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
