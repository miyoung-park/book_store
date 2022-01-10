package com.mi.bookvillage.rental.model.vo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
public class RentalVO {

    private int rentalSeq;
    private int bookSeq;
    private int userSeq;
    private String rentalDt;
    private String predictReturnDt;
    private String returnDt;
    private String rentalStatus;
    private String rentalRegDt;
    private String rentalUpdateDt;


}
