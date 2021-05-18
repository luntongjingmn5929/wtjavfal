package com.bayside.app.opinion.mobile.war.redirect.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bayside.app.util.HttpRequest;

@RestController
public class RedirectController {
	private static final Logger log = Logger.getLogger(RedirectController.class);
	@RequestMapping(value="/redirect", method=RequestMethod.GET)
	public ModelAndView redirect(ModelAndView model,String id,HttpServletRequest request,HttpServletResponse response){
		System.out.println(request.getRealPath("/upload"));
		model.setViewName("forward:content/fenxiang.html");
		//return new ModelAndView("forward:http://localhost:8080/app-opinion-mobile-war/testForward?id="+id);
		return model;
	}
	@RequestMapping(value="/redirect1", method=RequestMethod.GET)
	public void redirect1(String id,HttpServletRequest request,HttpServletResponse response){
		String url ="http://192.168.1.250:8020/app-opinion-mobile-web/src/main/webapp/content/fenxiang.html?articleid=sogonews1481875164704news.changsha.cn589&mid=93a56bdba6b747d398d72d64a5750af0sogonews1481875164704news.changsha.cn589&from=singlemessage&isappinstalled=1";
		String html = HttpRequest.sendGet(url, null);
		try {
			PrintWriter out = response.getWriter();
			out.print(html);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		System.out.println(html);
	}
	@RequestMapping(value="/testForward", method=RequestMethod.GET)
	public String testForward(String id){
		System.out.println(id);
		//return new ModelAndView("forward:http://localhost:8080");
		return id;
	}
}
