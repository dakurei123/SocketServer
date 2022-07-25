package service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Map;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import dto.RawData;
import dto.UserDTO;
import entity.User;
import repository.UserRepository;

public class Login {
	private DataInputStream dis;
	private DataOutputStream dos;
	Map<Long, UserDTO> userList;
	
	public static boolean checkLogin(RawData rd, DataInputStream dis, DataOutputStream dos, Map<Long, UserDTO> userList) throws IOException, SQLException {
		Gson gson = new Gson();
		boolean loggedUser = false;
		try {
			//Đọc user từ raw data
			User user = gson.fromJson(rd.getData(), User.class);
			//Hash password
			user.setPassword(Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString());
			//Check user
			user = UserRepository.checkUser(user);
			//Thông báo online
			System.out.println("User online: " + user.getId());
			userList.get(user.getId()).setDis(dis);
			userList.get(user.getId()).setDos(dos);
			userList.get(user.getId()).setStatus(true);
			//Gửi lại về client
			dos.writeUTF(gson.toJson(user));
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return loggedUser;
	}

	public DataInputStream getDis() {
		return dis;
	}

	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

	public Map<Long, UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(Map<Long, UserDTO> userList) {
		this.userList = userList;
	}
}
