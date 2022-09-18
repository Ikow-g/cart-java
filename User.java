
public class User {
	private String username;
	private String password;
	private String role;

	// constructor
	public User(String username, String password, String role){
	this.username = username;
	this.password = password;
	this.role = role;
	}

	//setter
	//user
	public void setUser(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	//getter
	public String getUser() {
		return username;
	}
	
	public String getPass() {
		return password;
	}
	
	public String getRole() {
		return role;
	}
}
