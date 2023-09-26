package user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import user.bean.UserImageDTO;
import user.service.UserServiceUpload;

@Controller
@RequestMapping(value="user")
public class UserControllerUpload {
	@Autowired
	private UserServiceUpload userServiceUpload;
	
	@GetMapping(value="uploadForm")
	public String uploadForm() {
		return "/user/uploadForm";
	}
	
	//----------- name="img" 1개 일때 -----------------------
	/*
	@PostMapping(value="upload", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String upload(@ModelAttribute UserImageDTO userImageDTO,
			             @RequestParam MultipartFile img,
			             HttpSession session) {
		
		//가상폴더
		String filePath_lier = "D:/Spring/workspace/chapter06_Web/src/main/webapp/WEB-INF/storage";
		
		//실제폴더
		String filePath = session.getServletContext().getRealPath("/WEB-INF/storage");
		System.out.println("실제폴더 = " + filePath);
		
		String fileName = img.getOriginalFilename();
		
		//파일 생성
		File file = new File(filePath, fileName);
		//File file = new File(filePath_lier, fileName);
		System.out.println(file);
		
		try {
			//파일 이동
			img.transferTo(file);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		userImageDTO.setImage1(fileName);
		
		return "<img src='/chapter06_Web/storage/" + fileName + "' width='300' height='300' />";
	}
	*/
	
	//----------- name="img" 2개 이상 일때 -----------------------
	/*
	@PostMapping(value="upload", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String upload(@ModelAttribute UserImageDTO userImageDTO,
			             @RequestParam MultipartFile[] img,
			             HttpSession session) {
		
		//실제폴더
		String filePath = session.getServletContext().getRealPath("/WEB-INF/storage");
		System.out.println("실제폴더 = " + filePath);
		
		String fileName;
		File file;
		String result = "";
		
		if(img[0] != null) {
			fileName = img[0].getOriginalFilename();
			file = new File(filePath, fileName);
			
			try {
				img[0].transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			result = "<span><img src='/chapter06_Web/storage/" + fileName + "' width='300' height='300' /></span>";
		}//if
		
		if(img[1] != null) {
			fileName = img[1].getOriginalFilename();
			file = new File(filePath, fileName);
			
			try {
				img[1].transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			result += "<span><img src='/chapter06_Web/storage/" + fileName + "' width='300' height='300' /></span>";
		}//if
		
		return result;
	}
	*/
	
	//----------- 한 번에 여러개의 파일을 선택 -----------------------
	@PostMapping(value="upload", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String upload(@ModelAttribute UserImageDTO userImageDTO,
			             @RequestParam("img[]") List<MultipartFile> list,
			             HttpSession session) {
		
		//실제폴더
		String filePath = session.getServletContext().getRealPath("/WEB-INF/storage");
		System.out.println("실제폴더 = " + filePath);
		
		String fileName;
		File file;
		String result = "";
		
		//파일명만 모아서 DB로 보내기
		List<String> fileNameList = new ArrayList<String>();
		
		for(MultipartFile img : list) {
			fileName = img.getOriginalFilename();
			file = new File(filePath, fileName);
			
			fileNameList.add(fileName);
			
			try {
				img.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			result += "<span><img src='/chapter06_Web/storage/" + fileName + "' width='300' height='300' /></span>";
			
		}//for
		
		//DB
		userServiceUpload.upload(userImageDTO, fileNameList);
		
		return result;
	}
	
}













