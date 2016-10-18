package com.wha.springmvc.controller;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wha.springmvc.model.FileModel;
import com.wha.springmvc.model.Justificatif;
import com.wha.springmvc.model.TypeJustificatif;
import com.wha.springmvc.service.JustificatifService;
import com.wha.springmvc.util.FileValidator;
@RestController
public class FileUploadController {
	
	private static String UPLOAD_LOCATION = "C:/justificatifs/";

	@Autowired
	JustificatifService justificatifService;
									
	
	@Autowired
	FileValidator fileValidator;

	@InitBinder("fileModel")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

//	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
//	public String getHomePage(ModelMap model) {
//		return "General";
//	}

	@RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
	public String getSingleUploadPage(ModelMap model) {
		System.out.println("Essai Upload en GET Impossible");
		FileModel fileModel = new FileModel();
		model.addAttribute("fileModel", fileModel);
		return "GET Impossible";
	}

	@RequestMapping(value = "/demande/fileupload/{demande_id}&{nom}&{prenom}&{typeJustificatif}", method = RequestMethod.POST)
	public ResponseEntity<Void> singleFileUpload(@Valid FileModel fileModel, BindingResult result, ModelMap model, @PathVariable("demande_id") long demande_id,@PathVariable("nom") String nom, @PathVariable("prenom") String prenom, @PathVariable("typeJustificatif") TypeJustificatif typeJustificatif)
			throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			System.out.println("Fetching file");
			MultipartFile multipartFile = fileModel.getFile();

			
			String url = UPLOAD_LOCATION + demande_id+ "_" + nom + "_" + prenom + "_" + typeJustificatif + "." + fileModel.getFile().getOriginalFilename().split("\\.")[1];
			// Now do something with file...
			FileCopyUtils.copy(fileModel.getFile().getBytes(),
					new File(url));

			String fileName = multipartFile.getOriginalFilename();
			model.addAttribute("fileName", fileName);
			System.out.println(fileName);
			
			Justificatif justificatif = new Justificatif();
			justificatif.setType(typeJustificatif);
			justificatif.setUrl(url);
			justificatifService.saveJustificatif(demande_id, justificatif);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}


}
