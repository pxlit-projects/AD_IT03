/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.objects;

import com.google.gson.annotations.SerializedName;

public class UserType {

    private int id;
    @SerializedName("screenname") private String typeName;
    private String description;

    public UserType(int id, String typeName, String description) {
        this.id = id;
        this.typeName = typeName;
        this.description = description;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "UserType [id=" + id + ", typeName=" + typeName
				+ ", description=" + description + "]";
	}

}
