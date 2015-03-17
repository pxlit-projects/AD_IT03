/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;

/**
 *
 * @author Jordy
 */
public class Rapport {
    private int ID;
       
    private QuestionList QuestionList;
    private AnswerList AnswerList;
    
    
    private Date Date;
    private int Time;
    
    
    public Rapport(int ID, QuestionList QuestionList, AnswerList AnswerList, Date Date, int Time) {
    this.ID = ID;
    this.QuestionList = QuestionList;
    this.AnswerList = AnswerList;
    this.Date = Date;
    this.Time = Time;
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
     * @return the date
     */
    public Date getDate() {
        return Date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.Date = date;
    }

    /**
     * @return the Time
     */
    public int getTime() {
        return Time;
    }

    /**
     * @param Time the Time to set
     */
    public void setTime(int Time) {
        this.Time = Time;
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
     * @return the AnswerList
     */
    public AnswerList getAnswerList() {
        return AnswerList;
    }

    /**
     * @param AnswerList the AnswerList to set
     */
    public void setAnswerList(AnswerList AnswerList) {
        this.AnswerList = AnswerList;
    }
    
    
    
}
