package com.mi.bookvillage.common.vo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
public class RentalVO {

    private int rentalSeq;
    private int bookSeq;
    private String bookTitle;
    private int userSeq;
    private String rentalDt;
    private String predictReturnDt;
    private String returnDt;
    private String rentalStatus;
    private String rentalRegDt;
    private String rentalUpdateDt;


}
