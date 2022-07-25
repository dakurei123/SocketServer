package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import dto.RawData;
import dto.UserDTO;
import dto.UserSocket;
import entity.User;
import error.ErrorHandle;
import error.Errors;
import service.LoginService;

public class HandleRequest extends Thread{
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Map<Long, UserDTO> userDTOList;
	private Map<Long, UserSocket> userSocketList;
	private List<Long> userIdOnlineList;
	private Long clientId;
	
	public HandleRequest(Socket socket, DataInputStream dis, DataOutputStream dos, Map<Long, UserDTO> userDTOList,
			Map<Long, UserSocket> userSocketList, List<Long> userIdOnlineList) {
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
		this.userDTOList = userDTOList;
		this.userIdOnlineList = userIdOnlineList;
		this.userSocketList = userSocketList;
	}

	@Override
	public void run() {
		try {
			Gson gson = new Gson();
			LoginService loginService = new LoginService();
			while(true) {
				// Đọc raw data
				RawData rd = gson.fromJson(dis.readUTF(), RawData.class);
				if (rd.getType().equals("LOGIN")) {
					User user = loginService.checkLogin(rd);
					if (user == null) {
						Errors error = new Errors("LOGIN_ERR","Sai tên tài khoản hoặc mật khẩu");
						ErrorHandle.sendToUser(error, dos, clientId);
					} else {
						loginService.loginSuccess(clientId, userDTOList,
								userSocketList, userIdOnlineList, dos, dis);
					}
				} else if (rd.getType().equals("SIGNUP")) {
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Out: " + clientId);
			dis.close();
			dos.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}


























