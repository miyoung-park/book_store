package com.mi.bookvillage.api;

import com.mi.bookvillage.common.util.point.PointUtil;
import com.mi.bookvillage.model.service.CustomerService;
import com.mi.bookvillage.model.service.PointService;
import com.mi.bookvillage.model.vo.CustomerVO;
import com.mi.bookvillage.model.service.RentalService;
import com.mi.bookvillage.model.vo.PointVO;
import com.mi.bookvillage.model.vo.RentalVO;
import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.common.response.APIResponseBuilderFactory;
import com.mi.bookvillage.common.security.JWTokenUtil;
import com.mi.bookvillage.common.util.rental.RentalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * RentalAPI
 */
@RestController
@Slf4j
public class RentalAPI {

    private RentalService rentalService;
    private CustomerService customerService;
    private PointService pointService;
    private APIResponseBuilderFactory apiResponseBuilderFactory;
    private PointUtil pointUtil;

    public RentalAPI(RentalService rentalService , CustomerService customerService , PointService pointService, PointUtil pointUtil, APIResponseBuilderFactory apiResponseBuilderFactory ){
        this.rentalService = rentalService;
        this.customerService = customerService;
        this.pointService = pointService;
        this.pointUtil = pointUtil;
        this.apiResponseBuilderFactory = apiResponseBuilderFactory;
    }

    /* customer */

    /**
     * 대여 목록 조회
     * @param request
     * @return
     */
    @RequestMapping(value = "/rental/list", method = RequestMethod.GET)
    public APIResponse getRentalList(HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");

        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");
        CustomerVO customer = customerService.getCustomerDetailById(customerId);
        List<RentalVO> rentalList = rentalService.getRentalList(customer.getUserSeq());

        return apiResponseBuilderFactory.success().setData(rentalList).build();
    }


    /**
     * 대여하기 : user
     * @param bookSeq
     * @param rentalInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/rental/book/{bookSeq}" , method = RequestMethod.POST)
    public APIResponse rentalBook( @PathVariable int bookSeq
                                  ,@RequestBody Map<String, Object> rentalInfo
                                  ,HttpServletRequest request) {

        // --- header 토큰 GET
        String token = request.getHeader("Authorization");
        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");
        // 남은 포인트 확인
        CustomerVO customer = customerService.getCustomerDetailById(customerId);

        String rentalDayCount = (String)rentalInfo.get("rentalDayCount");
        RentalVO rentalVO = RentalUtil.getRentalInfo(bookSeq, customer.getUserSeq(), rentalDayCount);
        rentalService.rentalBook(rentalVO);

        return apiResponseBuilderFactory.success().build();
    }

    /* admin */

    /**
     * 고객 대여 목록 조회 : admin   ******** 수정 요망
     * @param request
     * @return
     */
    @RequestMapping(value = "/rental/list/{userSeq}", method = RequestMethod.GET)
    public APIResponse getRentalListBySeq(HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");

        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");
        CustomerVO customer = customerService.getCustomerDetailById(customerId);
        List<RentalVO> rentalList = rentalService.getRentalList(customer.getUserSeq());

        return apiResponseBuilderFactory.success().setData(rentalList).build();
    }

    /**
     * 모든 대여정보 조회 : admin
     * @param request
     * @return
     */
    @RequestMapping(value = "/rental/all-list", method = RequestMethod.GET)
    public APIResponse getRentalAllList(HttpServletRequest request){
        List<RentalVO> rentalList = rentalService.getRentalList(null);
        return apiResponseBuilderFactory.success().setData(rentalList).build();
    }


    /**
     * 대여 승인 : admin
     * @param rentalSeq
     * @param request
     * @return
     */
    @RequestMapping(value = "/rental/approve/{rentalSeq}", method = RequestMethod.PUT)
    public APIResponse approveRental( @PathVariable("rentalSeq") int rentalSeq
                                    , HttpServletRequest request){

        RentalVO rentalVO = RentalUtil.setApproveRental(rentalSeq);
        rentalService.approveRental(rentalVO);

        return apiResponseBuilderFactory.success().build();
    }

    /**
     * 대여 거절 : admin
     * @param rentalSeq
     * @param request
     * @return
     */
    @RequestMapping(value = "/rental/reject/{rentalSeq}" , method = RequestMethod.PUT)
    public APIResponse rejectRental(  @PathVariable("rentalSeq") int rentalSeq
                                    , HttpServletRequest request){

        RentalVO rentalVO = RentalUtil.setRejectRental(rentalSeq);
        rentalService.rejectRental(rentalVO);
        return apiResponseBuilderFactory.success().build();
    }


}
