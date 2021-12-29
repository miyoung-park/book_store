package com.mi.bookvillage.book.model.vo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mi.bookvillage.common.util.file.FileVO;
import lombok.*;
import org.springframework.util.MultiValueMap;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
