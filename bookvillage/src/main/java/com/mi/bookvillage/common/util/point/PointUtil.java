package com.mi.bookvillage.common.util.point;

import com.mi.bookvillage.book.model.service.BookService;
import com.mi.bookvillage.book.model.vo.BookVO;
import com.mi.bookvillage.customer.model.vo.CustomerVO;
import com.mi.bookvillage.point.model.service.PointService;
import com.mi.bookvillage.point.model.vo.PointVO;
import com.mi.bookvillage.rental.model.vo.RentalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointUtil {
    static PointService pointService;
    static BookService bookService;

    public PointUtil(PointService pointService , BookService bookService){
        this.pointService = pointService;
        this.bookService = bookService;
    }

    public PointVO chargePoint(PointVO pointVO, CustomerVO customerVO){
        pointVO.setUserSeq(customerVO.getUserSeq());
        int previousPoint = pointService.getPreviousTotalPoint(customerVO.getUserSeq());
        pointVO.setPreviousPoint(previousPoint);

        return pointVO;
    }

    public PointVO minusPoint(RentalVO rentalVO){
        PointVO pointVO = new PointVO();
        BookVO bookVO = bookService.getBookDetail(rentalVO.getBookSeq());
        // -- 고객번호 정의
        pointVO.setUserSeq(rentalVO.getUserSeq());
        // -- 대여번호 정의
        pointVO.setRentalSeq(rentalVO.getRentalSeq());
        // -- 이전 포인트 정의
        int previousPoint = pointService.getPreviousTotalPoint(rentalVO.getUserSeq());
        pointVO.setPreviousPoint(previousPoint);
        // -- 거래 상태 정의
        pointVO.setPointStatus("01");
        // -- 사용 포인트 정의
        pointVO.setPointTransaction("-" + bookVO.getBookRentalFee());

        return pointVO;
    }
}
