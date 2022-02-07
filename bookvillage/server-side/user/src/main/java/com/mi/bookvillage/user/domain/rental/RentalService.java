package com.mi.bookvillage.user.domain.rental;


import com.mi.bookvillage.common.common.exceptions.ApiException;
import com.mi.bookvillage.common.common.exceptions.ApiServiceErrorCode;
import com.mi.bookvillage.common.domain.point.PointMapper;
import com.mi.bookvillage.common.domain.rental.RentalMapper;
import com.mi.bookvillage.common.domain.point.PointVO;
import com.mi.bookvillage.common.domain.rental.RentalVO;
import com.mi.bookvillage.user.common.PointUtil;
import com.mi.bookvillage.user.common.RentalFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalMapper rentalMapper;
    private final PointMapper pointMapper;
    private final PointUtil pointUtil;
    private static String CODE_RENTAL = "01";
    private static String CODE_LATE ="03";


    /**
     * 대여 목록 확인
     */
    public List<RentalVO> getRentalList(int userSeq){
        List<RentalVO> rentalList = rentalMapper.getRentalList(userSeq);
        return rentalList;
    }

    /**
     * 대여 정보 확인
     */
    public RentalVO getRentalDetail(int rentalSeq){
        RentalVO rental =  rentalMapper.getRentalDetail(rentalSeq);
        if( rental == null ) {
            throw new ApiException(ApiServiceErrorCode.DATA_NOT_FOUND, "해당 대여 내역이 존재하지 않습니다.");
        }
        return rental;
    }

    /**
     * 도서 대여
     */
    @Transactional(rollbackFor = Exception.class)
    public void rentalBook(RentalVO rentalVO) throws ParseException {
        // 대여하기
        rentalMapper.rentalBook(rentalVO);
        // 대여정보 GET
        RentalVO rental = getRentalDetail(rentalVO.getRentalSeq());
        // 대여정보 기준으로 포인트 차감 객체생성
        PointVO pointVO = pointUtil.getMinusPointInstance(rental , PointUtil.pointStatusCode.RENTAL_MINUS_POINT);
        // 포인트 차감
        pointMapper.transactionPoint(pointVO);
    }

    /**
     * 대여도서 반납
     */
    @Transactional(rollbackFor = Exception.class)
    public void returnBook(RentalVO rentalVO) throws ParseException {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date predictReturnDate = format.parse(rentalVO.getPredictReturnDt());
        int diff = (int)( now.getTime() - predictReturnDate.getTime() ) / ( 24 * 60 * 60 * 1000 ); // 연체기간

        // 연체 된 경우
        if( diff > 0 ) {
            PointVO point = pointUtil.getMinusPointInstance( rentalVO, PointUtil.pointStatusCode.LATE_FEE_MINUS_POINT );
            pointMapper.transactionPoint(point);
        }
        // 연체 안된 경우
        RentalVO rental = RentalFactory.setReturnRental(rentalVO.getRentalSeq());
        rentalMapper.returnBook(rental);
    }

}
