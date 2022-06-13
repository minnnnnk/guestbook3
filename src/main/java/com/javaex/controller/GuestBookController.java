package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Controller
public class GuestBookController {
	
	@RequestMapping(value="/list" ,method ={RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GuestBookController>list");
		
		GuestBookDao gDao = new GuestBookDao();
		
		List<GuestBookVo> gList = gDao.getGuestList();
		
		model.addAttribute("gList",gList);
		
		return "/WEB-INF/views/addList.jsp";
	}
	@RequestMapping(value="add", method= {RequestMethod.GET,RequestMethod.POST})
	public String add(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("GuestBookController>add");
		
		
		GuestBookDao gDao = new GuestBookDao();
		
		gDao.bookInsert(guestBookVo);
		
		return "redirect:/list";
		
	}
	
	@RequestMapping(value="/deleteForm" ,method= {RequestMethod.GET,RequestMethod.POST})
	public String deleteForm() {
		System.out.println("GuestBookController>deleteForm");
		return "/WEB-INF/views/deleteForm.jsp";
	}
	@RequestMapping(value="delete",method= {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam("no") int no,@RequestParam("password") String password) {
		System.out.println("GuestBookController>delete");
		
		GuestBookDao gDao = new GuestBookDao();
	
		int count = gDao.bookDelete(no , password);
		System.out.println(count);
		
		return "redirect:/list";
	}
	
	
}
