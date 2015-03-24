/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.objects;

public class AnswerList {
	private int id;
	private String title;
	private Answer[] answer;
	private QuestionList questionList;

	private User user;

	private String patient;

	public AnswerList(int id, String title, Answer[] answer,
			QuestionList questionList, User user, String patient) {
		this.id = id;
		this.title = title;
		this.answer = answer;
		this.questionList = questionList;
		this.user = user;
		this.patient = patient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Answer[] getAnswer() {
		return answer;
	}

	public void setAnswer(Answer[] answer) {
		this.answer = answer;
	}

	public QuestionList getQuestionList() {
		return questionList;
	}

	public void setQuestionList(QuestionList questionList) {
		this.questionList = questionList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

}
