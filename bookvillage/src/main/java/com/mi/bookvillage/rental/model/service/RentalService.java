package com.mi.bookvillage.rental.model.service;

import com.mi.bookvillage.common.util.point.PointUtil;
import com.mi.bookvillage.point.model.dao.PointDAO;
import com.mi.bookvillage.point.model.vo.PointVO;
import com.mi.bookvillage.rental.model.dao.RentalDAO;
import com.mi.bookvillage.rental.model.vo.RentalVO;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class RentalService {

    private RentalDAO rentalDAO;
    private PointDAO pointDAO;
    private PointUtil pointUtil;

    public RentalService(RentalDAO rentalDAO, PointDAO pointDAO, PointUtil pointUtil){
        this.rentalDAO = rentalDAO;
        this.pointDAO = pointDAO;
        this.pointUtil = pointUtil;
    }

    public List<RentalVO> getRentalList(Integer userSeq){
        List<RentalVO> rentalList = rentalDAO.getRentalList(userSeq);
        return rentalList;
    }

    public void rentalBook(RentalVO rentalVO){
        rentalDAO.rentalBook(rentalVO);
    }

    public RentalVO getRentalDetail(int rentalSeq){
        RentalVO rentalVO =  rentalDAO.getRentalDetail(rentalSeq);
        return rentalVO != null ? rentalVO : null;
    }

    public RentalVO getRentalDetailByBookSeq(int bookSeq){
        RentalVO rentalVO =  rentalDAO.getRentalDetailByBookSeq(bookSeq);
        return rentalVO != null ? rentalVO : null;
    }


    public void approveRental(RentalVO rental){
        // rental 승인
        rentalDAO.approveRental(rental);
        // 대여정보 GET
        RentalVO rentalVO = getRentalDetail(rental.getRentalSeq());
        // 대여정보 기준으로 포인트 차감 객체생성
        PointVO pointVO = pointUtil.minusPoint(rentalVO);
        // 포인트 차감
        pointDAO.transactionPoint(pointVO);

    }


}
