package com.mi.bookvillage.common.domain.User;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {


    List<UserVO> getCustomerList();
    UserVO loginCustomer(UserVO userVO);
    void addCustomer(UserVO userVO);
    UserVO getCustomerDetailBySeq(int userSeq);
    UserVO getCustomerDetailById(String userId);
    void updateCustomer(UserVO userVO);
    void deleteCustomer(int userSeq);
}
