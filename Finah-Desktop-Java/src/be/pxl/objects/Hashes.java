package be.pxl.objects;

import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

public class Hashes {

	private int id;
	private String hash;
	private Timestamp date;
	private int user;
	private int status;
	
	
	
	public Hashes(int id, String hash, Timestamp date, int user, int status) {
		this.id = id;
		this.hash = hash;
		this.date = date;
		this.user = user;
		this.status = status;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
