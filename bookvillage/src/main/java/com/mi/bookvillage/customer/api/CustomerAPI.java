package com.mi.bookvillage.customer.api;

import com.mi.bookvillage.admin.model.vo.AdminVO;
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

import javax.servlet.http.HttpServletRequest;
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
        CustomerVO authCustomer = customerService.loginCustomer(customerObj);
        try{
            Map<String, Object> authCustomerObj = new HashMap<>();
            authCustomerObj.put("userId" , authCustomer.getUserId());
            // 토큰 발급 및 return
            token = JWTokenUtil.createJwToken(authCustomerObj);
            // token / roles put
            Map<String, Object> adminInfoMap = new HashMap<>();
            adminInfoMap.put("token" , token);
            adminInfoMap.put("role" , authCustomer.getUserRole());
            return apiResponseBuilderFactory.success().setData(adminInfoMap).build();
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


    /**
     * 관리자가 고객정보 조회
     * @param userSeq
     * @param request
     * @return
     */
    @RequestMapping(value ="/customer/detail/{userSeq}" , method = RequestMethod.GET)
    public APIResponse getCustomerDetailBySeq(  @PathVariable("userSeq") int userSeq
            , HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");

        // --- 토큰 유효성 검사
        if( token == null || ! JWTokenUtil.checkToken(token)){
            return null; // --- Exception 추가
        }
        // --- admin 정보 GET
        CustomerVO customerVO = customerService.getCustomerDetail(userSeq);
        return apiResponseBuilderFactory.success().setData(customerVO).build();
    }

    /**
     * 해당유저가 정보 조회
     * @param customerVO
     * @param request
     * @return
     */
    @RequestMapping(value ="/customer/detail" , method = RequestMethod.POST)
    public APIResponse getCustomerDetailById( @ModelAttribute CustomerVO customerVO
                                              , HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");

        // --- 토큰 유효성 검사
        if( token == null || ! JWTokenUtil.checkToken(token)){
            return null; // --- Exception 추가
        }

        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");

        // --- admin 정보 GET
        CustomerVO customer = customerService.getCustomerDetailById(customerId);
        return apiResponseBuilderFactory.success().setData(customer).build();
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
