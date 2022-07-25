package dto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = -1269484380250181242L;
	private Long id;
	private String name;
	private boolean status;
	private DataInputStream dis;
	private DataOutputStream dos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
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
	public UserDTO(Long id, String name, boolean status, DataInputStream dis, DataOutputStream dos) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.dis = dis;
		this.dos = dos;
	}
	public UserDTO(Long id, String name, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}
	public UserDTO() {
		super();
	}
	
	
}
