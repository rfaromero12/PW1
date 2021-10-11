package ej2;

public class User {

	private String username_;
	private String mail_;
	private String name_;
	private String password_;
	private int admin_;

	public User(String username, String mail, String name, String password, int admin) {
		username_ = username;
		mail_ = mail;
		name_ = name;
		password_ = password;
		admin_ = admin;
	}

	public String getUser() {
		return username_;
	}
	
	public String getMail() {
		return mail_;
	}
	
	public String getName() {
		return name_;
	}
	
	public String getPassword() {
		return password_;
	}
	
	public int admin() {
		return admin_;
	}
	
}
