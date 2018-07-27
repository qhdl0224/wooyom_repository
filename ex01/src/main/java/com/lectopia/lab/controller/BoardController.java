package com.lectopia.lab.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lectopia.lab.domain.BoardVO;
import com.lectopia.lab.service.BoardService;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class); 
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public String registerView(BoardVO board, Model model) throws Exception{
		//move to regist page
		logger.info("register get method called");
		return "/board/register";//WEB-INF/classes/views/register.jsp
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String registerPost(@ModelAttribute("newMember") BoardVO vo) throws Exception {
		logger.info("regist post method called");
		//logger.info(board.toString());
		
		try {
			service.regist(vo);
		}catch(Exception e) {
			logger.error("Register post method - Error occured["+e.getMessage()+"]");
		}
		
		//DB insert -> move to success page
		return "/board/success"; //WEB-INF/classes/views/success.jsp
	}
	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		logger.info("show all list");
	}
}
