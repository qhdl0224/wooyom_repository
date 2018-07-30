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
import com.lectopia.lab.domain.UserVO;
import com.lectopia.lab.service.BoardService;
import com.lectopia.lab.service.UserService;
import com.lectopia.lab.util.MD5Util;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPost(UserVO vo,Model model,HttpSession session) {
		session.removeAttribute("login");
		try {
			UserVO dbUserVO = service.login(vo.getUserid());
			if(dbUserVO!=null) {
				if(dbUserVO.getUserpwd().equals(MD5Util.GET_CRYPTO_MD5( vo.getUserpwd()))) {
				//if(dbUserVO.getUserpwd().equals( vo.getUserpwd())) {
					session.setAttribute("login",vo.getUserid());
					return "redirect:/board/listAll";
				}else {
					model.addAttribute("error", "wrong_userpwd");
					return "/login";
				}
			}else {
				model.addAttribute("error", "wrong_userid");
				return "/login";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "/error";
		}
	}
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public String logout(HttpSession session) {
		session.removeAttribute("login");
		return "redirect:/";
	}
	
	
	
}
