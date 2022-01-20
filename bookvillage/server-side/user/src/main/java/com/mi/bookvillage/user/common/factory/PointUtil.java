package com.mi.bookvillage.user.common.factory;


import com.mi.bookvillage.common.common.exceptions.customException.NotEnoughPointException;
import com.mi.bookvillage.common.domain.Book.BookVO;
import com.mi.bookvillage.common.domain.User.UserVO;
import com.mi.bookvillage.common.domain.Point.PointVO;
import com.mi.bookvillage.common.domain.Rental.RentalVO;
import com.mi.bookvillage.user.domain.book.BookService;
import com.mi.bookvillage.user.domain.point.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class PointUtil {
    private final PointService pointService;
    private final BookService bookService;
    private static int LATE_FEE = 100;


    public PointVO chargePoint(PointVO pointVO, UserVO userVO){
        /* validation 추가 */
        pointVO.setUserSeq(userVO.getUserSeq());
        int previousPoint = pointService.getPreviousTotalPoint(userVO.getUserSeq());
        pointVO.setPreviousPoint(previousPoint);

        return pointVO;
    }


    public PointVO minusPoint(RentalVO rentalVO , String status) throws ParseException {
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

        // -- 연체료 계산
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date predictReturnDate = format.parse(rentalVO.getPredictReturnDt());
        int diff = (int)( now.getTime() - predictReturnDate.getTime() ) / ( 24 * 60 * 60 * 1000 ); // 연체기간

        // 연체 된 경우
        if( diff > 0 ) {
            int totalLateFee = diff * LATE_FEE;
            // 포인트가 부족한 경우
            if( previousPoint < totalLateFee ) {
                throw new NotEnoughPointException("포인트가 부족합니다. 충전 후 반납을 진행해주세요.");
            }
            // 포인트가 있는 경우
            // -- 사용 포인트 정의
            pointVO.setPointTransaction( "-" + totalLateFee );
        }
        // 연체 안된 경우
        // -- 사용 포인트 정의
        if(status == "01") { // -- 차감
            pointVO.setPointTransaction("-" + bookVO.getBookRentalFee());
        } else if ( status == "04" ) { // -- 재적립
            pointVO.setPointTransaction(bookVO.getBookRentalFee());
        }
        return pointVO;
    }

}
