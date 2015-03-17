/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.classes;


/**
 *
 * @author Jordy
 */
public class Question {
    private int ID;
    private String Title;
    private String Description;
    
    private Theme Theme;
    
    
    private boolean Choice;
    
    public Question(int ID, String Title, String Description, Theme Theme, boolean Choice) {
    this.ID = ID;
    this.Title = Title;
    this.Description = Description;
    this.Theme = Theme;
    this.Choice = Choice;
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
     * @return the Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String Title) {
        this.Title = Title;
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

    /**
     * @return the Choice
     */
    public boolean isChoice() {
        return Choice;
    }

    /**
     * @param Choice the Choice to set
     */
    public void setChoice(boolean Choice) {
        this.Choice = Choice;
    }

    /**
     * @return the Theme
     */
    public Theme getTheme() {
        return Theme;
    }

    /**
     * @param Theme the Theme to set
     */
    public void setTheme(Theme Theme) {
        this.Theme = Theme;
    }
    
}
