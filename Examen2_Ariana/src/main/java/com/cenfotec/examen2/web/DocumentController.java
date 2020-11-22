package com.cenfotec.examen2.web;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/report")
public class DocumentController {

	@GetMapping
	public void getGeneratedDocument(HttpServletResponse response) throws IOException {
		response.setHeader("Content-disposition", "attachment; filename=test.pptx");
		XMLSlideShow ppt = new XMLSlideShow();
		ppt.createSlide();
		ppt.createSlide();
		ppt.createSlide();
		ppt.write(response.getOutputStream());
	}

}
