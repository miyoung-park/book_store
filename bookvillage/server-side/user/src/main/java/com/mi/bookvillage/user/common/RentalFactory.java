package com.mi.bookvillage.user.common;



import com.mi.bookvillage.common.domain.rental.RentalVO;

public class RentalFactory {

    private static String Return_Code = "02";


    public static RentalVO setReturnRental(int rentalSeq){
        RentalVO rentalVO = new RentalVO();
        rentalVO.setRentalSeq(rentalSeq);
        rentalVO.setRentalStatus(Return_Code);
        return rentalVO;
    }


}
