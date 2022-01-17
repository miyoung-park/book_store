package com.mi.bookvillage.admin.controller;

import com.mi.bookvillage.common.common.factory.PointFactory;
import com.mi.bookvillage.common.common.factory.RentalFactory;
import com.mi.bookvillage.common.common.response.APIResponse;
import com.mi.bookvillage.common.common.security.JWTokenUtil;
import com.mi.bookvillage.common.vo.CustomerVO;
import com.mi.bookvillage.common.vo.RentalVO;
import com.mi.bookvillage.common.service.PointService;
import com.mi.bookvillage.common.service.RentalService;
import com.mi.bookvillage.common.service.CustomerService;
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

    private RentalService rentalService;
    private CustomerService customerService;
    private PointService pointService;
    private PointFactory pointFactory;

    /* user */

    /**
     * 대여 목록 조회 : user
     * @param request
     * @return
     */
    @RequestMapping(value = "/rental/list", method = RequestMethod.GET)
    public ResponseEntity<?> getRentalList(HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");

        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");

        CustomerVO customer = customerService.getCustomerDetailById(customerId);

        List<RentalVO> rentalList = rentalService.getRentalList(customer.getUserSeq());

        return APIResponse.builder().success(rentalList).build();
    }

    /**
     * 대여 내역 조회 : user
     * @param rentalSeq
     * @return
     */
    @RequestMapping(value="/rental/detail/{rentalSeq}", method = RequestMethod.GET)
    public ResponseEntity<?> getRentalDetail( @PathVariable int rentalSeq){
        RentalVO rentalVO = rentalService.getRentalDetail(rentalSeq);
        if( rentalVO == null ) {
            return APIResponse.builder().error("해당 대여 내역이 존재하지 않습니다.").build();
        }
        return APIResponse.builder().success(rentalVO).build();
    }


    /**
     * 대여하기 : user
     * @param bookSeq
     * @param rentalInfo
     * @param request
     * @return
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
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");
        // 남은 포인트 확인
        CustomerVO customer = customerService.getCustomerDetailById(customerId);

        String rentalDayCount = (String)rentalInfo.get("rentalDayCount");
        RentalVO rentalVO = RentalFactory.getRentalInfo(bookSeq, customer.getUserSeq(), rentalDayCount);
        rentalService.rentalBook(rentalVO);

        return APIResponse.builder().success().build();
    }


    /**
     * 대여도서 반납 --- 도전과제
     * @param rentalSeq
     * @return
     */

    // TODO: 새로 만들어야 하는 로직
    @RequestMapping(value = "/rental/book/return/{rentalSeq}" , method = RequestMethod.PUT)
    public ResponseEntity<?> returnBook ( @PathVariable int rentalSeq ) {

        return APIResponse.builder().success().build();
    }







    /* admin */

    /**
     * 고객 대여 목록 조회 : admin
     * @param request
     * @return
     */
    @RequestMapping(value = "/rental/list/{userSeq}", method = RequestMethod.GET)
    public ResponseEntity<?> getRentalListBySeq( @PathVariable int userSeq
                                                ,HttpServletRequest request){

        List<RentalVO> rentalList = rentalService.getRentalList(userSeq);

        return APIResponse.builder().success(rentalList).build();
    }

    /**
     * 모든 대여정보 조회 : admin
     * @return
     */
    @RequestMapping(value = "/rental/all-list", method = RequestMethod.GET)
    public ResponseEntity<?> getRentalAllList(){
        List<RentalVO> rentalList = rentalService.getRentalList(null);
        return APIResponse.builder().success(rentalList).build();
    }


    /**
     * 대여 승인 : admin
     * @param rentalSeq
     * @return
     */
    @RequestMapping(value = "/rental/approve/{rentalSeq}", method = RequestMethod.PUT)
    public ResponseEntity<?> approveRental( @PathVariable("rentalSeq") int rentalSeq ){

        RentalVO rentalVO = RentalFactory.setApproveRental(rentalSeq);
        rentalService.approveRental(rentalVO);
        return APIResponse.builder().success().build();
    }

    /**
     * 대여 거절 : admin
     * @param rentalSeq
     * @return
     */
    @RequestMapping(value = "/rental/reject/{rentalSeq}" , method = RequestMethod.PUT)
    public ResponseEntity<?> rejectRental(  @PathVariable("rentalSeq") int rentalSeq ){
        RentalVO rentalVO = RentalFactory.setRejectRental(rentalSeq);
        rentalService.rejectRental(rentalVO);

        return APIResponse.builder().success().build();
    }


}
