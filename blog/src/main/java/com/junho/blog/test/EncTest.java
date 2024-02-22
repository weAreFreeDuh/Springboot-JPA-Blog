package com.junho.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

	@Test
	public void Test() {
		
		
		
		int blockSize =5;
		int currPage = 1;
		int startPage  = (int) (Math.floor(((currPage - 1) / blockSize)) * blockSize + 1);
		System.out.println(Math.floor(5.4));
		System.out.println(Math.floor(0));
		System.out.println(Math.floor((currPage - 1 / blockSize)));
		System.out.println(startPage);
	}
	
}
