package com.wha.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wha.springmvc.model.FileModel;

@Controller
@RequestMapping("/")
public class IndexController {

	
	@RequestMapping(method = RequestMethod.GET)
	public String getIndexPage(ModelMap model) {
		/* ajout du model pour l'upload */
		FileModel fileModel = new FileModel();
		model.addAttribute("fileModel", fileModel);
		return "General";
	}
	

}