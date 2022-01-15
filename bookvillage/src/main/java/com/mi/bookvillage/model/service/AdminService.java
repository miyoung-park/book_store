package com.mi.bookvillage.model.service;

import com.mi.bookvillage.common.eum.ErrorCode;
import com.mi.bookvillage.common.exceptions.customException.InvalidPasswordException;
import com.mi.bookvillage.common.util.string.StringUtil;
import com.mi.bookvillage.model.dao.AdminDAO;
import com.mi.bookvillage.model.vo.AdminVO;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private AdminDAO adminDAO;
    public AdminService( AdminDAO adminDAO ){
        this.adminDAO = adminDAO;
    }
    
    public AdminVO loginAdmin(AdminVO adminVo){

        AdminVO admin = adminDAO.loginAdmin(adminVo);

        if( !StringUtil.isEmpty(admin) ){
            throw new InvalidPasswordException();
        }
        return admin;
    }

    public AdminVO getAdminInfo(String userId){
        AdminVO admin = adminDAO.getAdminInfo(userId);
        if(admin == null){
            throw new InvalidPasswordException();
        }
        return admin;
    }
}
