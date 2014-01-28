package br.com.ss.data.controlador;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class TerminalController {

	public String handleCommand(String command, String[] params) {
		if (command.equals("greet")) {
			if (params.length > 0)
				return "Hello " + params[0];
			else
				return "Hello Stranger";
		} else if (command.equals("date"))
			return new Date().toString();
		else
			return command + " not found";
	}
}
