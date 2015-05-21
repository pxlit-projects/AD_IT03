package be.pxl.objects;




public class Hashes {

	private int id;
	private String hash;
	private String date;
	private int user;
	private int status;
	
	
	
	public Hashes(int id, String hash, String date, int user, int status) {
		this.id = id;
		this.hash = hash;
		this.date = date;
		this.user = user;
		this.status = status;
	}
	
	public Hashes(String hash) {
		this.hash = hash;
	}
	
	public Hashes() {
		
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
