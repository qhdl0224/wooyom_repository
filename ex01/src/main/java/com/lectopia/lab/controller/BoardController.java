package com.lectopia.lab.controller;


import java.io.File;
import java.io.Writer;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lectopia.lab.domain.BoardVO;
import com.lectopia.lab.domain.Criteria;
import com.lectopia.lab.domain.PageMaker;
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
	
	/*@RequestMapping(value="/regist", method=RequestMethod.POST)
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
		return "redirect:/board/listPage"; //WEB-INF/classes/views/success.jsp
	}*/
	
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
	
	//페이지 용
	@RequestMapping(value="/listCri",method=RequestMethod.GET)
	public void listAll(Criteria cri,Model model) throws Exception{
		logger.info("show list Page with Criteria......");
		
		model.addAttribute("list", service.listCriteria(cri));
	}
	
	//페이지용 2
	
	@RequestMapping(value="/listPage", method=RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception{
		logger.info(cri.toString());
		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
	//	pageMaker.setTotalCount(131);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		
	}
	//페이지용 3
	@RequestMapping(value="/readPage",method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno,
							@ModelAttribute("cri") Criteria cri,
							Model model) throws Exception{
		
		model.addAttribute(service.get(bno));
	}
	//페이지용 4
		@RequestMapping(value="/removePage",method=RequestMethod.POST)
		public String remove(@RequestParam("bno") int bno,
								 Criteria cri,
								RedirectAttributes rttr) throws Exception{
			
			service.remove(bno);
			
			
			rttr.addAttribute("page", cri.getPage());
			rttr.addAttribute("perPageNum", cri.getPerPageNum());
			rttr.addFlashAttribute("msg","SUCCESS");
			
			return "redirect:/board/listPage";
		}
		
		//페이지용 5
				@RequestMapping(value="/modifyPage",method=RequestMethod.GET)
				public void modifyPagingGET(@RequestParam("bno") int bno,
										 @ModelAttribute("cri") Criteria cri,
										Model model) throws Exception{
					
					model.addAttribute(service.get(bno));
				}
				//페이지용 6
				@RequestMapping(value="/modifyPage",method=RequestMethod.POST)
				public String modifyPagingPOST(BoardVO vo,
										 Criteria cri,MultipartFile file,
										 RedirectAttributes rttr) throws Exception{
					System.out.println(vo);
					if(file!=null) {

						String newFilename = uploadFile(file.getOriginalFilename(),file.getBytes(),vo.getWriter());
					
						vo.setFilename(newFilename);
						vo.setFilesize((int)file.getSize());
						vo.setOldname(file.getOriginalFilename());
						
					}
					
					service.modify(vo);
					
					
					rttr.addAttribute("page", cri.getPage());
					rttr.addAttribute("perPageNum", cri.getPerPageNum());
					rttr.addFlashAttribute("msg","SUCCESS");
					
					return "redirect:/board/listPage";
					
				}
				
				
				
				
				
				
		//upload File------------------------------------------------------------
				
				
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
				
				//insert board작업하기
				System.out.println(vo);
				try {
					service.regist(vo);	
					//temp에 저장해뒀다가 잘 저장되면 user 폴더로 이동
				}catch(Exception e) {
					e.printStackTrace();
					//주기적으로 temp folder 안의 내용 삭제
				}
				
			}
				
				
			private String uploadFile(String fileName, byte[] fileData,String userId) throws Exception{
				/*UUID uid = UUID.randomUUID();
				String savedName = uid.toString()+"_"+fileName;*/
				File dir = new File("/test_file/upload/"+userId);//절대 경로
			//	File dir = new File("./test_file/upload/"); //상대 경로
				//현재 작업 중 Web 폴더 (톰캣)에 저장하고 싶을 경우?
			//	File dir = new File(dirName+ File.separator+"test_file/upload/");
			//	System.out.println(dirName+ File.separator+"test_file/upload/");
				if(!dir.exists()) {
					dir.mkdirs();//존재하지 않는 모든 경로를 다 생성
					//dir.mkdir();//제일 마지막 폴더만 생성
				}
				File target = new File(dir,getNewFilename(fileName));
				System.out.println("Path = "+target.getAbsolutePath());
				FileCopyUtils.copy(fileData, target);
				
				return target.getName();
			}
			
			private String getNewFilename(String filename) {
				String ext = filename.substring(filename.lastIndexOf(".")); //뒤에 확장자
				return UUID.randomUUID().toString() + ext;
				
			}
			
			@RequestMapping(value="/regist", method=RequestMethod.POST)
			public String registerPost(BoardVO vo,MultipartFile file,Model model,
					HttpServletRequest req) throws Exception {
				
				
				if(file!=null) {

					String newFilename = uploadFile(file.getOriginalFilename(),file.getBytes(),vo.getWriter());
				
					vo.setFilename(newFilename);
					vo.setFilesize((int)file.getSize());
					vo.setOldname(file.getOriginalFilename());
					
				}
				
				//insert board작업하기
				System.out.println(vo);
				try {
					service.regist(vo);	
					//temp에 저장해뒀다가 잘 저장되면 user 폴더로 이동
				}catch(Exception e) {
					e.printStackTrace();
					//주기적으로 temp folder 안의 내용 삭제
				}
				
				
				logger.info("regist post method called");
				//logger.info(board.toString());
				
				
				//DB insert -> move to success page
				model.addAttribute("result", "success");
				return "redirect:/board/listPage"; //WEB-INF/classes/views/success.jsp
			}
}
