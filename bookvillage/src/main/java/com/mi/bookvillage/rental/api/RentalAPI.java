package com.mi.bookvillage.rental.api;

import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.common.security.JWTokenUtil;
import com.mi.bookvillage.customer.model.service.CustomerService;
import com.mi.bookvillage.customer.model.vo.CustomerVO;
import com.mi.bookvillage.rental.model.service.RentalService;
import com.mi.bookvillage.rental.model.vo.RentalVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class RentalAPI {

    @Autowired
    RentalService rentalService;
    private CustomerService customerService;
    private String token;

    public RentalAPI( CustomerService customerService ){
        this.customerService = customerService;
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


        return null;
    }


}
