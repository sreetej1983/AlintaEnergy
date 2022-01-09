package com.alintacodetest.sreetej.entity;

 
	
	import java.util.Date;

import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

	@Entity
	@Table(name="customers")
	public class Customer {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private int id;
		
		@Column(name="first_name")
		private String firstName;
		
		@Column(name="last_name")
		private String lastName;
		
		@Column(name="dob")
		private Date dob;
		
		public Customer() {
			
		}

		public Customer(String firstName, String lastName, Date dob) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.dob = dob;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
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

		public Date getDob() {
			return dob;
		}

		public void setDob(Date dob) {
			this.dob = dob;
		}

		@Override
		public String toString() {
			return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + "]";
		}
		
	}



