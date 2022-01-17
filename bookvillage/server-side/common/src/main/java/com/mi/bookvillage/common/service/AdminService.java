package com.mi.bookvillage.common.service;


import com.mi.bookvillage.common.common.exceptions.customException.InvalidPasswordException;
import com.mi.bookvillage.common.common.util.string.StringUtil;
import com.mi.bookvillage.common.mapper.AdminDAO;
import com.mi.bookvillage.common.vo.AdminVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminDAO adminDAO;
    
    public AdminVO loginAdmin(AdminVO adminVo){
        System.out.println("adminVo : " + adminVo);
        AdminVO admin = adminDAO.loginAdmin(adminVo);
        System.out.println(admin);
        if( admin == null ){
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
