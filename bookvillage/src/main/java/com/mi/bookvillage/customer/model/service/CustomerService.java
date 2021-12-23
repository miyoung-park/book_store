package com.mi.bookvillage.customer.model.service;

import com.mi.bookvillage.customer.model.dao.CustomerDAO;
import com.mi.bookvillage.customer.model.vo.CustomerVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    public List<CustomerVO> getListCustomer(){
        return customerDAO.getListCustomer();
    }

}
