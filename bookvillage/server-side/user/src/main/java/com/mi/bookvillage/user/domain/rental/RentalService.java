package com.mi.bookvillage.user.domain.rental;


import com.mi.bookvillage.common.domain.Point.PointMapper;
import com.mi.bookvillage.common.domain.Rental.RentalMapper;
import com.mi.bookvillage.common.domain.Point.PointVO;
import com.mi.bookvillage.common.domain.Rental.RentalVO;
import com.mi.bookvillage.user.common.factory.PointUtil;
import com.mi.bookvillage.user.common.factory.RentalFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private static String Code_Rental = "01";
    private static String Code_Return = "02";
    private static String Code_Reject = "04";



    public List<RentalVO> getRentalList(Integer userSeq){
        List<RentalVO> rentalList = rentalMapper.getRentalList(userSeq);
        return rentalList;
    }


    public RentalVO getRentalDetail(int rentalSeq){
        RentalVO rentalVO =  rentalMapper.getRentalDetail(rentalSeq);
        return rentalVO != null ? rentalVO : null;
    }


    public void rentalBook(RentalVO rentalVO) throws ParseException {
        // 대여하기
        rentalMapper.rentalBook(rentalVO);
        // 대여정보 GET
        RentalVO rental = getRentalDetail(rentalVO.getRentalSeq());
        // 대여정보 기준으로 포인트 차감 객체생성
        PointVO pointVO = pointUtil.minusPoint(rental , Code_Rental);
        // 포인트 차감
        pointMapper.transactionPoint(pointVO);
    }

    public void returnBook(RentalVO rentalVO) throws ParseException {
        // 정보 확인 했을 때 연체료가 있는 경우 연체료 청구 -> PointFactory
        // Code_Late = '03';

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date predictReturnDate = format.parse(rentalVO.getPredictReturnDt());
        int diff = (int)( now.getTime() - predictReturnDate.getTime() ) / ( 24 * 60 * 60 * 1000 ); // 연체기간

        // 연체 된 경우
        if( diff > 0 ) {
            PointVO point = pointUtil.minusPoint(rentalVO, "03");
            pointMapper.transactionPoint(point);
        }
        // 연체 안된 경우
        RentalVO rental = RentalFactory.setReturnRental(rentalVO.getRentalSeq());
        rentalMapper.updateRentalStatus(rental);
    }

}
