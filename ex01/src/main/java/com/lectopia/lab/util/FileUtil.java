package com.lectopia.lab.util;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.test.context.ContextConfiguration;

import com.lectopia.lab.domain.BoardVO;
import com.lectopia.lab.persistence.BoardMapper;


@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class FileUtil {
	//중복 파일 처리할 수 있는 method
	@Inject
	private BoardMapper mapper;
	
	
	
	public String changeFileName(String filename) {
		UUID uid = UUID.randomUUID();
		String newFileName=filename;
		
		try {
			
			List<BoardVO> list = mapper.listAll();
			
			for(BoardVO v : list) {
				if(v.getFilename().equals(newFileName)) {
					newFileName = uid.toString()+"_"+newFileName;
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return newFileName;
	}
	
	public static void main(String[] args) {
		FileUtil futil = new FileUtil();
		
		System.out.println(futil.changeFileName("aaa.jpg"));
		System.out.println(futil.changeFileName("bbb.jpg"));
		System.out.println(futil.changeFileName("aaa.jpg"));
		System.out.println(futil.changeFileName("bbb.jpg"));
	}
	
}
