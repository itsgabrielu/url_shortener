package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	@RequestMapping(value = "/{hash}", method = RequestMethod.GET)
	public void method(HttpServletResponse httpServletResponse,
		@PathVariable("hash") String hash) {
		httpServletResponse.setHeader("Location", String.format("http://www.google.com/search?q=%s",hash));
		httpServletResponse.setStatus(302);
	}
}