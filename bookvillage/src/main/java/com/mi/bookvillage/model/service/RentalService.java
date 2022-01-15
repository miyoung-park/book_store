package com.mi.bookvillage.model.service;

import com.mi.bookvillage.model.dao.PointDAO;
import com.mi.bookvillage.model.vo.PointVO;
import com.mi.bookvillage.model.dao.RentalDAO;
import com.mi.bookvillage.model.vo.RentalVO;
import com.mi.bookvillage.common.util.point.PointFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private RentalDAO rentalDAO;
    private PointDAO pointDAO;
    private PointFactory pointFactory;
    private static String Code_Rental = "01";
    private static String Code_Reject = "04";

    public RentalService(RentalDAO rentalDAO, PointDAO pointDAO, PointFactory pointFactory){
        this.rentalDAO = rentalDAO;
        this.pointDAO = pointDAO;
        this.pointFactory = pointFactory;
    }

    public List<RentalVO> getRentalList(Integer userSeq){
        List<RentalVO> rentalList = rentalDAO.getRentalList(userSeq);
        return rentalList;
    }


    public RentalVO getRentalDetail(int rentalSeq){
        RentalVO rentalVO =  rentalDAO.getRentalDetail(rentalSeq);
        return rentalVO != null ? rentalVO : null;
    }



    public RentalVO getRentalDetailByBookSeq(int bookSeq){
        RentalVO rentalVO =  rentalDAO.getRentalDetailByBookSeq(bookSeq);
        return rentalVO != null ? rentalVO : null;
    }


    public void rentalBook(RentalVO rentalVO){
        // 대여하기
        rentalDAO.rentalBook(rentalVO);
        // 대여정보 GET
        RentalVO rental = getRentalDetail(rentalVO.getRentalSeq());
        // 대여정보 기준으로 포인트 차감 객체생성
        PointVO pointVO = pointFactory.minusPoint(rental , Code_Rental);
        // 포인트 차감
        pointDAO.transactionPoint(pointVO);
    }


    public void approveRental(RentalVO rentalVO){
        rentalDAO.updateRentalStatus(rentalVO);
    }

    public void rejectRental(RentalVO rentalVO){

        // Rental 내역가지고 와서 원복
        RentalVO rental = getRentalDetail(rentalVO.getRentalSeq());
        PointVO point = pointFactory.minusPoint(rental, Code_Reject);
        // 포인트도 대여취소로 만들어주기
        pointDAO.transactionPoint(point);
        // rental 취소로 바꿔주기
        rentalDAO.updateRentalStatus(rentalVO);
    }


    // TODO: 추가해야 하는 로직
    public void returnRental(RentalVO rentalVO){
        // RENTAL 승인 / 거절 / 반납
        rentalDAO.updateRentalStatus(rentalVO);
    }

    public void updateRentalDays(RentalVO rentalVO){ // 대여하기
        // 반납날짜 늘려주기
    }


}
