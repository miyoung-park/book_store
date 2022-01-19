package com.mi.bookvillage.user.domain.point;


import com.mi.bookvillage.common.common.response.APIResponse;
import com.mi.bookvillage.common.common.security.JWTokenUtil;
import com.mi.bookvillage.common.domain.User.UserVO;
import com.mi.bookvillage.common.domain.Point.PointVO;
import com.mi.bookvillage.user.common.factory.PointFactory;
import com.mi.bookvillage.user.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Admin_PointAPI
 */

// TODO : PointVO 가 들어 왔을 때 해당 숫자가 음수인지 아닌지 Back 에서도 Validation 확인하기 / File 이나 Book 도 마찬가지 !
@RestController
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;
    private final UserService userService;
    private final PointFactory pointFactory;

    private String token;



    /**
     * 포인트 목록 조회
     */
    @RequestMapping(value = "/point/list", method = RequestMethod.GET)
    public ResponseEntity<?> getPointListById(HttpServletRequest request ){
        /*  header 토큰 GET */ // document 주석인지 확인
        token = request.getHeader("Authorization");

        /* 토큰 해독 */
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");
       List<PointVO> pointList = pointService.getPointListById(customerId);

       return APIResponse.builder().success(pointList).build();
    }


    /**
     * 포인트 충전
     */
    @RequestMapping(value = "/point/charge", method = RequestMethod.POST)
    public ResponseEntity<?> transactionPoint(@RequestBody PointVO pointObj,
                                         HttpServletRequest request ) {
        token = request.getHeader("Authorization");
        // TODO: 서비스 쪽에 PointFactory 숨겨두기 ( 메소드로 만들지 component 로 만들지 고민 )
        // 토큰 해독 후 customer 객체 GET
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");

        UserVO customer = userService.getCustomerDetailById(customerId);
        // 포인트 충전 객체로 전환
        PointVO pointVO = pointFactory.chargePoint(pointObj , customer);
        // 포인트 적립
        pointService.transactionPoint(pointVO);

        return APIResponse.builder().success().build();
    }



}
