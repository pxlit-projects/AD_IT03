/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.daanvanrobays.pojo;

import java.util.Date;

public class Rapport {
    private int id;
       
    //property QuestionList!!!!
    //property AnswerList!!!!!
    
    private Date date;
    private int time;
    
    
    public Rapport() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}

 
    
}
