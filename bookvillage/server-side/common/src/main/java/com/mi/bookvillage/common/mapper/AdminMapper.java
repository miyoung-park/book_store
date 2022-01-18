package com.mi.bookvillage.common.mapper;


import com.mi.bookvillage.common.vo.AdminVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    AdminVO loginAdmin(AdminVO adminVO);
    AdminVO getAdminInfo(String userId);

}
