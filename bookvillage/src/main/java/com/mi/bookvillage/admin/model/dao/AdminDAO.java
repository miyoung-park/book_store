package com.mi.bookvillage.admin.model.dao;

import com.mi.bookvillage.admin.model.vo.AdminVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDAO {


    void loginAdmin(AdminVO adminVO);
}
