package com.application.auth.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.application.auth.user.User;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Repository
public class ExRepositoryImpl implements ExRepository {

	private final SqlSession sqlSession;
	
	
	@Override
	public void saveUser(User user) {
		sqlSession.insert("user.insertUserInfo", user);

	}


	@Override
	public User findUserByEmail(String email) {
		return sqlSession.selectOne("user.getUserInfo", email);
	}

}
