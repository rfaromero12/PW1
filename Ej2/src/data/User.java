package data;

/**
 * A class to represent an user.
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public class User {

	private String username_;
	private String mail_;
	private String name_;
	private String password_;
	private int admin_;

	/**
	 * Parameterized constructor
	 * @param username User's unique username
	 * @param mail User's unique email
	 * @param name User's name
	 * @param password Account's password
	 * @param admin It indicates if the user is an administrator
	 */
	public User(String username, String mail, String name, String password, int admin) {
		username_ = username;
		mail_ = mail;
		name_ = name;
		password_ = password;
		admin_ = admin;
	}

	/**
	 * Get the user's username
	 * @return User's username
	 */
	public String getUser() {
		return username_;
	}
	
	/**
	 * Get the user's mail
	 * @return User's mail
	 */
	public String getMail() {
		return mail_;
	}
	
	/**
	 * Get the user's name
	 * @return User's name
	 */
	public String getName() {
		return name_;
	}
	
	/**
	 * Get the user's password
	 * @return User's password
	 */
	public String getPassword() {
		return password_;
	}
	
	/**
	 * Get the user permissions
	 * @return 1 if the user is administrator, 0 if it isn't
	 */
	public int admin() {
		return admin_;
	}
	
	/**
	 * Set admin permissions to the user
	 * @return 1
	 */
	public int setAdmin() {
		admin_ = 1;
		return admin_;
	}
	
}
