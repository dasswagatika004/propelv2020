package com.user.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.bean.User;
import com.user.dao.UserDao;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao;

	public void init() {
		userDao = userDao.getInstance();
	}

	public UserServlet() {
		super();

	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getServletPath();
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}

	// calling Display method
	public void listUser(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException,
			ServletException {

		List<User> listUser = userDao.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	// Add user
	public void showNewForm(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException,
			ServletException {

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);

	}

	// calling Edit and Update method
	private void showEditForm(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(request.getParameter("id"));
		User existingUser = userDao.selectUser(id);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	// method calling for insert user

	private void insertUser(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User newUser = new User(name, email, country);
		int result = userDao.insertUser(newUser);
		if (result == 1) {
			response.sendRedirect("list");
		} else {
			System.out.println("Not inserted");
		}
	}

	// method calling for update user

	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User userData = new User(id, name, email, country);

		userDao.updateUser(userData);
		response.sendRedirect("list");
	}

	// method calling for delete user

	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		userDao.deleteUser(id);
		response.sendRedirect("list");
	}

}
