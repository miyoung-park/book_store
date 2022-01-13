package com.mi.bookvillage.model.service;

import com.mi.bookvillage.model.dao.CustomerDAO;
import com.mi.bookvillage.model.vo.CustomerVO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerDAO customerDAO;
    private BCryptPasswordEncoder encoder;

    public CustomerService(CustomerDAO customerDAO , BCryptPasswordEncoder encoder){
        this.customerDAO = customerDAO;
        this.encoder = encoder;
    }

    public List<CustomerVO> getCustomerList(){
        return customerDAO.getCustomerList();
    }


    public CustomerVO loginCustomer(CustomerVO customerVO){
        CustomerVO authCustomer = customerDAO.loginCustomer(customerVO);
        if(authCustomer == null || !encoder.matches( customerVO.getUserPw(), authCustomer.getUserPw())){
            throw new RuntimeException("password can not be null or unmatched");
        }
        return authCustomer;
    }


    public void addCustomer(CustomerVO customerVO){
        String encodedPassword = encoder.encode(customerVO.getUserPw());
        customerVO.setUserPw(encodedPassword);
        customerDAO.addCustomer(customerVO);
    }


    public CustomerVO getCustomerDetailById(String userId){
        CustomerVO customer = customerDAO.getCustomerDetailById(userId);
        if(customer == null){
            throw new RuntimeException("Invalid userInfo");
        }
        return customer;
    }


    public CustomerVO getCustomerDetailBySeq(int userSeq){
        CustomerVO customer = customerDAO.getCustomerDetailBySeq(userSeq);
        if(customer == null){
            throw new RuntimeException("Invalid userInfo");
        }
        return customer;
    }


    public void updateCustomer(CustomerVO customerVO){
        if( customerVO.getUserPw() != null ){
            String encodedPassword = encoder.encode(customerVO.getUserPw());
            customerVO.setUserPw(encodedPassword);
        }
        customerDAO.updateCustomer(customerVO);
    }
    public void deleteCustomer(int userSeq){
        customerDAO.deleteCustomer(userSeq);
    }

}
