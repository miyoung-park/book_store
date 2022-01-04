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

    public List<CustomerVO> getCustomerList(){
        return customerDAO.getCustomerList();
    }

    public void addCustomer(CustomerVO customerVO){
        customerDAO.addCustomer(customerVO);
    }


    public CustomerVO getCustomerDetail(int userSeq){
        CustomerVO customerVO = customerDAO.getCustomerDetail(userSeq);
        return customerVO;
    }

    public void updateCustomer(CustomerVO customerVO){
        customerDAO.updateCustomer(customerVO);
    }

    public void deleteCustomer(int userSeq){
        customerDAO.deleteCustomer(userSeq);
    }

}
