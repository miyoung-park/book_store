package com.mi.bookvillage.user.domain.rental;


import com.mi.bookvillage.common.mapper.PointMapper;
import com.mi.bookvillage.common.mapper.RentalMapper;
import com.mi.bookvillage.common.vo.PointVO;
import com.mi.bookvillage.common.vo.RentalVO;
import com.mi.bookvillage.user.common.factory.PointFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RentalService {

    private RentalMapper rentalMapper;
    private PointMapper pointMapper;
    private PointFactory pointFactory;
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



    public RentalVO getRentalDetailByBookSeq(int bookSeq){
        RentalVO rentalVO =  rentalMapper.getRentalDetailByBookSeq(bookSeq);
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


    public void approveRental(RentalVO rentalVO){
        rentalMapper.updateRentalStatus(rentalVO);
    }

    public void rejectRental(RentalVO rentalVO){

        // Rental 내역가지고 와서 원복
        RentalVO rental = getRentalDetail(rentalVO.getRentalSeq());
        PointVO point = pointFactory.minusPoint(rental, Code_Reject);
        // 포인트도 대여취소로 만들어주기
        pointMapper.transactionPoint(point);
        // rental 취소로 바꿔주기
        rentalMapper.updateRentalStatus(rentalVO);
    }


    // TODO: 추가해야 하는 로직
    public void returnRental(RentalVO rentalVO){
        // RENTAL 승인 / 거절 / 반납
        rentalMapper.updateRentalStatus(rentalVO);
    }

    public void updateRentalDays(RentalVO rentalVO){ // 대여하기
        // 반납날짜 늘려주기
    }


}
