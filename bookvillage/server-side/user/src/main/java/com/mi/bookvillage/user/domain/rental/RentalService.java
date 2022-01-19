package com.mi.bookvillage.user.domain.rental;


import com.mi.bookvillage.common.domain.Point.PointMapper;
import com.mi.bookvillage.common.domain.Rental.RentalMapper;
import com.mi.bookvillage.common.domain.Point.PointVO;
import com.mi.bookvillage.common.domain.Rental.RentalVO;
import com.mi.bookvillage.user.common.factory.PointFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalMapper rentalMapper;
    private final PointMapper pointMapper;
    private final PointFactory pointFactory;
    private static String Code_Rental = "01";
    private static String Code_Reject = "04";


    public List<RentalVO> getRentalList(Integer userSeq){
        List<RentalVO> rentalList = rentalMapper.getRentalList(userSeq);
        return rentalList;
    }


    public RentalVO getRentalDetail(int rentalSeq){
        RentalVO rentalVO =  rentalMapper.getRentalDetail(rentalSeq);
        return rentalVO != null ? rentalVO : null;
    }



    public void rentalBook(RentalVO rentalVO){
        // 대여하기
        rentalMapper.rentalBook(rentalVO);
        // 대여정보 GET
        RentalVO rental = getRentalDetail(rentalVO.getRentalSeq());
        // 대여정보 기준으로 포인트 차감 객체생성
        PointVO pointVO = pointFactory.minusPoint(rental , Code_Rental);
        // 포인트 차감
        pointMapper.transactionPoint(pointVO);
    }


}
