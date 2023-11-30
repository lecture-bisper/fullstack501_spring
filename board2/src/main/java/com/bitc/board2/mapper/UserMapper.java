package com.bitc.board2.mapper;

import com.bitc.board2.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
  int isUserInfo(@Param("userId") String userId, @Param("userPw") String userPw) throws Exception;

  UserDTO getUserInfo(@Param("userId") String userId) throws Exception;
}













