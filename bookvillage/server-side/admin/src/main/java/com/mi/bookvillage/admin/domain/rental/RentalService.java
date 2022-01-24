package com.mi.bookvillage.admin.domain.rental;

import com.mi.bookvillage.admin.common.factory.PointFactory;
import com.mi.bookvillage.common.domain.Point.PointMapper;
import com.mi.bookvillage.common.domain.Rental.RentalMapper;
import com.mi.bookvillage.common.domain.Point.PointVO;
import com.mi.bookvillage.common.domain.Rental.RentalVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalMapper rentalMapper;
    private final PointMapper pointMapper;
    private final PointFactory pointFactory;
    private static String Code_Reject = "04";


    public List<RentalVO> getAllRentalList() {
        List<RentalVO> rentalList = rentalMapper.getAllRentalList();
        return rentalList;
    }

    public List<RentalVO> getRentalList(Integer userSeq){
        List<RentalVO> rentalList = rentalMapper.getRentalList(userSeq);
        return rentalList;
    }


    public RentalVO getRentalDetail(int rentalSeq){
        RentalVO rentalVO =  rentalMapper.getRentalDetail(rentalSeq);
        return rentalVO != null ? rentalVO : null;
    }


    public void approveRental(RentalVO rentalVO){
        rentalMapper.updateRentalStatus(rentalVO);
    }

    public void rejectRental(RentalVO rentalVO){
        // Rental 내역가지고 와서 원복
        RentalVO rental = getRentalDetail(rentalVO.getRentalSeq());
        PointVO point = pointFactory.minusPoint(rental, Code_Reject);

        System.out.println(point);
        // 포인트도 대여취소로 만들어주기
        pointMapper.transactionPoint(point);
        // rental 취소로 바꿔주기
        rentalMapper.updateRentalStatus(rentalVO);
    }


    // TODO: 추가해야 하는 로직
    public void updateRentalDays(RentalVO rentalVO){ // 대여하기
        // 반납날짜 늘려주기
    }


}
