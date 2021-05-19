package org.sid.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.sid.model.FileData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import groovyjarjarcommonscli.MissingArgumentException;

@Service
public class FileEditService {

	public String insertImageIntoPdf(FileData fileData) throws MissingArgumentException, IOException {

		MultipartFile pdf = fileData.getPdf();
		MultipartFile image = fileData.getImage();

		if (pdf == null || image == null) {
			throw new MissingArgumentException("Missing pdf or image file");
		}

		// Loading an existing document
		PDDocument doc = PDDocument.load(pdf.getBytes());

		// Retrieving the page
		PDPage page = doc.getPage(0);

		// Copy received image to temp dir
		image.transferTo(new File(System.getProperty("java.io.tmpdir") + "\\tmp-" + image.getOriginalFilename()));

		// Creating PDImageXObject object
		PDImageXObject pdImage = PDImageXObject
				.createFromFile(System.getProperty("java.io.tmpdir") + "\\tmp-" + image.getOriginalFilename(), doc);

		// creating the PDPageContentStream object
		PDPageContentStream contents = new PDPageContentStream(doc, page, AppendMode.APPEND, true);

		// Drawing the image in the PDF document
		contents.drawImage(pdImage, fileData.getxAxis(), fileData.getyAxis());

		System.out.println("Image inserted");

		// Closing the PDPageContentStream object
		contents.close();

		// Saving the document
		String filePath = System.getProperty("java.io.tmpdir") + "\\" + pdf.getOriginalFilename() + "-MODIFIED.pdf";
		doc.save(new File(filePath));

		// Closing the document
		doc.close();
		
		return filePath; 
	
		
		

	}

}
