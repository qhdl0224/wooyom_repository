package com.lectopia.lab.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lectopia.lab.domain.BoardVO;
import com.lectopia.lab.service.BoardService;

@RestController
@RequestMapping(value="/api")
public class RestBoardController {
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<BoardVO> listAll() throws Exception{
		List<BoardVO> list = service.getAll();
		return list;
	}
	@RequestMapping(value="/getAllMap", method=RequestMethod.GET)
	public Map<String, BoardVO> getAllMap() throws Exception{
		List<BoardVO> list = service.getAll();
		Map<String,BoardVO> vo = new HashMap<String,BoardVO>();
		for(BoardVO v : list)
		{
			vo.put(String.valueOf(v.getBno()), v);
		}
		return vo;
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public BoardVO read(@RequestParam("bno") int bno) throws Exception{
		
		BoardVO vo = service.get(bno);
		
		return vo;
	}
	
	
	@RequestMapping(value="/get/{bno}",method=RequestMethod.GET)
	public BoardVO readPath(@PathVariable("bno") int bno) throws Exception{
		
		BoardVO vo = service.get(bno);
		
		return vo;
	}
	
	
	

	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String add(BoardVO vo) throws Exception {
		
		try {
			service.regist(vo);
			return "{\"result\": \"OK\"}";
			
		} catch(Exception e) {
			return "{\"result\": \"NG\"}";
		}
	}
	
	
}
