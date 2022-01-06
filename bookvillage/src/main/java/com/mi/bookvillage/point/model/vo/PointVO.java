package com.mi.bookvillage.point.model.vo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
public class PointVO {

    private int pointSeq;
    private int userSeq;
    private int rentalSeq;
    private int previousPoint;
    private String pointStatus;
    private int pointTransaction;
    private int totalPoint;
    private String transactionRegDt;
    private String transactionUpdateDt;


}
