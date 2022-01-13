package com.mi.bookvillage.model.service;

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
        try{
            AdminVO admin = adminDAO.loginAdmin(adminVo);
            return admin;
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("unmatched password");
            return null;
        }
    }

    public AdminVO getAdminInfo(String userId){
        AdminVO admin = adminDAO.getAdminInfo(userId);
        if(admin == null){
            // Exception
        }
        return admin;
    }
}
