package com.junho.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspTestController {

	// http://localhost/test/jsp
	@GetMapping("/test/jsp")
	public String hello() {
		System.out.println("http://localhost/test/jsp");
		return "test";
	}

}
