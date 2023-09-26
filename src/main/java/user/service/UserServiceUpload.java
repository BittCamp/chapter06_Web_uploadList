package user.service;

import java.util.List;

import user.bean.UserImageDTO;

public interface UserServiceUpload {

	public void upload(UserImageDTO userImageDTO, List<String> fileNameList);

	public List<UserImageDTO> getUpload_list_Ajax();

}
