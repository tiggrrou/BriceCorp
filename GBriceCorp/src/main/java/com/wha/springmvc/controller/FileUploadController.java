package com.wha.springmvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	// @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	// public String getHomePage(ModelMap model) {
	// return "General";
	// }

	@RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
	public String getSingleUploadPage(ModelMap model) {
		System.out.println("Essai Upload en GET Impossible");
		FileModel fileModel = new FileModel();
		model.addAttribute("fileModel", fileModel);
		return "GET Impossible";
	}

	@RequestMapping(value = "/demande/fileupload/{id_demandeouclient}&{nom}&{prenom}&{typeJustificatif}&{clientOuDemande}", method = RequestMethod.POST)
	public ResponseEntity<Void> singleFileUpload(@Valid FileModel fileModel, BindingResult result, ModelMap model,
			@PathVariable("id_demandeouclient") long id_demandeouclient, @PathVariable("nom") String nom, @PathVariable("prenom") String prenom,
			@PathVariable("typeJustificatif") TypeJustificatif typeJustificatif,
			@PathVariable("clientOuDemande") int clientOuDemande) throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			System.out.println("Fetching file");
			String repertoire = UPLOAD_LOCATION;
			if (clientOuDemande == 0) {
				repertoire = UPLOAD_LOCATION + "demandes/" + id_demandeouclient + "/";
			} else if (clientOuDemande == 1){
				repertoire = UPLOAD_LOCATION+ "clients/" + id_demandeouclient + "/";
			}
			Date aujourdhui = new Date();
			
			SimpleDateFormat  formater = new SimpleDateFormat("YYYY-MM-dd");
		
			
			
			String nomfichier = nom + "_" + prenom + "_" + typeJustificatif + "_"+ formater.format(aujourdhui)+"."
					+ fileModel.getFile().getOriginalFilename().split("\\.")[1];

			File drirectory = new File(repertoire);
			if (!drirectory.exists()) {
				if (drirectory.mkdir()) {
					System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}

			// Now do something with file...
			FileCopyUtils.copy(fileModel.getFile().getBytes(), new File(repertoire + nomfichier));
			// MultipartFile multipartFile = fileModel.getFile();
			// String fileName = multipartFile.getOriginalFilename();
			// model.addAttribute("fileName", fileName);

			Justificatif justificatif = new Justificatif();
			justificatif.setType(typeJustificatif);
			justificatif.setPath(repertoire);
			justificatif.setNomDuFichier(nomfichier);
			justificatif.setDate(aujourdhui);
			justificatifService.saveJustificatif(id_demandeouclient, justificatif, clientOuDemande);

			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	// -------------------Retrieve justificatifs by Id de la demande ou du client--------------------------------------------------------

	@RequestMapping(value = "/demande/justificatif/{id}&{clientOuDemande}", method = RequestMethod.GET)
	public ResponseEntity<List<Justificatif>> justificatifsById(@PathVariable("id") long id,
			@PathVariable("clientOuDemande") int clientOuDemande) {
		System.out.println("fetch justificatifs " + id);
		
		try {
			List<Justificatif> justificatifs = justificatifService.findById(id, clientOuDemande);

			return new ResponseEntity<List<Justificatif>>(justificatifs, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<Justificatif>>(HttpStatus.NO_CONTENT);
		}		
				

	

		
	
	}
	
	// -------------------download file--------------------------------------------------------

	@RequestMapping(value = "/demande/download", method = RequestMethod.PUT)
	public ResponseEntity<InputStreamResource> download(@RequestBody String file) {
		System.out.println("fetch file " + file);
		
		try {
			
		    File downloadfile = new File(file);
		   
		    HttpHeaders respHeaders = new HttpHeaders();
		    respHeaders.setContentType(MediaType.APPLICATION_PDF);
		    respHeaders.setContentLength(downloadfile.length());
		    respHeaders.setContentDispositionFormData("attachment", downloadfile.getName());
		    InputStreamResource isr = new InputStreamResource(new FileInputStream(downloadfile));
		    return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<InputStreamResource>(HttpStatus.NO_CONTENT);
		}		
				

	

		
	
	}
}
