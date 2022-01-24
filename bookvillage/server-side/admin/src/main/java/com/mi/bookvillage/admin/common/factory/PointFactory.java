package com.mi.bookvillage.admin.common.factory;


import com.mi.bookvillage.admin.domain.book.BookService;
import com.mi.bookvillage.admin.domain.point.PointService;
import com.mi.bookvillage.common.common.exceptions.ApiException;
import com.mi.bookvillage.common.common.exceptions.ApiServiceErrorCode;
import com.mi.bookvillage.common.common.util.string.StringUtil;
import com.mi.bookvillage.common.domain.Book.BookVO;
import com.mi.bookvillage.common.domain.User.UserVO;
import com.mi.bookvillage.common.domain.Point.PointVO;
import com.mi.bookvillage.common.domain.Rental.RentalVO;
import org.springframework.stereotype.Component;

@Component
public class PointFactory {
    static PointService pointService;
    static BookService bookService;


    public PointVO minusPoint(RentalVO rentalVO , String status){

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
            if( previousPoint < Integer.parseInt( bookVO.getBookRentalFee() )){
                throw new ApiException(ApiServiceErrorCode.NOT_ENOUGH_POINT, "포인트 충전 후 이용해주세요.");
            }
            pointVO.setPointTransaction("-" + bookVO.getBookRentalFee());
        } else if ( status == "04" ) { // -- 재적립
            pointVO.setPointTransaction(bookVO.getBookRentalFee());
        }
        return pointVO;
    }

}
