package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import user.bean.UserImageDTO;

@Repository
@Transactional
public class UserDAOUploadMybatis implements UserDAOUpload {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void upload(UserImageDTO userImageDTO, List<String> fileNameList) {
		for(String fileName : fileNameList) {
			userImageDTO.setImage1(fileName);
			sqlSession.insert("userUploadSQL.upload", userImageDTO);
			
		}//for
	}

	@Override
	public List<UserImageDTO> getUpload_list_Ajax() {
		return sqlSession.selectList("userUploadSQL.getUpload_list_Ajax");
	}

}









