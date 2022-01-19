package com.mi.bookvillage.common.domain.Admin;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    AdminVO loginAdmin(AdminVO adminVO);
    AdminVO getAdminInfo(String userId);

}
