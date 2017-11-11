package model;

public class User {

	private String name;
	private String family;
	private String username;
	private String password;

	public User() {

	}

	public User(String username) {
		setUsername(username);
	}

	public User(String username, String password) {
		setUsername(username);
		setPassword(password);
	}

	public User(String name, String family, String username, String password) {
		setName(name);
		setFamily(family);
		setUsername(username);
		setPassword(password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}