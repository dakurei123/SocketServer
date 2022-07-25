package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.UserDTO;
import dto.UserSocket;
import repository.UserRepository;

public class ChatAppMain {
	public static void main(String[] args) throws SQLException {
		// Create socket, port and listening
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(9999);
			Map<Long, UserDTO> userDTOList = new HashMap<>();
			Map<Long, UserSocket> userSocketList = new HashMap<>();
			UserRepository.getListUser(userDTOList, userSocketList);
			List<Long> userIdOnlineList = new ArrayList<>();

			while (true) {
				// Server accept
				Socket socket = serverSocket.accept();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				Thread thread = new HandleRequest(socket, dis, dos,userDTOList, userSocketList, userIdOnlineList);
				thread.start();
			}
		} catch (IOException e1) {
			System.out.println(e1);
		}

	}
}
