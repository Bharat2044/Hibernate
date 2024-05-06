package com.hibernate.mapping.unidirection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_details")
public class CustomerDetails {
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "email", length = 128, nullable = false, unique = true)
	private String email;

	@Column(name = "phone", length = 10, nullable = false)
	private long phone;

	@Column(name = "address")
	private String address;

	public CustomerDetails() {

	}

	public CustomerDetails(int id, String email, long phone, String address) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "email=" + email + ", phone=" + phone + ", address=" + address;
	}
}
