package com.mi.bookvillage.customer.model.dao;

import com.mi.bookvillage.customer.model.vo.CustomerVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerDAO {


    List<CustomerVO> getCustomerList();
    CustomerVO loginCustomer(CustomerVO customerVO);
    void addCustomer(CustomerVO customerVO);
    CustomerVO getCustomerBySeq(int userSeq);
    CustomerVO getCustomerById(String userId);
    void updateCustomer(CustomerVO customerVO);
    void deleteCustomer(int userSeq);
}
