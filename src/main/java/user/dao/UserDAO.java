package user.dao;

import java.util.List;
import java.util.Map;

import user.bean.UserDTO;

public interface UserDAO {

	public UserDTO isExistId(String id);

	public void write(UserDTO userDTO);

	public List<UserDTO> getUserList(Map<String, Integer> map);

	public int getTotalA();

	public UserDTO getUser(String id);

	public void update(UserDTO userDTO);

	public void delete(String id);

}
