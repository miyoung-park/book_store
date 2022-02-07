package com.mi.bookvillage.common.domain.point;

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
    private String statusReason;
    private String pointTransaction;
    private int totalPoint;
    private String transactionRegDt;


}
