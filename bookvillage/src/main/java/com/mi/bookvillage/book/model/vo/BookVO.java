package com.mi.bookvillage.book.model.vo;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookVO {

    @Getter @Setter
    private int bookSeq;

    @Getter @Setter
    private String bookTitle;

    @Getter @Setter
    private String bookPrice;

    @Getter @Setter
    private String bookRentalFee;

    @Getter @Setter
    private String bookMemo;

    @Getter @Setter
    private String bookRegDt;


}
