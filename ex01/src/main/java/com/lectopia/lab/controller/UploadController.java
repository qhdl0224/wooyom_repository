package com.lectopia.lab.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.lectopia.lab.domain.BoardVO;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	/*@Autowired
	private String uploadPath;*/
	
	@RequestMapping(value="/uploadForm",method=RequestMethod.GET)
	public void uploadForm() {
		
	}
	
	//commons-fileupload library
	@RequestMapping(value="/uploadForm",method=RequestMethod.POST)
	public void uploadForm(MultipartFile file, Model model,HttpServletRequest req) throws Exception{
		BoardVO vo = new BoardVO();
		vo.setWriter(req.getParameter("userid"));
		if(file!=null) {
			//System.out.println("UserId : "+req.getParameter("userid"));
			logger.info("originalName : "+file.getOriginalFilename());
			logger.info("size: "+file.getSize());
			logger.info("contentType: "+file.getContentType());

			String newFilename = uploadFile(file.getOriginalFilename(),file.getBytes(),vo.getWriter());
			//model.addAttribute("savedName", file.getOriginalFilename());
			
			vo.setFilename(newFilename);
			vo.setFilesize((int)file.getSize());
			vo.setOldname(file.getOriginalFilename());
			
		}
		
		//insert board�۾��ϱ�
		System.out.println(vo);
		
		
	}
	/*@RequestMapping(value="/uploadForm",method=RequestMethod.POST)
	public void uploadForm(HttpServletRequest req) throws Exception{
		
		String paramUserId = req.getParameter("userid");
		String paramFile = req.getParameter("file");
		System.out.println("UserId:" + paramUserId);
		System.out.println("File: "+paramFile);
		InputStream is = req.getInputStream();
		
		byte[] buf = new byte[1024];
		int c;
		while((c=is.read(buf))!=-1) {
			System.out.println(new String(buf));
		}
		
		
	}*/
	
	private String uploadFile(String fileName, byte[] fileData,String userId) throws Exception{
		/*UUID uid = UUID.randomUUID();
		String savedName = uid.toString()+"_"+fileName;*/
		File dir = new File("/test_file/upload/"+userId);//���� ���
	//	File dir = new File("./test_file/upload/"); //��� ���
		//���� �۾� �� Web ���� (��Ĺ)�� �����ϰ� ���� ���?
	//	File dir = new File(dirName+ File.separator+"test_file/upload/");
	//	System.out.println(dirName+ File.separator+"test_file/upload/");
		if(!dir.exists()) {
			dir.mkdirs();//�������� �ʴ� ��� ��θ� �� ����
			//dir.mkdir();//���� ������ ������ ����
		}
		File target = new File(dir,getNewFilename(fileName));
		System.out.println("Path = "+target.getAbsolutePath());
		FileCopyUtils.copy(fileData, target);
		
		return target.getName();
	}
	
	private String getNewFilename(String filename) {
		String ext = filename.substring(filename.lastIndexOf(".")); //�ڿ� Ȯ����
		return UUID.randomUUID().toString() + ext;
		
	}
}
