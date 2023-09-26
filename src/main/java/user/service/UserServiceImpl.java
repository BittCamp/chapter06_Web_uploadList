package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.bean.UserPaging;
import user.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserPaging userPaging;
	
	@Override
	public String isExistId(String id) {
		//DB
		UserDTO userDTO = userDAO.isExistId(id);
		System.out.println(userDTO);
		
		if(userDTO == null)
			return "non_exist";
		else
			return "exist";
	}

	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);
	}

	@Override
	public Map<String, Object> getUserList(String pg) {	
		//1페이지당 3개씩
		int endNum = Integer.parseInt(pg) * 3;
		int startNum = endNum - 2;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		//DB
		List<UserDTO> list = userDAO.getUserList(map);
		//System.out.println(list);
		
		//List 객체 -> JSON 변환
		/*
		<dependency>
		    <groupId>org.json</groupId>
		    <artifactId>json</artifactId>
		    <version>20230227</version>
		</dependency>
	    */
		
	    /*
		JSONObject json = new JSONObject();
		
		JSONArray array = new JSONArray();
		for(UserDTO userDTO : list) {
			JSONObject temp = new JSONObject();
			temp.put("name", userDTO.getName());
			temp.put("id", userDTO.getId());
			temp.put("pwd", userDTO.getPwd());
			
			array.put(temp);
		}//for
		
		json.put("list", array);
		
		//{"list":[{"name":"둘리","id":"doori","pwd":"111"},{~~},{~~}]}
		System.out.println(json);
		*/
		
		//페이징 처리
		int totalA = userDAO.getTotalA(); //총글수
		
		userPaging.setCurrentPage(Integer.parseInt(pg));
		userPaging.setPageBlock(3);
		userPaging.setPageSize(3);
		userPaging.setTotalA(totalA);
		
		userPaging.makePagingHTML();
				
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("list", list);
		map2.put("userPaging", userPaging);
		
		return map2;
	}

	@Override
	public UserDTO getUser(String id) {
		return userDAO.getUser(id);
	}

	@Override
	public void update(UserDTO userDTO) {
		userDAO.update(userDTO);
	}

	@Override
	public void delete(String id) {
		userDAO.delete(id);
	}

}













