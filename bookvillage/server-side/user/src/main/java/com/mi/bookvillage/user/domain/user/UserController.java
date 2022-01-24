package com.mi.bookvillage.user.domain.user;


import com.mi.bookvillage.common.common.response.ApiResponse;
import com.mi.bookvillage.common.common.response.ApiResponseBuilderFactory;
import com.mi.bookvillage.common.common.security.JWTokenUtil;
import com.mi.bookvillage.common.domain.User.UserVO;
import com.mi.bookvillage.user.domain.point.PointService;
import lombok.RequiredArgsConstructor;
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
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final PointService pointService;
    private final ApiResponseBuilderFactory apiResponseBuilderFactory;


    /**
     * 고객 로그인
     */
    @RequestMapping(value="/user/login" , method = RequestMethod.POST)
    public ApiResponse LoginCustomer(@RequestBody UserVO user) throws Exception {
        String token;

        // 해당 아이디로 userId가 있는지 확인하고 비밀번호 matched
        UserVO authCustomer = userService.loginUser(user);

        Map<String, Object> authCustomerMap = new HashMap<>();
        Map<String, Object> adminInfoMap = new HashMap<>();

        // 토큰 저장 정보 Object / 토큰 발급 및 return
        authCustomerMap.put("userId" , authCustomer.getUserId());
        token = JWTokenUtil.createJwToken(authCustomerMap);

        // store 저장 정보 Object
        adminInfoMap.put("token" , token);

        // log.info("Login Success  ::: " + customerObj.getUserId() + " Login Access");
        return apiResponseBuilderFactory.success().setData(adminInfoMap).build();

    }

    /**
     * 개인정보 조회
     */
    @RequestMapping(value ="/user/detail" , method = RequestMethod.POST)
    public ApiResponse getUserDetailById( HttpServletRequest request){

        String userId = JWTokenUtil.getUserIdFromToken(request);
        // --- user point GET
        UserVO user = userService.getUserDetailById(userId);
        int totalPoint = pointService.getPreviousTotalPoint(user.getUserSeq());
        user.setUserPoint(totalPoint);

        return apiResponseBuilderFactory.success(user).build();
    }

}
