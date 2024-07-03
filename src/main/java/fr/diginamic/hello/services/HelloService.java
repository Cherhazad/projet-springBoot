package fr.diginamic.hello.services;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
	
	public HelloService() {
		System.out.println("HelloService : je suis instanciée depuis la classe HelloService");
	}
	
	public String salutations() {
		String message = "Je suis la classe de service et je vous dis Bonjour.";
		return message;
	}

}
