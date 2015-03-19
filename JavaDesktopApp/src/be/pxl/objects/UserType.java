/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.objects;

/**
 *
 * @author Jordy
 */
public class UserType {

    private int ID;
    private String ScreenName;
    private String Description;

    public UserType(int ID, String ScreenName, String Description) {
        this.ID = ID;
        this.ScreenName = ScreenName;
        this.Description = Description;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the ScreenName
     */
    public String getScreenName() {
        return ScreenName;
    }

    /**
     * @param ScreenName the ScreenName to set
     */
    public void setScreenName(String ScreenName) {
        this.ScreenName = ScreenName;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

}
