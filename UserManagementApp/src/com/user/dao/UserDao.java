package com.user.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mvc.helper.ConnectionFactory;
import com.user.bean.User;

public class UserDao {
	// Implements singleton design pattern
		private static UserDao instance = null;

		// Default constructor
		public UserDao() {
			super();
			// TODO Auto-generated constructor stub
		}

		// getInstance method
		public static UserDao getInstance() {
			if (instance == null) {
				instance = new UserDao();
			}
			return instance;
		}

		private static final String INSERT_USERS_SQL = "INSERT INTO tbluser(name,email,country) VALUES(?,?,?)";
		private static final String SELECT_USER_BY_ID = "SELECT id,name,email,country from tbluser where id=?";
		private static final String SELECT_ALL_USERS = "SELECT * from tbluser";
		private static final String DELETE_USERS_SQL = "DELETE from tbluser where id=?";
		private static final String UPDATE_USERS_SQL = "update tbluser set name=?,email=?,country=? where id=?";

		// creating objects
		private Connection connection = null;
		private PreparedStatement statement = null;
		private ResultSet resultSet = null;

	
		public int insertUser(User user) throws SQLException {

			int result = -1;
			System.out.println(INSERT_USERS_SQL);
			try {
				connection = ConnectionFactory.getConnection();
				statement = connection.prepareStatement(INSERT_USERS_SQL);
				statement.setString(1, user.getName());
				statement.setString(2, user.getEmail());
				statement.setString(3, user.getCountry());
				System.out.println(statement);

				result = statement.executeUpdate();

				statement.close();
				connection.close();
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

	
		public User selectUser(int id) {
			User user = null;
			try {
				connection = ConnectionFactory.getConnection();
				statement = connection.prepareStatement(SELECT_USER_BY_ID);
				statement.setInt(1, id);
				System.out.println(statement);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Integer idNo = resultSet.getInt("ID");
					String name = resultSet.getString("NAME");
					String email = resultSet.getString("EMAIL");
					String country = resultSet.getString("COUNTRY");
					user = new User(idNo, name, email, country);
				}
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}
			return user;

		}

	
		public List<User> selectAllUsers() {

			List<User> users = new ArrayList<User>();
			try {
				connection = ConnectionFactory.getConnection();
				statement = connection.prepareStatement(SELECT_ALL_USERS);
				System.out.println(statement);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Integer id = resultSet.getInt("id");
					String name = resultSet.getString("NAME");
					String email = resultSet.getString("EMAIL");
					String country = resultSet.getString("COUNTRY");
					users.add(new User(id, name, email, country));

				}
			} catch (SQLException e) {
				System.out.println(e);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return users;
		}

		
		public boolean deleteUser(int id) throws SQLException{
		
			boolean rowDeleted=false;
			try{
				connection = ConnectionFactory.getConnection();
				statement = connection.prepareStatement(DELETE_USERS_SQL);
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() > 0;

			}
			catch (SQLException e) {
				System.out.println(e);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return rowDeleted;
		}
		
	
		public boolean updateUser(User user) throws SQLException{
			
			boolean rowUpdated=false;
			
			try
			{
				connection=ConnectionFactory.getConnection();
				statement=connection.prepareStatement(UPDATE_USERS_SQL);
				statement.setString(1, user.getName());
				statement.setString(2, user.getEmail());
				statement.setString(3, user.getCountry());
				statement.setInt(4, user.getId());

				rowUpdated = statement.executeUpdate() > 0;
			}catch(SQLException e){
				System.out.println(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rowUpdated;
		}

		
	}



	