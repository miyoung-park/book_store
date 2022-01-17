package com.mi.bookvillage.common.mapper;

import com.mi.bookvillage.common.vo.CustomerVO;
import org.apache.ibatis.annotations.Mapper;

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
