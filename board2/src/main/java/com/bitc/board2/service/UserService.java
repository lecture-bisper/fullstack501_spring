package com.bitc.board2.service;

import com.bitc.board2.dto.UserDTO;

public interface UserService {
  int isUserInfo(String userId, String userPw) throws Exception;

  UserDTO getUserInfo(String userId) throws Exception;
}













