package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {
	private DemoService service;

	public DemoController(DemoService service) {
		this.service = service;
	}

	@PostMapping(value = "/save")
	public String save() {
		return this.service.save();
	}

	@GetMapping(value = "/getPdf")
	public void getPdf(String uuid, HttpServletResponse response) throws Exception {
		byte[] bs = Base64.getDecoder().decode(fileToBase64("D:\\TEST_PDF_FILE.pdf"));
		response.setContentType("application/pdf");
		response.getOutputStream().write(bs);
		this.service.getPdf(uuid);
	}

	public static String fileToBase64(String path) {
		String base64 = null;
		InputStream in = null;
		try {
			File file = new File(path);
			in = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			in.read(bytes);
			base64 = Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return base64;
	}
}
