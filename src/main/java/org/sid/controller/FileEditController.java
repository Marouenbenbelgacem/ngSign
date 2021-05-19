package org.sid.controller;

import java.io.File;
import java.io.IOException;

import org.sid.model.FileData;
import org.sid.services.FileEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import groovyjarjarcommonscli.MissingArgumentException;

@RestController
@RequestMapping(path = "/fileUpload")
@CrossOrigin(origins = "http://localhost:4200")
public class FileEditController {
	

	@Autowired
	FileEditService fileEditService;

	@PostMapping("/upload")
	public ResponseEntity uploadData(
			@RequestParam("pdf") MultipartFile pdf,
			@RequestParam("image") MultipartFile image,
			@RequestParam("xAxis") int xAxis,
			@RequestParam("yAxis") int yAxis) {
		
		FileData fileData = new FileData(pdf, image, xAxis, yAxis);
				
		try {
			String filepath = fileEditService.insertImageIntoPdf(fileData);
			return ResponseEntity.ok(new File(filepath));
		} catch (MissingArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Internal serveur error");
		}
	
	}

}
