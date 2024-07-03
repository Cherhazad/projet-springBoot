package fr.diginamic.hello.services;

import org.springframework.stereotype.Component;

@Component
public class HelloService {
	
	public String salutation() {
		String message = "Je suis la classe de service et je vous dis Bonjour.";
		return message;
	}

}
