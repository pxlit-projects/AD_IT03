/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.objects;

import java.sql.Date;
import java.sql.Time;

public class AnswerList {
	private int id;
	private int list;
	private Answer[] answer;
	private Question question;
	private int workpoint;
	private String hash;
	private Date date;
	private UserType usertype;
	private int time;
	
	
	
	public AnswerList(int id, int list, Answer[] answer, Question question,
			int workpoint, String hash, Date date, UserType usertype, int time) {
	
		this.id = id;
		this.list = list;
		this.answer = answer;
		this.question = question;
		this.workpoint = workpoint;
		this.hash = hash;
		this.date = date;
		this.usertype = usertype;
		this.time = time;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getList() {
		return list;
	}
	public void setList(int list) {
		this.list = list;
	}
	public Answer[] getAnswer() {
		return answer;
	}
	public void setAnswer(Answer[] answer) {
		this.answer = answer;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public int getWorkpoint() {
		return workpoint;
	}
	public void setWorkpoint(int workpoint) {
		this.workpoint = workpoint;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public UserType getUsertype() {
		return usertype;
	}
	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	

	

	
}
