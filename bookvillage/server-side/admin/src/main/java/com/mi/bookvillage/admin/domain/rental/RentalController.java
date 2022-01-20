package com.mi.bookvillage.admin.domain.rental;

import com.mi.bookvillage.admin.common.factory.RentalFactory;
import com.mi.bookvillage.admin.domain.user.UserService;
import com.mi.bookvillage.common.common.response.APIResponse;
import com.mi.bookvillage.common.common.security.JWTokenUtil;
import com.mi.bookvillage.common.domain.User.UserVO;
import com.mi.bookvillage.common.domain.Rental.RentalVO;
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


    /**
     * 고객 대여 목록 조회
     */
    @RequestMapping(value = "/rental/list/{userSeq}", method = RequestMethod.GET)
    public ResponseEntity<?> getRentalListBySeq( @PathVariable int userSeq){

        List<RentalVO> rentalList = rentalService.getRentalList(userSeq);

        return APIResponse.builder().success(rentalList).build();
    }

    /**
     * 모든 대여정보 조회
     */
    @RequestMapping(value = "/rental/all-list", method = RequestMethod.GET)
    public ResponseEntity<?> getRentalAllList(){
        List<RentalVO> rentalList = rentalService.getRentalList(null);
        return APIResponse.builder().success(rentalList).build();
    }


    /**
     * 대여 승인
     */
    @RequestMapping(value = "/rental/approve/{rentalSeq}", method = RequestMethod.PUT)
    public ResponseEntity<?> approveRental( @PathVariable("rentalSeq") int rentalSeq ){

        RentalVO rentalVO = RentalFactory.setApproveRental(rentalSeq);
        rentalService.approveRental(rentalVO);
        return APIResponse.builder().success().build();
    }

    /**
     * 대여 거절
     */
    @RequestMapping(value = "/rental/reject/{rentalSeq}" , method = RequestMethod.PUT)
    public ResponseEntity<?> rejectRental(  @PathVariable("rentalSeq") int rentalSeq ){
        RentalVO rentalVO = RentalFactory.setRejectRental(rentalSeq);
        rentalService.rejectRental(rentalVO);

        return APIResponse.builder().success().build();
    }


}
