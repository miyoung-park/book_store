package com.mi.bookvillage.admin.domain.admin;


import com.mi.bookvillage.common.common.exceptions.customException.InvalidPasswordException;
import com.mi.bookvillage.common.domain.Admin.AdminMapper;
import com.mi.bookvillage.common.domain.Admin.AdminVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    
    public AdminVO loginAdmin(AdminVO adminVo){
        AdminVO admin = adminMapper.loginAdmin(adminVo);
        if( admin == null ){
            throw new InvalidPasswordException();
        }
        return admin;
    }

    public AdminVO getAdminInfo(String userId){
        AdminVO admin = adminMapper.getAdminInfo(userId);
        if(admin == null){
            throw new InvalidPasswordException();
        }
        return admin;
    }
}
