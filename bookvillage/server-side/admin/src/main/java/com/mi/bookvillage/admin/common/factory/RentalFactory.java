package com.mi.bookvillage.admin.common.factory;



import com.mi.bookvillage.common.domain.Rental.RentalVO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalFactory {

    private static String Approve_Code = "01";
    private static String Return_Code = "02";
    private static String Reject_Code = "04";


    public static RentalVO getRentalInfo(int bookSeq, int userSeq, String rentalDayCount){
        RentalVO rentalVO = new RentalVO();
        rentalVO.setBookSeq(bookSeq); // --- bookSeq 세팅
        rentalVO.setUserSeq(userSeq); // --- userSeq 세팅
        rentalVO.setPredictReturnDt(getPredictReturnDt(rentalDayCount));  // --- predictReturnDt 세팅
        return rentalVO;
    }

    private static String getPredictReturnDt( String rentalDayCount ){
        // 대여일자 정의
        long dayCount = Long.parseLong(rentalDayCount);
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 현재 날짜 + 대여일자 = 반납예정일 정의
        LocalDate now = LocalDate.now().plusDays(dayCount);
        // 포맷 적용
        String formattedNow = now.format(formatter);
        String predictReturnDt = formattedNow + "235959"; // --- 23시 59분 59초까지 반납가능

        return predictReturnDt;
    }
    // TODO: 들어오는 응답코드에 따라 어떻게 할지 형태로 바꾸기 ?
    public static RentalVO setApproveRental(int rentalSeq){
        RentalVO rentalVO = new RentalVO();
        rentalVO.setRentalSeq(rentalSeq);
        rentalVO.setRentalStatus(Approve_Code);
        return rentalVO;
    }

    public static RentalVO setRejectRental(int rentalSeq){
        RentalVO rentalVO = new RentalVO();
        rentalVO.setRentalSeq(rentalSeq);
        rentalVO.setRentalStatus(Reject_Code);
        return rentalVO;
    }


}
