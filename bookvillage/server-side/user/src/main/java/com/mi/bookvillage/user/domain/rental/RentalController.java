package com.mi.bookvillage.user.domain.rental;


import com.mi.bookvillage.common.common.response.APIResponse;
import com.mi.bookvillage.common.common.security.JWTokenUtil;
import com.mi.bookvillage.common.domain.User.UserVO;
import com.mi.bookvillage.common.domain.Rental.RentalVO;
import com.mi.bookvillage.user.common.factory.RentalFactory;
import com.mi.bookvillage.user.domain.point.PointService;
import com.mi.bookvillage.user.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * RentalAPI
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;
    private final PointService pointService;
    private static int LATE_FEE = 100;


    /**
     * 대여 목록 조회
     */
    @RequestMapping(value = "/rental/list", method = RequestMethod.GET)
    public ResponseEntity<?> getRentalList(HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");

        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");

        UserVO customer = userService.getCustomerDetailById(customerId);

        List<RentalVO> rentalList = rentalService.getRentalList(customer.getUserSeq());

        return APIResponse.builder().success(rentalList).build();
    }

    /**
     * 대여 내역 조회 : user
     */
    @RequestMapping(value="/rental/detail/{rentalSeq}", method = RequestMethod.GET)
    public ResponseEntity<?> getRentalDetail( @PathVariable int rentalSeq,
                                              HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");
        // --- 토큰 해독
        Map<String, Object> userObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)userObj.get("userId");
        // 남은 포인트 확인
        UserVO user = userService.getCustomerDetailById(customerId);
        // --- customer point GET
        int totalPoint = pointService.getPreviousTotalPoint(user.getUserSeq());
        user.setUserPoint(totalPoint);
        RentalVO rental = rentalService.getRentalDetail(rentalSeq);

        if( rental == null ) {
            return APIResponse.builder().error("해당 대여 내역이 존재하지 않습니다.").build();
        }
        return APIResponse.builder().success("rentalInfo" , rental)
                                    .success("userInfo" , user)
                                    .build();
    }


    /**
     * 대여하기
     */
    @RequestMapping(value = "/rental/book/{bookSeq}" , method = RequestMethod.POST)
    public ResponseEntity<?> rentalBook(  @PathVariable int bookSeq
                                         ,@RequestBody Map<String, Object> rentalInfo
                                         ,HttpServletRequest request) {

        // TODO :  프론트에서 Map<String, Object> rentalInfo 으로 쓰지 않게끔 데이터를 조작해서 넘겨주는 형태로 전환!
        // TODO : Ex) 대여일 - 반납예정일 세팅해서 보내주기( 프론트에서 rentalDayCount 를 받아서 Date 계산한 후에 보내주기 RentalVO 로 )

        // --- header 토큰 GET
        String token = request.getHeader("Authorization");
        // --- 토큰 해독
        Map<String, Object> userObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)userObj.get("userId");
        // 고객 정보 확인
        UserVO customer = userService.getCustomerDetailById(customerId);

        String rentalDayCount = (String)rentalInfo.get("rentalDayCount");
        RentalVO rentalVO = RentalFactory.getRentalInfo(bookSeq, customer.getUserSeq(), rentalDayCount);
        rentalService.rentalBook(rentalVO);

        return APIResponse.builder().success().build();
    }


    /**
     * 대여도서 반납
     */

    // TODO: 새로 만들어야 하는 로직

    @RequestMapping(value = "/rental/book/return/{rentalSeq}" , method = RequestMethod.PUT)
    public ResponseEntity<?> returnBook ( @PathVariable int rentalSeq,
                                          @RequestParam("lateDays") int lateDays,
                                          HttpServletRequest request) {

       int lateFee = lateDays * LATE_FEE;

        // --- header 토큰 GET
        String token = request.getHeader("Authorization");
        // --- 토큰 해독
        Map<String, Object> userObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)userObj.get("userId");
        // 남은 포인트 확인
        UserVO user = userService.getCustomerDetailById(customerId);
        int totalPoint = pointService.getPreviousTotalPoint(user.getUserSeq());


        // 포인트 부족시
        if( lateFee > totalPoint ) {
            return APIResponse.builder().error("포인트가 부족합니다. 포인트 충전 후 이용해주세요.").build();
        }
        // 포인트 있을 경우 포인트 차감 후( 대여비 만큼 연체료 증가 )

        // 렌탈 반납처리

       //  return APIResponse.builder().success().build();
        return null;
    }


}
