package com.mi.bookvillage.admin.domain.rental;

import com.mi.bookvillage.admin.common.factory.RentalFactory;
import com.mi.bookvillage.common.common.response.ApiResponse;
import com.mi.bookvillage.common.common.response.ApiResponseBuilderFactory;
import com.mi.bookvillage.common.domain.rental.RentalVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * RentalAPI
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final ApiResponseBuilderFactory apiResponseBuilderFactory;


    /**
     * 모든 대여 목록 조회
     */
    @RequestMapping(value = "/rental/all-list", method = RequestMethod.GET)
    public ApiResponse getRentalAllList(  HttpServletRequest request ){
        List<RentalVO> rentalAllList = rentalService.getAllRentalList();
        return apiResponseBuilderFactory.success().setData(rentalAllList).build();
    }

    /**
     * 고객 대여 목록 조회
     */
    @RequestMapping(value = "/rental/list/{userSeq}", method = RequestMethod.GET)
    public ApiResponse getRentalListBySeq(@PathVariable int userSeq,
                                           HttpServletRequest request){

        List<RentalVO> rentalList = rentalService.getRentalList(userSeq);

        return apiResponseBuilderFactory.success(rentalList).build();
    }




    /**
     * 대여 승인
     */
    @RequestMapping(value = "/rental/approve/{rentalSeq}", method = RequestMethod.PUT)
    public ApiResponse approveRental( @PathVariable("rentalSeq") int rentalSeq ){

        RentalVO rentalVO = RentalFactory.setApproveRental(rentalSeq);
        rentalService.approveRental(rentalVO);
        return apiResponseBuilderFactory.success().build();
    }

    /**
     * 대여 거절
     */
    @RequestMapping(value = "/rental/reject/{rentalSeq}" , method = RequestMethod.PUT)
    public ApiResponse rejectRental(  @PathVariable("rentalSeq") int rentalSeq ){

        RentalVO rentalVO = RentalFactory.setRejectRental(rentalSeq);
        rentalService.rejectRental(rentalVO);

        return apiResponseBuilderFactory.success().build();
    }


}
