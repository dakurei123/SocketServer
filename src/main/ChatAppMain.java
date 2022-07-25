package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Map;

import com.google.gson.Gson;

import dto.RawData;
import dto.UserDTO;
import repository.UserRepository;
import service.Login;

public class ChatAppMain {
	public static void main(String[] args) throws SQLException {
		// Create socket, port and listening
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(9999);
			Map<Long, UserDTO> userList = UserRepository.getListUser();
			Gson gson = new Gson();

			while (true) {
				// Server accept
				Socket socket = serverSocket.accept();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				boolean logged = false;

				while (!logged) {
					// Đọc raw data
					String string = dis.readUTF();
					System.out.println(string);
					RawData rd = gson.fromJson(string, RawData.class);
					if (rd.getType().equals("LOGIN"))
						logged = Login.checkLogin(rd, dis, dos, userList);
				}
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
