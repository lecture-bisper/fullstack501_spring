package com.bitc.board2.service;

import com.bitc.board2.dto.UserDTO;
import com.bitc.board2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public int isUserInfo(String userId, String userPw) throws Exception {
    return userMapper.isUserInfo(userId, userPw);
  }

  @Override
  public UserDTO getUserInfo(String userId) throws Exception {
    return userMapper.getUserInfo(userId);
  }
}







