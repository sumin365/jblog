package com.bitacademy.jblog2.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bitacademy.jblog2.exception.ControllerException;

@Controller
public class MainController {
	@RequestMapping({ "/", "/main" })
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		// 전달된 ViewName을 기반으로 ViewResolver에게 실제 뷰
		// 위치를 질의
		return mav;
	}

	// 예외 처리 테스트
	@RequestMapping("/except")
	@ResponseBody
	public String except(HttpServletRequest req) {
		try {
			int result = 4 / 0; // -> 예외
		} catch (Exception e) {
			// 예외는 완벽하게 복구 되기 힘들기 때문에
			// 예외를 전환해서 외부로 던진다
//			System.err.println("예외:" + e.getMessage());
//			throw new RuntimeException("Main Controller Error");
			// 일반적인 예외는 구체적인 의미를 가진 예외로 전환해서
			// 처리하는 것을 권장
			throw new ControllerException("Main Controller Error", req);
		}
		return "Exception Test";
	}

}
