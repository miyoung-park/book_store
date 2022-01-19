package com.mi.bookvillage.user.domain.user;

import com.mi.bookvillage.common.common.response.APIResponse;
import com.mi.bookvillage.common.common.security.JWTokenUtil;
import com.mi.bookvillage.common.domain.User.UserVO;
import com.mi.bookvillage.user.domain.point.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
public class UserController {


    private UserService userService;
    private PointService pointService;

    public UserController(UserService userService, PointService pointService){
        this.userService = userService;
         this.pointService = pointService;
    }

    /* user */

    /**
     * 고객 로그인 : user
     * @param customerObj
     * @return
     */
    @RequestMapping(value="/customer/login" , method = RequestMethod.POST)
    public ResponseEntity<?> LoginCustomer(@RequestBody UserVO customerObj) throws Exception {
        String token;

        // 해당 아이디로 userId가 있는지 확인하고 비밀번호 matched
        UserVO authCustomer = userService.loginCustomer(customerObj);

        Map<String, Object> authCustomerMap = new HashMap<>();
        Map<String, Object> adminInfoMap = new HashMap<>();

        // 토큰 저장 정보 Object / 토큰 발급 및 return
        authCustomerMap.put("userId" , authCustomer.getUserId());
        token = JWTokenUtil.createJwToken(authCustomerMap);

        // store 저장 정보 Object
        adminInfoMap.put("token" , token);

        // log.info("Login Success  ::: " + customerObj.getUserId() + " Login Access");
        return APIResponse.builder().success(adminInfoMap).build();

    }

    /**
     * 개인정보 조회 : user
     * @param request
     * @return
     */
    @RequestMapping(value ="/customer/detail" , method = RequestMethod.POST)
    public ResponseEntity<?> getCustomerDetailById( HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");

        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");

        // --- customer information GET
        UserVO customer = userService.getCustomerDetailById(customerId);

        // --- customer point GET
        int totalPoint = pointService.getPreviousTotalPoint(customer.getUserSeq());
        customer.setUserPoint(totalPoint);

        return APIResponse.builder().success(customer).build();
    }



    /*  admin  */

    /**
     * 고객정보 리스트 : admin
     * @return
     */
    @RequestMapping(value = "/customer/list" , method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerList(){
        List<UserVO> customerList = userService.getCustomerList();
        log.info("Success ::: success get customer all list");
        return APIResponse.builder().success(customerList).build();
    }


    /**
     * 고객정보 추가 : admin
     * @param userVO
     * @return
     */
    @RequestMapping(value="/customer/add" , method = RequestMethod.POST)
    public ResponseEntity<?> addCustomer(@RequestBody UserVO userVO){
        userService.addCustomer(userVO);
        log.info("Success ::: success insert customer information");
        return APIResponse.builder().success().build();
    }


    /**
     * 고객정보 조회 : admin
     * @param userSeq
     * @param request
     * @return
     */
    @RequestMapping(value ="/customer/detail/{userSeq}" , method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerDetailBySeq(  @PathVariable("userSeq") int userSeq
                                              , UserVO userVo
                                              , HttpServletRequest request){

        // --- customer information GET
        UserVO userVO = userService.getCustomerDetailBySeq(userSeq); // TODO: customer point 도 service 안에 추가
        // --- customer point GET
        int totalPoint = pointService.getPreviousTotalPoint(userSeq);
        userVO.setUserPoint(totalPoint);

        log.info("Success ::: success get customer detail");
        return APIResponse.builder().success(userVO).build();
    }


    /**
     * 고객정보 업데이트 : admin
     * @param userVO
     * @param request
     * @return
     */
    @RequestMapping(value="/customer/update/{userSeq}" , method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@RequestBody UserVO userVO,
                                      HttpServletRequest request){

        userService.updateCustomer(userVO);
        log.info("Success ::: success update customer");
        return APIResponse.builder().success().build();

    }


    /**
     * 고객정보 삭제 : admin
     * @param userSeq
     * @return
     */
    @RequestMapping(value="/customer/delete/{userSeq}" , method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable("userSeq") int userSeq){
        userService.deleteCustomer(userSeq);
        log.info("Success ::: success delete customer");
        return APIResponse.builder().success().build();
    }



}
