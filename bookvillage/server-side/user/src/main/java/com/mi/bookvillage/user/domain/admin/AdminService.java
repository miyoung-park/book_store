package com.mi.bookvillage.user.domain.admin;


import com.mi.bookvillage.common.common.exceptions.customException.InvalidPasswordException;
import com.mi.bookvillage.common.mapper.AdminMapper;
import com.mi.bookvillage.common.vo.AdminVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    
    public AdminVO loginAdmin(AdminVO adminVo){
        System.out.println("adminVo : " + adminVo);
        AdminVO admin = adminMapper.loginAdmin(adminVo);
        System.out.println(admin);
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
