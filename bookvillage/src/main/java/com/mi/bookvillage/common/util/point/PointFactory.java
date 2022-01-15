package com.mi.bookvillage.common.util.point;

import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.model.service.BookService;
import com.mi.bookvillage.model.service.PointService;
import com.mi.bookvillage.model.vo.BookVO;
import com.mi.bookvillage.model.vo.CustomerVO;
import com.mi.bookvillage.model.vo.PointVO;
import com.mi.bookvillage.model.vo.RentalVO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PointFactory {
    static PointService pointService;
    static BookService bookService;

    public PointFactory(PointService pointService , BookService bookService){
        this.pointService = pointService;
        this.bookService = bookService;
    }

    public PointVO chargePoint(PointVO pointVO, CustomerVO customerVO){
        /* validation 추가 */
        pointVO.setUserSeq(customerVO.getUserSeq());
        int previousPoint = pointService.getPreviousTotalPoint(customerVO.getUserSeq());
        pointVO.setPreviousPoint(previousPoint);

        return pointVO;
    }


    public PointVO minusPoint(RentalVO rentalVO , String status){
        /* validation 추가 */

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
        pointVO.setPointStatus(status);
        // -- 사용 포인트 정의
        if(status == "01") { // -- 차감
            pointVO.setPointTransaction("-" + bookVO.getBookRentalFee());
        } else if ( status == "04" ) { // -- 재적립
            pointVO.setPointTransaction(bookVO.getBookRentalFee());
        }
        return pointVO;
    }

}
