//package com.lbg.everestbe.rest;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@SpringBootApplication
//@RestController
//public class AuthenticationController {
//
//	private static final String ADMIN_USERNAME = "admin";
//	private static final String ADMIN_PASSWORD = "admin";
//
//	public static void main(String[] args) {
//		SpringApplication.run(AuthenticationController.class, args);
//	}
//
//	@PostMapping("/login")
//	public String login(@RequestBody UserCredentials credentials) {
//		String username = credentials.getUsername();
//		String password = credentials.getPassword();
//
//		if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
//			return "Successful login";
//		} else {
//			return "Invalid username or password";
//		}
//	}
//
//	public static class UserCredentials {
//		private String username;
//		private String password;
//
//		// Getters and setters
//
//		public String getUsername() {
//			return username;
//		}
//
//		public void setUsername(String username) {
//			this.username = username;
//		}
//
//		public String getPassword() {
//			return password;
//		}
//
//		public void setPassword(String password) {
//			this.password = password;
//		}
//	}
//
//}
