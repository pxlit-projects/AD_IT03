/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.daanvanrobays.pojo;

public class Theme {

	private int id;
	private String title;
	private String description;

	public Theme(){};
	
	public Theme(int id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
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

	@Override
	public String toString() {
		return "Theme [id=" + id + ", title=" + title + ", description="
				+ description + "]";
	}

	
	
}
