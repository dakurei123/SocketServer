package service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;

import dto.LoginOk;
import dto.RawData;
import dto.UserDTO;
import dto.UserSocket;
import entity.User;
import repository.UserRepository;

public class LoginService {
	private User user;

	public User checkLogin(RawData rd) throws IOException, SQLException {
		Gson gson = new Gson();
		// Đọc user từ raw data
		user = gson.fromJson(rd.getData(), User.class);
		// Hash password
		user.setPassword(Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString());
		// Check user
		return UserRepository.checkUser(user);
	}

	public void loginSuccess(Long clientId, Map<Long, UserDTO> userDTOList, Map<Long, UserSocket> userSocketList,
			List<Long> userIdOnlineList, DataOutputStream dos, DataInputStream dis) throws IOException {
		Gson gson = new Gson();
		clientId = user.getId();
		userDTOList.get(clientId).setStatus(true);
		userSocketList.get(clientId).setDis(dis);
		userSocketList.get(clientId).setDos(dos);
		userIdOnlineList.add(clientId);
		LoginOk loginOk = new LoginOk(clientId, user.getName(), userDTOList);
		String data = gson.toJson(loginOk);
		RawData rd = new RawData("LOGIN_OK", clientId, new Date().getTime(), data);
		String okString = gson.toJson(rd);
		System.out.println(okString);
		dos.writeUTF(okString);
//		dos.writeUTF(gson.toJson(rd));
	}
}
