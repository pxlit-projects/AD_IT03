package be.pxl.daanvanrobays.pojo;

public class QuestionAndTheme {

	private Question question;
	private Theme theme;

	public QuestionAndTheme(Question question, Theme theme) {
		super();
		this.question = question;
		this.theme = theme;

	}
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
}
