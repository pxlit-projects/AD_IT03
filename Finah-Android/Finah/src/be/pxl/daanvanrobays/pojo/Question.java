/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.daanvanrobays.pojo;

import com.google.gson.annotations.SerializedName;

public class Question {
	private int id;
	private String title;
	private String description;
	@SerializedName("theme") private int themeId;
	private int choice;

	public Question() {
		
	}
	
	public Question(String title, String description, int themeId) {
		this.title = title;
		this.description = description;
		this.themeId = themeId;
	}
	
	public Question(String title, int themeId) {
		this.title = title;
		this.themeId = themeId;
	}
	
	public Question(int id, String title, String description, int themeId,
			int choice) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.themeId = themeId;
		this.choice = choice;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getThemeId() {
		return themeId;
	}

	public void setTheme(int themeId) {
		this.themeId = themeId;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", description="
				+ description + ", themeId=" + themeId + ", choice=" + choice
				+ "]";
	}

	
	
}
