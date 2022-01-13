package com.mi.bookvillage.model.dao;

import com.mi.bookvillage.model.vo.CustomerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerDAO {


    List<CustomerVO> getCustomerList();
    CustomerVO loginCustomer(CustomerVO customerVO);
    void addCustomer(CustomerVO customerVO);
    CustomerVO getCustomerDetailBySeq(int userSeq);
    CustomerVO getCustomerDetailById(String userId);
    void updateCustomer(CustomerVO customerVO);
    void deleteCustomer(int userSeq);
}
