package br.com.ss.centralaamar.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class RedirectorBean {
	public String userPage() {
		return "/pages/user/userPage.xhtml";
	}

	public String adminPage() {
		return "/pages/admin/adminPage.xhtml";
	}

	public String index() {
		return "index.xhtml";
	}

	public String successfulPage() {
		return "/pages/successfulPage.xhtml";
	}
}