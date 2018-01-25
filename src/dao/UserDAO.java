package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.User;

public class UserDAO extends DAOFactory implements Queries {

	Connection con;
	Statement stat;
	PreparedStatement state;
	private ResultSet resSet;
	public Boolean check = false;

	public UserDAO() {
		try {
			con = super.getDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addUser(User entity) {
		try {

			stat = con.createStatement();
			resSet = stat.executeQuery("Select nickname, mail from users where nickname = '" + entity.getNickname()
					+ "' OR mail = '" + entity.getEmail() + "';");

			if (resSet.next()) {
				check = true;
				System.out.println("not Unique");
				return;
			}
			stat.close();
			resSet.close();

			state = con.prepareStatement(addUser);

			state.setString(1, entity.getNickname());
			state.setString(2, entity.getPassword());
			state.setString(3, entity.getEmail());
			state.setDate(4, entity.getRegDate());

			state.executeUpdate();

			con.commit();

			state.close();
			System.out.println(entity);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User getUserById(int id) {


		try {
			state = con.prepareStatement(userById);
			state.setInt(1, id);
			resSet = state.executeQuery();
						
			User user = new User();
			user.setUserId(id);
			user.setNickname(resSet.getString(2));
			user.setPassword(resSet.getString(3));
			user.setRegDate(resSet.getDate(4));
			user.setEmail(resSet.getString(5));
			user.setRole(resSet.getByte(6));
			state.close();
			return user;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public User confirmAuthor(String login, String password) {

		System.out.println("Confirmation of: " + login);

		if (login == null || password == null)
			return null;

		try {
			stat = con.createStatement();
			resSet = stat.executeQuery(
					"Select id, role, mail from users where nickname = '" + login 
					+ "' AND password = '" + password + "';");

			if (resSet.next()) {
				int id = resSet.getInt(1);
				Byte role = resSet.getByte(2);
				String mail = resSet.getString(3);
				System.out.println("Confirmed new user " + resSet.getInt(1));
				stat.close();
				
				User user = new User();
				user.setNickname(login);
				user.setUserId(id);
				user.setRole(role);
				user.setEmail(mail);
				user.setPassword(password);
				
				return user;
			}

		} catch (SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Not passed: " + login);
		return null;

	}

	@Override
	protected void finalize() throws Throwable {
		con.close();
		// TODO Auto-generated method stub
		super.finalize();
	}

}
