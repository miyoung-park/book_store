package com.mi.bookvillage.api;

import com.mi.bookvillage.model.service.CustomerService;
import com.mi.bookvillage.model.vo.CustomerVO;
import com.mi.bookvillage.model.service.PointService;
import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.common.response.APIResponseBuilderFactory;
import com.mi.bookvillage.common.security.JWTokenUtil;
import lombok.extern.slf4j.Slf4j;
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
    private PointService pointService;
    private APIResponseBuilderFactory apiResponseBuilderFactory;

    public CustomerAPI(CustomerService customerService, PointService pointService, APIResponseBuilderFactory apiResponseBuilderFactory ){
        this.customerService = customerService;
        this.pointService = pointService;
        this.apiResponseBuilderFactory = apiResponseBuilderFactory;
    }


    /**
     * 고객 로그인
     * @param customerObj
     * @return
     */
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
            return apiResponseBuilderFactory.success().setData(adminInfoMap).build();
        } catch(Exception e){
            e.printStackTrace();
        }
        return apiResponseBuilderFactory.success().build();
    }


    /**
     * 고객정보 리스트 : admin
     * @return
     */
    @RequestMapping(value = "/customer/list" , method = RequestMethod.GET)
    public APIResponse getCustomerList(){
        List<CustomerVO> customerList = customerService.getCustomerList();
        return apiResponseBuilderFactory.success().setData(customerList).build();
    }


    /**
     * 고객정보 추가 : admin
     * @param customerVO
     * @return
     */
    @RequestMapping(value="/customer/add" , method = RequestMethod.POST)
    public APIResponse addCustomer(@RequestBody CustomerVO customerVO){
        customerService.addCustomer(customerVO);
        return apiResponseBuilderFactory.success().build();
    }


    /**
     * 관리자가 고객정보 조회 : admin
     * @param userSeq
     * @param request
     * @return
     */
    @RequestMapping(value ="/customer/detail/{userSeq}" , method = RequestMethod.GET)
    public APIResponse getCustomerDetailBySeq(  @PathVariable("userSeq") int userSeq
                                              , CustomerVO customerVo
                                              , HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");

        // --- 토큰 유효성 검사
        if( token == null || ! JWTokenUtil.checkToken(token)){
            return null; // --- Exception 추가
        }
        // --- customer information GET
        CustomerVO customerVO = customerService.getCustomerDetailBySeq(userSeq);
        // --- customer point GET
        int totalPoint = pointService.getPreviousTotalPoint(userSeq);

        customerVO.setUserPoint(totalPoint);

        return apiResponseBuilderFactory.success().setData(customerVO).build();
    }

    /**
     * 해당유저가 정보 조회 : user
     * @param request
     * @return
     */
    @RequestMapping(value ="/customer/detail" , method = RequestMethod.POST)
    public APIResponse getCustomerDetailById( HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");

        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");
        // --- customer information GET
        CustomerVO customer = customerService.getCustomerDetailById(customerId);

        // --- customer point GET
        int totalPoint = pointService.getPreviousTotalPoint(customer.getUserSeq());
        customer.setUserPoint(totalPoint);
        return apiResponseBuilderFactory.success().setData(customer).build();
    }


    /**
     * 고객정보 업데이트 : admin
     * @param customerVO
     * @param request
     * @return
     */
    @RequestMapping(value="/customer/update/{userSeq}" , method = RequestMethod.PUT)
    public APIResponse updateCustomer(@RequestBody CustomerVO customerVO,
                                      HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");

        // --- 토큰 유효성 검사
        if( token == null || ! JWTokenUtil.checkToken(token)){
            return null; // --- Exception 추가
        }

        customerService.updateCustomer(customerVO);
        return apiResponseBuilderFactory.success().build();
    }


    /**
     * 고객정보 삭제 : admin
     * @param userSeq
     * @return
     */
    @RequestMapping(value="/customer/delete/{userSeq}" , method = RequestMethod.DELETE)
    public APIResponse deleteCustomer(@PathVariable("userSeq") int userSeq){
        customerService.deleteCustomer(userSeq);
        return apiResponseBuilderFactory.success().build();
    }



}
