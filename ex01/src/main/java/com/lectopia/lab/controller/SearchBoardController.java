package com.lectopia.lab.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lectopia.lab.domain.BoardVO;
import com.lectopia.lab.domain.Criteria;
import com.lectopia.lab.domain.PageMaker;
import com.lectopia.lab.domain.SearchCriteria;
import com.lectopia.lab.service.BoardService;

@Controller
@RequestMapping(value="/sboard/*")
public class SearchBoardController {
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Autowired
	private BoardService service;
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		logger.info(cri.toString());
		model.addAttribute("list", service.listSearchCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
	//	pageMaker.setTotalCount(131);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		
	}
	
	//�������� 3
		@RequestMapping(value="/readPage",method=RequestMethod.GET)
		public void read(@RequestParam("bno") int bno,
								@ModelAttribute("cri") SearchCriteria cri,
								Model model) throws Exception{
			
			model.addAttribute(service.get(bno));
		}
	
		//�������� 4
				@RequestMapping(value="/removePage",method=RequestMethod.POST)
				public String remove(@RequestParam("bno") int bno,
										 SearchCriteria cri,
										RedirectAttributes rttr) throws Exception{
					
					service.remove(bno);
					
					
					rttr.addAttribute("page", cri.getPage());
					rttr.addAttribute("perPageNum", cri.getPerPageNum());
					rttr.addAttribute("searchType", cri.getSearchType());
					rttr.addAttribute("keyword", cri.getKeyword());
					rttr.addFlashAttribute("msg","SUCCESS");
					
					return "redirect:/sboard/list";
				}
	
				//�������� 5
				@RequestMapping(value="/modifyPage",method=RequestMethod.GET)
				public void modifyPagingGET(@RequestParam("bno") int bno,
										 @ModelAttribute("cri") SearchCriteria cri,
										Model model) throws Exception{
					
					model.addAttribute(service.get(bno));
				}
				//�������� 6
				@RequestMapping(value="/modifyPage",method=RequestMethod.POST)
				public String modifyPagingPOST(BoardVO vo,
										 SearchCriteria cri,MultipartFile file,
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

					rttr.addAttribute("searchType", cri.getSearchType());
					rttr.addAttribute("keyword", cri.getKeyword());
					rttr.addFlashAttribute("msg","SUCCESS");
					
					return "redirect:/sboard/list";
					
				}		
				
				
				
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
				
				
				
				@RequestMapping(value="/regist", method=RequestMethod.GET)
				public String registerView(BoardVO board, Model model,HttpSession session) throws Exception{
					//move to regist page
					logger.info("register get method called");
					
					String loginId = (String)session.getAttribute("login");
					if(loginId == null || loginId.isEmpty())
					{
						return "/login";
					}
							
				
					return "/sboard/register";//WEB-INF/classes/views/register.jsp
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
					
					//insert board�۾��ϱ�
					System.out.println(vo);
					try {
						service.regist(vo);	
						//temp�� �����ص״ٰ� �� ����Ǹ� user ������ �̵�
					}catch(Exception e) {
						e.printStackTrace();
						//�ֱ������� temp folder ���� ���� ����
					}
					
					
					logger.info("regist post method called");
					//logger.info(board.toString());
					
					
					//DB insert -> move to success page
					model.addAttribute("result", "success");
					return "redirect:/sboard/list"; //WEB-INF/classes/views/success.jsp
				}
			
}
