package com.mi.bookvillage.controller;

import com.mi.bookvillage.model.service.CustomerService;
import com.mi.bookvillage.model.vo.CustomerVO;
import com.mi.bookvillage.model.service.PointService;
import com.mi.bookvillage.model.vo.PointVO;
import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.common.security.JWTokenUtil;
import com.mi.bookvillage.common.util.point.PointFactory;
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
public class PointAPI {

    private PointService pointService;
    private CustomerService customerService;
    private String token;
    private PointFactory pointFactory;

    public PointAPI(PointService pointService, CustomerService customerService, PointFactory pointFactory){
        this.pointService = pointService;
        this.customerService = customerService;
        this.pointFactory = pointFactory;
    }

    /*  user  */

    /**
     * 포인트 목록 조회 : user
     * @return
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
     * 포인트 충전 : user
     * @param pointObj
     * @param request
     * @return
     */
    @RequestMapping(value = "/point/charge", method = RequestMethod.POST)
    public ResponseEntity<?> transactionPoint(@RequestBody PointVO pointObj,
                                         HttpServletRequest request ) {
        token = request.getHeader("Authorization");
        // TODO: 서비스 쪽에 PointFactory 숨겨두기 ( 메소드로 만들지 component 로 만들지 고민 )

        // 토큰 해독 후 customer 객체 GET
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");

        CustomerVO customer = customerService.getCustomerDetailById(customerId);
        // 포인트 충전 객체로 전환
        PointVO pointVO = pointFactory.chargePoint(pointObj , customer);
        // 포인트 적립
        pointService.transactionPoint(pointVO);

        return APIResponse.builder().success().build();
    }

    /*  admin  */


    /**
     * 포인트 목록 조회 : admin
     * @return
     */
    @RequestMapping(value = "/point/list/{userSeq}", method = RequestMethod.GET)
    public ResponseEntity<?> getPointListBySeq( @PathVariable int userSeq
                                          ,HttpServletRequest request ){
        // --- header 토큰 GET
        token = request.getHeader("Authorization");

        List<PointVO> pointList = pointService.getPointListBySeq(userSeq);

        return APIResponse.builder().success(pointList).build();
    }




}
