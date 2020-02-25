package com.user.bean;

public class User {
	
	//instance variables
	
		private Integer id;
		private String name;
		private String email;
		private String country;
		
		//Default constructor
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}

		//parameterized constructor
		public User(Integer id, String name, String email, String country) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.country = country;
		}

		//parameterized constructor
		public User(String name, String email, String country) {
			super();
			this.name = name;
			this.email = email;
			this.country = country;
		}

		//getters and setters
		public Integer getId() {
			return id;
		}

		
		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}
		
		//toString method
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", email=" + email
					+ ", country=" + country + "]";
		}

		

	}



