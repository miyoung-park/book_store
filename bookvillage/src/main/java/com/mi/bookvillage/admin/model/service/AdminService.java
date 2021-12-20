package com.mi.bookvillage.admin.model.service;

import com.mi.bookvillage.admin.model.dao.AdminDAO;
import com.mi.bookvillage.admin.model.vo.AdminVO;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private AdminDAO adminDAO;
    public AdminService( AdminDAO adminDAO ){
        this.adminDAO = adminDAO;
    }
    
    public AdminVO loginAdmin(AdminVO adminVo){
        try{
            adminDAO.loginAdmin(adminVo);
            return
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("unmatched password");
        }
    }
}
