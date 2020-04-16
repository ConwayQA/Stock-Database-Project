package persistance.domain;

public class Customer {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String postcode;
	
	public Customer() {
		super();
	}

	public Customer(String firstName, String lastName, String address, String email, String postcode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.postcode = postcode;
	}
	
	public Customer(Long id, String firstName, String lastName, String address, String email, String postcode) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.postcode = postcode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + " First Name: " + firstName + " Last Name: " + lastName +
				" Address: " + address + " Email: " + email + " Postcode: " + postcode;
	}
	
	//Method from christophperrins to override hashcode()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
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
		Customer compareCustomer = (Customer) obj;
		if (firstName == null) {
			if(compareCustomer.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(compareCustomer.firstName)) {
			return false;
		}
		if (lastName == null) {
			if(compareCustomer.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(compareCustomer.lastName)) {
			return false;
		}
		if (id == null) {
			if(compareCustomer.id != null) {
				return false;
			}
		} else if (!id.equals(compareCustomer.id)) {
			return false;
		}
		if (address == null) {
			if(compareCustomer.address != null) {
				return false;
			}
		} else if (!address.equals(compareCustomer.address)) {
			return false;
		}
		if (email == null) {
			if(compareCustomer.email != null) {
				return false;
			}
		} else if (!email.equals(compareCustomer.email)) {
			return false;
		}
		if (postcode == null) {
			if(compareCustomer.postcode != null) {
				return false;
			}
		} else if (!postcode.equals(compareCustomer.postcode)) {
			return false;
		}
		return true;
		
	}
	

}
