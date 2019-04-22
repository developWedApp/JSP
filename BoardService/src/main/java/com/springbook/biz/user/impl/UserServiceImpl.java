package com.springbook.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	//setter Injection을 이용하기 위해  setter injection은 세터에서 받는 매개변수를 객체의 주소를 참조하는 참조변수를 담음.
	/*
	 * public void setUserDAO(UserDAO userDAO) { this.userDAO = userDAO;
	 * System.out.println("===>setter Injection"); }
	 */
	
	@Override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
		
	}

	
	@Override
	public void insertUser(UserVO vo) {
		userDAO.insertUser(vo);
	
		
	}
	
	
}
