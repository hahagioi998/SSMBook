package com.snwjas.ssmbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 通用 Controller
 *
 * @author snwjas
 */
@Controller
public class MainController {

	@GetMapping("/")
	public String index() {
		return "redirect:/book";
	}

}
