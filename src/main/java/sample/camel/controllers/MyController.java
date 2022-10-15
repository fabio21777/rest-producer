
package sample.camel.controllers;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.camel.NumberTest;
import sample.camel.Timer;

@RestController
public class MyController {

	@GetMapping(value = "/random/{id}")
	public String getRandomString(@PathVariable("id") Integer id) {
		return "HTTP GET Got " + randomString(id);
	}


	@GetMapping(value = "/random/teste/{id}")
	public NumberTest getRandomStringTeste(@PathVariable("id") Integer id) {
		Random random = new Random();
		return new NumberTest(random.nextInt(10000));
	}

	@PostMapping("evenOrOdd")
	public String evenOrOdd(@RequestBody NumberTest number) {
		return number.getNumber() % 2 == 0 ? "seu numero é par : " + number.getNumber() : "seu numero é impar : " + number.getNumber();
	}

	   
	@PostMapping("/time")
	public String getTime(@RequestBody Timer data) {
		return "HTTP POST Got " + data.getData();
	}
	
	String randomString(int len) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		
		return sb.toString();
	}
}
