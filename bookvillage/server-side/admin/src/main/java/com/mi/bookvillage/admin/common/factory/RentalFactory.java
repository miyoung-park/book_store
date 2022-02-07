package com.mi.bookvillage.admin.common.factory;

import com.mi.bookvillage.common.domain.rental.RentalVO;


public class RentalFactory {

    private static String Approve_Code = "01";
    private static String Reject_Code = "04";


    // TODO: 다시 바꿔보기
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
