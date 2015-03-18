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
public class AnswerList {
private int ID;
private String Title;
private Answer[] Answer;
private QuestionList QuestionList;

private User User;


private String Patient;


public AnswerList(int ID, String Title, Answer[] Answer, QuestionList QuestionList, User User, String Patient) { 
this.ID = ID;
this.Title = Title;
this.Answer = Answer;
this.QuestionList = QuestionList;
this.User = User;
this.Patient = Patient;
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
     * @return the patient
     */
    public String getPatient() {
        return Patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(String patient) {
        this.Patient = patient;
    }

    /**
     * @return the Answer
     */
    public Answer[] getAnswer() {
        return Answer;
    }

    /**
     * @param Answer the Answer to set
     */
    public void setAnswer(Answer[] Answer) {
        this.setAnswer(Answer);
    }

    /**
     * @return the QuestionList
     */
    public QuestionList getQuestionList() {
        return QuestionList;
    }

    /**
     * @param QuestionList the QuestionList to set
     */
    public void setQuestionList(QuestionList QuestionList) {
        this.QuestionList = QuestionList;
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
