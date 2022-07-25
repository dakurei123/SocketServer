package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dto.UserDTO;
import entity.User;

public class UserRepository {
	//id, username, password, name
	public static Map<Long, UserDTO> getListUser() throws SQLException {
		Map<Long,UserDTO> users = new HashMap<>();
//		String sha256hex = Hashing.sha256().hashString("hello", StandardCharsets.UTF_8).toString();
		Connection con = DataSource.getConnection();
		String sql = "select * from user";
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			users.put(rs.getLong("id"), new UserDTO(rs.getLong("id"), rs.getString("name"), false));
		}
		return users;
	}
			
	public static User checkUser(User user) throws SQLException {
		Connection con = DataSource.getConnection();
		String sql = "select * from user where username = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, user.getUsername());
		ResultSet rs = statement.executeQuery();
		rs.next();
		if (rs.getString("password").equals(user.getPassword())) {
			user.setId(rs.getLong("id"));
			user.setName(rs.getString("name"));
			user.setPassword(null);
			return user;
		}
		return null;
	}	
			
		

}