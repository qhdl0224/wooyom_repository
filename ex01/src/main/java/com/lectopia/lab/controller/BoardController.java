package com.lectopia.lab.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lectopia.lab.domain.BoardVO;
import com.lectopia.lab.service.BoardService;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class); 
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public String registerView(BoardVO board, Model model,HttpSession session) throws Exception{
		//move to regist page
		logger.info("register get method called");
		
		String loginId = (String)session.getAttribute("login");
		if(loginId == null || loginId.isEmpty())
		{
			return "/login";
		}
				
	
		return "/board/register";//WEB-INF/classes/views/register.jsp
	}
	
	/*@RequestMapping(value="/regist", method=RequestMethod.POST)
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
	}*/
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String registerPost(BoardVO vo,Model model) throws Exception {
		logger.info("regist post method called");
		//logger.info(board.toString());
		
		try {
			service.regist(vo);
		}catch(Exception e) {
			logger.error("Register post method - Error occured["+e.getMessage()+"]");
		}
		
		//DB insert -> move to success page
		model.addAttribute("result", "success");
		return "redirect:/board/listAll"; //WEB-INF/classes/views/success.jsp
	}
	
	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public ModelAndView listAll(Model model) throws Exception{
		ModelAndView mav = null;
		//search DB -> getAll() ->		->client
		try {
			List<BoardVO> list = service.getAll();
			mav = new ModelAndView("/board/listAll");
			mav.addObject("list", list);
		}catch(Exception e) {
			logger.error("List post method - Error occured["+e.getMessage()+"]");
		}
		return mav;
	}
	@RequestMapping(value="/read",method=RequestMethod.GET)
	public ModelAndView read(@RequestParam("bno") int bno) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/read");
		BoardVO vo = service.get(bno);
		if(vo!=null) {
			mav.addObject("boardVO", vo);
			
		}else {
			//move to error page
			mav.addObject("error", "wrong_bno");
			mav.setViewName("/board/listAll");
		}
		return mav;
	}
	
	@RequestMapping(value="/get/{bno}",method=RequestMethod.GET)
	public ModelAndView get(@PathVariable("bno") int bno) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/read");
		BoardVO vo = service.get(bno);
		if(vo!=null) {
			mav.addObject("boardVO", vo);
		}else {
			//move to error page
			mav.addObject("error", "wrong_bno");
			mav.setViewName("/board/listAll");
		}
		
		
		return mav;
	}

	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@RequestParam("bno") int bno,Model model) {
		
		//DB 검색 -> Modify 페이지로 이동
		try {
			model.addAttribute("boardVO",service.get(bno));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/board/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPost(BoardVO vo,Model model,RedirectAttributes rttr) {
		logger.info("regist post method called");
		//logger.info(board.toString());
		
		try {
			service.modify(vo);
		}catch(Exception e) {
			logger.error("Register post method - Error occured["+e.getMessage()+"]");
		}
		
		//DB insert -> move to success page
		rttr.addFlashAttribute("msg", "UPDATE");
		return "redirect:/board/listAll";
	}
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno,RedirectAttributes rttr) throws Exception{
		try {
			service.remove(bno);
			rttr.addFlashAttribute("msg", "SUCCESS");
		}catch(Exception e) {
			logger.error("delete post method - Error occured["+e.getMessage()+"]");
		}
		return "redirect:/board/listAll";
	}
	
	
	
}
