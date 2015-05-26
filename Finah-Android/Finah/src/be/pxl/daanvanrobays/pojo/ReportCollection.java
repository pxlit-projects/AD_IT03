package be.pxl.daanvanrobays.pojo;

import java.util.List;

public class ReportCollection {

	private Question question;
	private UserType userType;
	private Answer answer;
	private AnswerList answerList;
	
	public ReportCollection(){
		
	}

	public ReportCollection(Question question, UserType userType,
			Answer answer, AnswerList answerList) {
		super();
		this.question = question;
		this.userType = userType;
		this.answer = answer;
		this.answerList = answerList;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public AnswerList getAnswerList() {
		return answerList;
	}

	public void setAnswerList(AnswerList answerList) {
		this.answerList = answerList;
	}
}