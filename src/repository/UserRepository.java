package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import dto.UserDTO;
import dto.UserSocket;
import entity.User;

public class UserRepository {
	//id, username, password, name
	public static void getListUser(Map<Long, UserDTO> userDTOList,
			Map<Long, UserSocket> userSocketList) throws SQLException {
//		String sha256hex = Hashing.sha256().hashString("hello", StandardCharsets.UTF_8).toString();
		Connection con = DataSource.getConnection();
		String sql = "select * from user";
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			userDTOList.put(rs.getLong("id"), new UserDTO(rs.getLong("id"), rs.getString("name"), false));
			userSocketList.put(rs.getLong("id"), new UserSocket(rs.getLong("id")));
		}
	}
			
	public static User checkUser(User user) throws SQLException {
		Connection con = DataSource.getConnection();
		String sql = "select * from user where username = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, user.getUsername());
		ResultSet rs = statement.executeQuery();
		if (rs.next() == false) {
			return null;
		}
		else {
			if (rs.getString("password").equals(user.getPassword())) {
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPassword(null);
				return user;
			}
		}
		return null;
	}	
			
		

}