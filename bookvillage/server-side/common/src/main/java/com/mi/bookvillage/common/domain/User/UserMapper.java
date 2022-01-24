package com.mi.bookvillage.common.domain.User;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {


    List<UserVO> getUserList();
    UserVO loginUser(UserVO user);
    void addUser(UserVO user);
    UserVO getUserDetailBySeq(int userSeq);
    UserVO getUserDetailById(String userId);
    void updateUser(UserVO user);
    void deleteUser(int userSeq);
}
