package user.dao;

import java.util.List;

import user.bean.UserImageDTO;

public interface UserDAOUpload {

	public void upload(UserImageDTO userImageDTO, List<String> fileNameList);

	public List<UserImageDTO> getUpload_list_Ajax();

}
