/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.objects;


public class AnswerList {
	private int id;
	private int time;
	private String date;
	private int list;
	private int answer;
	private int question;
	private int workpoint;
	private String hash;
	private int usertype;
	
	
	
	public AnswerList(int id, int time, String date, int list, int answer, int question,
			int workpoint, String hash, int usertype) {
	
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
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public int getQuestion() {
		return question;
	}
	public void setQuestion(int question) {
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	

	

	
}
