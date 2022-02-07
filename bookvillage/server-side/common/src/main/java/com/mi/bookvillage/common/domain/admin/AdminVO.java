package com.mi.bookvillage.common.domain.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class AdminVO {

    private int userSeq;
    private String userId;
    private String userPw;
    private String userName;
    private String userRole;


}
