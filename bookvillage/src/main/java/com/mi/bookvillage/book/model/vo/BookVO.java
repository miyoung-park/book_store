package com.mi.bookvillage.book.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BookVO {

    private int bookSeq;
    private String bookTitle;
    private String bookPrice;
    private String bookRentalFee;
    private int bookRentalPeriod;
    private String bookMemo;


}
