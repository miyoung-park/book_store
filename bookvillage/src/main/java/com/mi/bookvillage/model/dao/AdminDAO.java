package com.mi.bookvillage.model.dao;

import com.mi.bookvillage.model.vo.AdminVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDAO {
    AdminVO loginAdmin(AdminVO adminVO);
    AdminVO getAdminInfo(String userId);
}
