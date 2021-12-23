package com.mi.bookvillage.customer.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter
@NoArgsConstructor @ToString
public class CustomerVO {


    private int userSeq;
    private String userId;
    private String userPw;
    private String userName;
    private String userBirth;
    private String userTell;
    private String userRegDt;
    private String userUpdateDt;
    private String userRole;


}
