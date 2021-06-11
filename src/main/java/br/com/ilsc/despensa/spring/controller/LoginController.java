package br.com.ilsc.despensa.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	private String inicio() {

		return "login";
	}
}
