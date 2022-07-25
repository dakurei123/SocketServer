package error;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

import com.google.gson.Gson;

import dto.RawData;

public class ErrorHandle {
	public static void sendToUser(Errors error, DataOutputStream dos, Long clientId) throws IOException {
		Gson gson = new Gson();
		String data = gson.toJson(error);
		System.out.println(data);
		RawData returnClientData = new RawData();
		returnClientData.setClientId(clientId);
		returnClientData.setDate(new Date().getTime());
		returnClientData.setType("ERROR");
		returnClientData.setData(data);
		System.out.println(gson.toJson(returnClientData));
		dos.writeUTF(gson.toJson(returnClientData));
	}
}
