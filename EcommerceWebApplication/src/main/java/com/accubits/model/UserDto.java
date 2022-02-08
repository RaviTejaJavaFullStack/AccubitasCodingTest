package com.accubits.model;

import java.util.List;

public class UserDto {

    private String username;
    private String password;
    private List<String> userRole;
    
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

	public List<String> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<String> userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserDto [username=" + username + ", password=" + password + ", userRole=" + userRole + "]";
	}

}
