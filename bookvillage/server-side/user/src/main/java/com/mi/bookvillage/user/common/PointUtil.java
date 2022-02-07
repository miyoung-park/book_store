package com.mi.bookvillage.user.common;


import com.mi.bookvillage.common.common.exceptions.ApiException;
import com.mi.bookvillage.common.common.exceptions.ApiServiceErrorCode;
import com.mi.bookvillage.common.domain.book.BookVO;
import com.mi.bookvillage.common.domain.point.PointVO;
import com.mi.bookvillage.common.domain.rental.RentalVO;
import com.mi.bookvillage.user.domain.book.BookService;
import com.mi.bookvillage.user.domain.point.PointService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class PointUtil {

    private final PointService pointService;
    private final BookService bookService;
    private static int LATE_FEE = 100;

    public PointVO getMinusPointInstance( RentalVO rental , pointStatusCode statusCode ) throws ParseException {

        PointVO point = new PointVO();

        /** status code 에 따라 switch - case */
        switch ( statusCode ) {
            case RENTAL_MINUS_POINT :
                point = minusRentalPoint( rental ,statusCode );
                break;
            case LATE_FEE_MINUS_POINT :
                point = minusLateFeePoint( rental ,statusCode );
                break;
        }

        return point;
    }

    /**
     * 도서 대여 시, 차감 포인트 객체
     */
    private PointVO minusRentalPoint ( RentalVO rental , pointStatusCode statusCode ) throws ParseException {

        BookVO book = bookService.getBookDetail(rental.getBookSeq());

        /** 기본적인 point 정보 세팅 */
        PointVO point = setPointBasicInfo( rental, statusCode );

        /** 대여일 계산 */
        Calendar getToday = Calendar.getInstance();
        getToday.setTime(new Date()); // 오늘 날짜
        String predictReturnDt = rental.getPredictReturnDt(); // 특정 일자
        Date parsedPredictReturnDt = new SimpleDateFormat("yyyy-MM-dd").parse( predictReturnDt );
        Calendar cmpDate = Calendar.getInstance();
        cmpDate.setTime( parsedPredictReturnDt ); // parsed 특정 일자
        long diffDays = ( ( getToday.getTimeInMillis() - cmpDate.getTimeInMillis()) / 1000 ) / (24*60*60) ; // 일자 수 차이

        /** 대여 포인트 계산 */
        int transactionPoint = ( int )diffDays * Integer.parseInt( book.getBookRentalFee() ); // 대여일 만큼 포인트 계산
        point.setPointTransaction( String.valueOf( transactionPoint ) );


        /** 보유 포인트 < 거래 포인트 */
        if( point.getPreviousPoint() < transactionPoint ){
            throw new ApiException(ApiServiceErrorCode.NOT_ENOUGH_POINT, "포인트가 부족합니다. 포인트 충전 후 다시 대여해주세요.");
        }
        point.setPointTransaction("-" + transactionPoint);

        return point;
    }




    /**
     * 도서 반납 시, 연체료 차감 포인트 객체
     */
    private PointVO minusLateFeePoint ( RentalVO rental , pointStatusCode statusCode ) throws ParseException {

        /** 기본적인 point 정보 세팅 */
        PointVO point = setPointBasicInfo( rental, statusCode );

        /** 연체료 계산 */
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date predictReturnDate = format.parse(rental.getPredictReturnDt());
        int diffDay = (int)( now.getTime() - predictReturnDate.getTime() ) / ( 24 * 60 * 60 * 1000 ); // 연체기간

        /** 연체료 납부 가능 유무 확인 */
        if( diffDay > 0 ) {
            int totalLateFee = diffDay * LATE_FEE;
            // 포인트가 부족한 경우
            if( point.getPreviousPoint() < totalLateFee ) {
                throw new ApiException(ApiServiceErrorCode.NOT_ENOUGH_POINT, "포인트가 부족합니다. 충전 후 반납을 진행해주세요.");
            }
            // 포인트가 있는 경우 ( 차감 포인트 정의 )
            point.setPointTransaction( "-" + totalLateFee );
        }
        return point;
    }


    /**
     * 포인트 객체 기본정보 세팅
     */
    private PointVO setPointBasicInfo( RentalVO rental , pointStatusCode statusCode ) {

        PointVO point = new PointVO();

        /** 고객번호 정의 */
        point.setUserSeq( rental.getUserSeq() );

        /** 대여번호 정의 */
        point.setRentalSeq( rental.getRentalSeq() );

        /** 이전 포인트 정의 */
        int previousPoint = pointService.getPreviousTotalPoint(rental.getUserSeq()); // 이전 포인트 정의
        point.setPreviousPoint(previousPoint);

        /** 거래 상태 정의 */
        point.setPointStatus( statusCode.getStatusCode() );

        /** 거래 사유 정의 */
        point.setStatusReason( statusCode.getMessage() );

        return point;
    }



    public enum pointStatusCode {
         RENTAL_MINUS_POINT("01", "도서 대여")
        ,LATE_FEE_MINUS_POINT("01", "연체료 차감");

        @Getter
        private final String statusCode;
        @Getter
        private final String message;


        pointStatusCode(String statusCode, String message) {
            this.statusCode = statusCode;
            this.message = message;
        }

    }

}
