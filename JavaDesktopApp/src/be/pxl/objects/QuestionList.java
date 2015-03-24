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
    private int id;
    private Question[] question;
    private User user;
    
    
    private String description;
    
    public QuestionList(int id, Question [] question, User user, String description) {
    this.id = id;
    this.question = question;
    this.user = user;
    this.description = description;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Question[] getQuestion() {
		return question;
	}

	public void setQuestion(Question[] question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    
}
