package by.epam.casino.domain;

/**
 * User of application.
 *
 */
public class User extends Entity {
	/**
	 * name
	 */
	private String name;
	/**
	 * surname
	 */
	private String surname;
	/**
	 * age
	 */
	private int age;
	/**
	 * gender
	 */
	private GenderType gender;
	/**
	 * login
	 */
	private String login;
	/**
	 * password
	 */
	private String password;
	/**
	 * role
	 */
	private RoleType role;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the gender
	 */
	public GenderType getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(GenderType gender) {
		this.gender = gender;
	}
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the role
	 */
	public RoleType getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(RoleType role) {
		this.role = role;
	}
}

