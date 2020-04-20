package persistance.domain;

public class User {
	
	private Long userID;
	private String firstName;
	private String lastName;
	private String username;
	private boolean loggedIn = false;
	private String password;
	
	public User(String firstName, String lastName, String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}
	
	public User(Long userID, String firstName, String lastName, String username) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}
	
	public User(String firstName, String lastName, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	public User(Long userID, String firstName, String lastName, String username, String password) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User ID: " + userID + " First Name: " + firstName + " Last Name: " + lastName +
				" Username: " + username;
	}
	
	//Method from christophperrins to override hashcode()
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((userID == null) ? 0 : userID.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
			return result;
		}
			
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User compareUser = (User) obj;
			if (firstName == null) {
				if(compareUser.firstName != null) {
					return false;
				}
			} else if (!firstName.equals(compareUser.firstName)) {
				return false;
			}
			if (lastName == null) {
				if(compareUser.lastName != null) {
					return false;
				}
			} else if (!lastName.equals(compareUser.lastName)) {
				return false;
			}
			if (userID == null) {
				if(compareUser.userID != null) {
					return false;
				}
			} else if (!userID.equals(compareUser.userID)) {
				return false;
			}
			if (username == null) {
				if(compareUser.username != null) {
					return false;
				}
			} else if (!username.equals(compareUser.username)) {
				return false;
			}
			return true;
			
		}

}
