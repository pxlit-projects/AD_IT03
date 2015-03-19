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
public class QuestionList {
    private int ID;
    private Question[] Question;
    private User User;
    
    
    private String Description;
    
    public QuestionList(int ID, Question [] Question, User User, String Description) {
    this.ID = ID;
    this.Question = Question;
    this.User = User;
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
     * @return the Question
     */
    public Question[] getQuestion() {
        return Question;
    }

    /**
     * @param Question the Question to set
     */
    public void setQuestion(Question[] Question) {
        this.Question = Question;
    }

    /**
     * @return the User
     */
    public User getUser() {
        return User;
    }

    /**
     * @param User the User to set
     */
    public void setUser(User User) {
        this.User = User;
    }
    
    
}
