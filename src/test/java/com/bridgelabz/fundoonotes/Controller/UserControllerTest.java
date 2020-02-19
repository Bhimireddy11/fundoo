package com.bridgelabz.fundoonotes.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.bridgelabz.fundoonotes.Model.UserDemo;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
  private MockMvc mockmvc;
	@Autowired
	private UserService userService;
	@Test
	public void TestRegistration() {
		UserDemo mockUser=new UserDemo();
	}
}
