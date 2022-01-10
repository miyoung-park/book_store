package com.mi.bookvillage.rental.api;

import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.common.response.APIResponseBuilder;
import com.mi.bookvillage.common.response.APIResponseBuilderFactory;
import com.mi.bookvillage.common.security.JWTokenUtil;
import com.mi.bookvillage.common.util.rental.RentalUtil;
import com.mi.bookvillage.customer.model.service.CustomerService;
import com.mi.bookvillage.customer.model.vo.CustomerVO;
import com.mi.bookvillage.rental.model.service.RentalService;
import com.mi.bookvillage.rental.model.vo.RentalVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class RentalAPI {

    private RentalService rentalService;
    private CustomerService customerService;
    private APIResponseBuilderFactory apiResponseBuilderFactory;
    private String token;

    public RentalAPI( RentalService rentalService , CustomerService customerService , APIResponseBuilderFactory apiResponseBuilderFactory ){
        this.rentalService = rentalService;
        this.customerService = customerService;
        this.apiResponseBuilderFactory = apiResponseBuilderFactory;
    }

    @RequestMapping(value = "/rental/list", method = RequestMethod.GET)
    public APIResponse getRentalList(HttpServletRequest request){
        // --- header 토큰 GET
        token = request.getHeader("Authorization");

        // --- 토큰 유효성 검사
        if( token == null || ! JWTokenUtil.checkToken(token)){
            return null; // --- Exception 추가
        }
        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");
         CustomerVO customer = customerService.getCustomerDetailById(customerId);
        List<RentalVO> rentalList = rentalService.getRentalList(customer.getUserSeq());

        return apiResponseBuilderFactory.success().setData(rentalList).build();
    }

    @RequestMapping(value = "/rental/book/{bookSeq}" , method = RequestMethod.POST)
    public APIResponse rentalBook( @PathVariable int bookSeq
                                  ,@RequestBody Map<String, Object> rentalInfo
                                  ,HttpServletRequest request) {

        // --- header 토큰 GET
        token = request.getHeader("Authorization");

        // --- 토큰 유효성 검사
        if( token == null || ! JWTokenUtil.checkToken(token)){
            return null; // --- Exception 추가
        }
        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");
        CustomerVO customer = customerService.getCustomerDetailById(customerId);

        String rentalDayCount = (String)rentalInfo.get("rentalDayCount");
        RentalVO rentalVO = RentalUtil.getRentalInfo(bookSeq, customer.getUserSeq(), rentalDayCount);
        rentalService.rentalBook(rentalVO);

        return apiResponseBuilderFactory.success().build();
    }


}
