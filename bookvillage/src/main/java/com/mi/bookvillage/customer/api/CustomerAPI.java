package com.mi.bookvillage.customer.api;

import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.common.response.APIResponseBuilder;
import com.mi.bookvillage.common.response.APIResponseBuilderFactory;
import com.mi.bookvillage.customer.model.service.CustomerService;
import com.mi.bookvillage.customer.model.vo.CustomerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * CustomerAPI
 */
@RestController
@Slf4j
public class CustomerAPI {

    private CustomerService customerService;
    private APIResponseBuilderFactory apiResponseBuilderFactory;

    public CustomerAPI(CustomerService customerService , APIResponseBuilderFactory apiResponseBuilderFactory ){
        this.customerService = customerService;
        this.apiResponseBuilderFactory = apiResponseBuilderFactory;
    }


    @RequestMapping(value = "/customer/list" , method = RequestMethod.GET)
    public APIResponse getCustomerList(){
        List<CustomerVO> customerList = customerService.getCustomerList();
        return apiResponseBuilderFactory.success().setData(customerList).build();
    }


    @RequestMapping(value="/customer/add" , method = RequestMethod.POST)
    public APIResponse addCustomer(@RequestBody CustomerVO customerVO){
        customerService.addCustomer(customerVO);
        return apiResponseBuilderFactory.success().build();
    }


    @RequestMapping(value ="/customer/detail/{userSeq}" , method = RequestMethod.GET)
    public  APIResponse getCustomerDetail(@PathVariable("userSeq") int userSeq){
        CustomerVO customerVO = customerService.getCustomerDetail(userSeq);
        return apiResponseBuilderFactory.success().setData(customerVO).build();
    }


    @RequestMapping(value="/customer/update/{userSeq}" , method = RequestMethod.PUT)
    public APIResponse updateCustomer(@PathVariable("userSeq") int userSeq,
                                      @ModelAttribute CustomerVO customerVO){
        customerService.updateCustomer(customerVO);
        return apiResponseBuilderFactory.success().build();
    }


    @RequestMapping(value="/customer/delete/{userSeq}" , method = RequestMethod.DELETE)
    public APIResponse deleteCustomer(@PathVariable("userSeq") int userSeq){
        customerService.deleteCustomer(userSeq);
        return apiResponseBuilderFactory.success().build();
    }



}
