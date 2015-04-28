/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.objects;

public class Answer {
 
	private int id;
    private String title;
    private int number;
    private int choice;
    
    
    
    public Answer (int id, String title, int number, int choice){
    this.id = id;
    this.title = title;
    this.number = number;
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



	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	}



	public int getChoice() {
		return choice;
	}



	public void setChoice(int choice) {
		this.choice = choice;
	}
	
	
	   @Override
		public String toString() {
			return "Answer [id=" + id + ", title=" + title + ", number=" + number
					+ ", choice=" + choice + "]";
		}

}
