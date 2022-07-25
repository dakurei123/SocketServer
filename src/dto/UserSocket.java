package dto;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class UserSocket {
	private Long id;
	private DataInputStream dis;
	private DataOutputStream dos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public UserSocket(Long id, DataInputStream dis, DataOutputStream dos) {
		super();
		this.id = id;
		this.dis = dis;
		this.dos = dos;
	}
	public UserSocket(Long id) {
		super();
		this.id = id;
	}
	
}
