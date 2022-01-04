package com.mi.bookvillage.customer.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerVO {

    @Getter @Setter
    private int userSeq;

    @Getter @Setter
    private String userId;

    @Getter @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPw;

    @Getter @Setter
    private String userName;

    @Getter @Setter
    private String userBirth;

    @Getter @Setter
    private String userTell;

    @Getter @Setter
    private String userRegDt;

    @Getter @Setter
    private String userUpdateDt;

    @Getter @Setter
    private String userRole;


}
