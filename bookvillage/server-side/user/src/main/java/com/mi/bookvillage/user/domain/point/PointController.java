package com.mi.bookvillage.user.domain.point;


import com.mi.bookvillage.common.common.annotation.JwtAuthorization;
import com.mi.bookvillage.common.common.response.ApiResponse;
import com.mi.bookvillage.common.common.response.ApiResponseBuilderFactory;
import com.mi.bookvillage.common.common.security.JWTokenUtil;
import com.mi.bookvillage.common.domain.User.UserVO;
import com.mi.bookvillage.common.domain.Point.PointVO;
import com.mi.bookvillage.user.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Admin_PointAPI
 */

// TODO : PointVO 가 들어 왔을 때 해당 숫자가 음수인지 아닌지 Back 에서도 Validation 확인하기 / File 이나 Book 도 마찬가지 !
@RestController
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;
    private final UserService userService;
    private final ApiResponseBuilderFactory apiResponseBuilderFactory;


    /**
     * 포인트 목록 조회
     */
    @JwtAuthorization
    @RequestMapping(value = "/point/list", method = RequestMethod.GET)
    public ApiResponse getPointListById( HttpServletRequest request ){
       // 토큰 해독 , return userId
       String userId = JWTokenUtil.getUserIdFromToken(request);
       List<PointVO> pointList = pointService.getPointListById(userId);

       return apiResponseBuilderFactory.success().setData(pointList).build();
    }


    /**
     * 포인트 충전
     */
    @JwtAuthorization
    @RequestMapping(value = "/point/charge", method = RequestMethod.POST)
    public ApiResponse transactionPoint(@RequestBody PointVO point,
                                         HttpServletRequest request ) {
        // TODO: 서비스 쪽에 PointFactory 숨겨두기 ( 메소드로 만들지 component 로 만들지 고민 )
        String userId = JWTokenUtil.getUserIdFromToken(request);
        UserVO user = userService.getUserDetailById(userId);
        // 포인트 적립
        pointService.transactionPoint(point , user);

        return apiResponseBuilderFactory.success().build();
    }



}
