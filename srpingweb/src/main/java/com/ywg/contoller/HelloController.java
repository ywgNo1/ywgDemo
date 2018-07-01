package com.ywg.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String test(@RequestParam("key") String key, HttpServletRequest request,HttpServletResponse response) {
		return key + "12312";
	}
	@ResponseBody
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String post(@RequestParam("key") String key, HttpServletRequest request,HttpServletResponse response) {
		return key + "12312";
	}
		
	
	
}
