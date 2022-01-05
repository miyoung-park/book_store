package com.mi.bookvillage.customer.api;

import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.common.response.APIResponseBuilder;
import com.mi.bookvillage.common.response.APIResponseBuilderFactory;
import com.mi.bookvillage.common.security.JWTokenUtil;
import com.mi.bookvillage.customer.model.service.CustomerService;
import com.mi.bookvillage.customer.model.vo.CustomerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


    @RequestMapping(value="/customer/login" , method = RequestMethod.POST)
    public APIResponse LoginCustomer(@RequestBody CustomerVO customerObj){

        String token;

        // 해당 아이디로 userId가 있는지 확인하고 비밀번호 matched
        CustomerVO authCustomer = customerService.LoginCustomer(customerObj);
        try{
            Map<String, Object> authCustomerObj = new HashMap<>();
            authCustomerObj.put("userId" , authCustomer.getUserId());
            // 토큰 발급 및 return
            token = JWTokenUtil.createJwToken(authCustomerObj);
            return apiResponseBuilderFactory.success().setData(token).build();
        } catch(Exception e){
            e.printStackTrace();
        }
        return apiResponseBuilderFactory.success().build();
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
