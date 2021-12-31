package com.mi.bookvillage.customer.api;

import com.mi.bookvillage.customer.model.service.CustomerService;
import com.mi.bookvillage.customer.model.vo.CustomerVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerAPI {

    private CustomerService customerService;

    public CustomerAPI(CustomerService customerService){
        this.customerService = customerService;
    }





    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public ResponseEntity<?> getListCustomer(){
        List<CustomerVO> customerList = customerService.getListCustomer();

        return ResponseEntity.ok(customerList);
    }



}
