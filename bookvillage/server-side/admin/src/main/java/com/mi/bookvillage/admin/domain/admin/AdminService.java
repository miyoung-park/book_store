package com.mi.bookvillage.admin.domain.admin;


import com.mi.bookvillage.common.common.exceptions.ApiException;
import com.mi.bookvillage.common.common.exceptions.ApiServiceErrorCode;
import com.mi.bookvillage.common.domain.Admin.AdminMapper;
import com.mi.bookvillage.common.domain.Admin.AdminVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    
    public AdminVO loginAdmin(AdminVO admin){

        AdminVO authAdmin = adminMapper.loginAdmin(admin);

        if( admin == null ){
            throw new ApiException( ApiServiceErrorCode.INVALID_USER , "헤당 아이디가 존재하지 않습니다.");
        }
        if( !admin.getUserPw().equals( authAdmin.getUserPw() ) ){
            throw new ApiException( ApiServiceErrorCode.INVALID_PASSWORD, "비밀번호가 틀렸습니다.");
        }
        return admin;
    }

    public AdminVO getAdminInfo(String userId){
        AdminVO admin = adminMapper.getAdminInfo(userId);
        if(admin == null){
            throw new ApiException( ApiServiceErrorCode.INVALID_USER , "헤당 아이디가 존재하지 않습니다.");
        }
        return admin;
    }
}
