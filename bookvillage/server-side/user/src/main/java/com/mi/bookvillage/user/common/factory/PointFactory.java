package com.mi.bookvillage.user.common.factory;


import com.mi.bookvillage.common.vo.BookVO;
import com.mi.bookvillage.common.vo.CustomerVO;
import com.mi.bookvillage.common.vo.PointVO;
import com.mi.bookvillage.common.vo.RentalVO;
import com.mi.bookvillage.user.domain.book.BookService;
import com.mi.bookvillage.user.domain.point.PointService;
import org.springframework.stereotype.Component;

@Component
public class PointFactory {
    static PointService pointService;
    static BookService bookService;


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
