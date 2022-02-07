package com.mi.bookvillage.user.domain.user;


import com.mi.bookvillage.common.common.response.ApiResponse;
import com.mi.bookvillage.common.common.response.ApiResponseBuilderFactory;
import com.mi.bookvillage.common.common.security.JWTokenUtil;
import com.mi.bookvillage.common.domain.User.UserVO;
import com.mi.bookvillage.user.domain.point.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * UserController
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
    public ApiResponse LoginUser(@RequestBody UserVO user) throws Exception {
        String token;

        // 해당 아이디가 존재하는지, 비밀번호가 matched 되는지 확인
        UserVO authUser = userService.loginUser(user);

        // 토큰발급
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("userId" , authUser.getUserId());
        String authToken = JWTokenUtil.createJwToken(tokenMap);

        // store 에 저장할 정보 전달
        Map<String, Object> adminInfoMap = new HashMap<>();
        adminInfoMap.put("token" , authToken);
        adminInfoMap.put("userId" , authUser.getUserId());
        adminInfoMap.put("userName" , authUser.getUserName());

        log.info("LOGIN_USER_ID  >>>>>  " + authUser.getUserId() );
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
