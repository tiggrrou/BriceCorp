package com.wha.springmvc.model;

public class User {

	//#region Variables
	/**
	 * ID de l'utilisateur
	 */
	private long id;
	
	/**
	 * Nom de l'utilisateur
	 */
	private String name;
	
	/**
	 * Prénom de l'utilisateur
	 */
	private String firstName;
	
	/**
	 * Adresse de l'utilisateur
	 */
	private String address;
	
	/**
	 * mail de l'utilisateur
	 */
	private String email;
	
	/**
	 * Pseudonyme de l'utilisateur
	 */
	private String login;
	
	/**
	 * Mot de passe dde l'utilisateur
	 */
	private String password;
	
	/**
	 * Téléphone de l'utilisateur
	 */
	private int phoneNumber;
	
	
	//#endregion
	
	//#region Constructeurs
	public User(){
		id=0;
	}
	
	public User(long id, String username, String address, String email, String login, String mdp){
		this.id = id;
		this.name = username;
		this.address = address;
		this.email = email;
		this.login = login;
		this.password = mdp;
	}
	//#endregion
	
	//#region Accesseurs
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return name;
	}

	public void setUsername(String username) {
		this.name = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	//#endregion
	
	//#region Méthodes redéfinies
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + name + ", adresse=" + address
				+ ", email=" + email + "]";
	}
	//#endregion

	//#region Méthodes
	
	//#endregion
	
}
