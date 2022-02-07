package com.mi.bookvillage.user.domain.rental;

import com.mi.bookvillage.common.common.response.ApiResponse;
import com.mi.bookvillage.common.common.response.ApiResponseBuilderFactory;
import com.mi.bookvillage.common.common.security.JWTokenUtil;
import com.mi.bookvillage.common.domain.User.UserVO;
import com.mi.bookvillage.common.domain.Rental.RentalVO;
import com.mi.bookvillage.user.domain.point.PointService;
import com.mi.bookvillage.user.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

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
    private final ApiResponseBuilderFactory apiResponseBuilderFactory;

    /**
     * 대여 목록 조회
     */
    @RequestMapping(value = "/rental/list", method = RequestMethod.GET)
    public ApiResponse getRentalListBySeq( HttpServletRequest request ){

        String userId = JWTokenUtil.getUserIdFromToken(request);
        UserVO user = userService.getUserDetailById(userId);

        List<RentalVO> rentalList = rentalService.getRentalList(user.getUserSeq());

        return apiResponseBuilderFactory.success(rentalList).build();
    }

    /**
     * 대여 내역 조회
     */
    @RequestMapping(value="/rental/detail/{rentalSeq}", method = RequestMethod.GET)
    public ApiResponse getRentalDetail(@PathVariable int rentalSeq,
                                       HttpServletRequest request){
        // 고객정보 확인
        String userId = JWTokenUtil.getUserIdFromToken(request);
        // 남은 포인트 확인
        UserVO user = userService.getUserDetailById(userId);
        int totalPoint = pointService.getPreviousTotalPoint(user.getUserSeq());
        user.setUserPoint(totalPoint);

        RentalVO rental = rentalService.getRentalDetail(rentalSeq);

        return apiResponseBuilderFactory.success()
                                        .putValue("rentalInfo" , rental)
                                        .putValue("userInfo" , user)
                                        .build();
    }


    /**
     * 대여하기
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/rental/book/{bookSeq}" , method = RequestMethod.POST)
    public ApiResponse rentalBook( @PathVariable int bookSeq,
                                   @RequestBody RentalVO rental,
                                   HttpServletRequest request) throws ParseException {

        String userId = JWTokenUtil.getUserIdFromToken( request );
        // 고객 정보 확인
        UserVO user = userService.getUserDetailById(userId);

        rental.setBookSeq(bookSeq);
        rental.setUserSeq(user.getUserSeq());
        System.out.println( rental );


        // TODO : 프론트에서 Map<String, Object> rentalInfo 으로 쓰지 않게끔 데이터를 조작해서 넘겨주는 형태로 전환! (완)
        rentalService.rentalBook(rental);

        return apiResponseBuilderFactory.success().build();
    }


    /**
     * 대여도서 반납
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/rental/book/return" , method = RequestMethod.PUT)
    public ApiResponse returnBook ( @RequestBody RentalVO rental ) throws ParseException {
       rentalService.returnBook(rental);
       return apiResponseBuilderFactory.success().build();
    }


}
