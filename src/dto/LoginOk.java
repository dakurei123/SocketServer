package dto;

import java.io.Serializable;
import java.util.Map;

public class LoginOk implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Map<Long, UserDTO> userDTOList;
	
	public LoginOk(Long id, String name, Map<Long, UserDTO> userDTOList) {
		this.id = id;
		this.name = name;
		this.userDTOList = userDTOList;
	}
	
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
	public Map<Long, UserDTO> getUserDTOList() {
		return userDTOList;
	}
	public void setUserDTOList(Map<Long, UserDTO> userDTOList) {
		this.userDTOList = userDTOList;
	}
	
}
