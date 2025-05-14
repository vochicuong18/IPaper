package entities

class User {
	String name
	String userName
	String password
	String email
	String phoneNumber
	String role
	String department

	private User() {}

	// --- Getter / Setter ---
	String getName() {
		return name
	}
	void setName(String name) {
		this.name = name
	}

	String getUserName() {
		return userName
	}
	void setUserName(String userName) {
		this.userName = userName
	}

	String getPassword() {
		return password
	}
	void setPassword(String password) {
		this.password = password
	}

	String getEmail() {
		return email
	}
	void setEmail(String email) {
		this.email = email
	}

	String getPhoneNumber() {
		return phoneNumber
	}
	void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber
	}

	String getRole() {
		return role
	}
	void setRole(String role) {
		this.role = role
	}

	String getDepartment() {
		return department
	}
	void setDepartment(String department) {
		this.department = department
	}

	// --- Builder pattern ---
	static class Builder {
		private String name
		private String userName
		private String password
		private String email
		private String phoneNumber
		private String role
		private String department

		Builder name(String name) {
			this.name = name
			return this
		}

		Builder userName(String userName) {
			this.userName = userName
			return this
		}

		Builder password(String password) {
			this.password = password
			return this
		}

		Builder email(String email) {
			this.email = email
			return this
		}

		Builder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber
			return this
		}

		Builder role(String role) {
			this.role = role
			return this
		}

		Builder department(String department) {
			this.department = department
			return this
		}

		User build() {
			User user = new User()
			user.name = this.name
			user.userName = this.userName
			user.password = this.password
			user.email = this.email
			user.phoneNumber = this.phoneNumber
			user.role = this.role
			user.department = this.department
			return user
		}
	}

	static Builder builder() {
		return new Builder()
	}
}
