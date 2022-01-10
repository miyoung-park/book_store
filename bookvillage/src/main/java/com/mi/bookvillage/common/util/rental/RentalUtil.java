package com.mi.bookvillage.common.util.rental;

import com.mi.bookvillage.rental.model.vo.RentalVO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalUtil {


    public static RentalVO getRentalInfo(int bookSeq , int userSeq, String rentalDayCount){
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



}
