package com.mi.bookvillage.admin.domain.admin;



import com.mi.bookvillage.common.common.annotation.NoJwtAuthorization;
import com.mi.bookvillage.common.common.response.ApiResponse;
import com.mi.bookvillage.common.common.response.ApiResponseBuilderFactory;
import com.mi.bookvillage.common.common.security.JWTokenUtil;
import com.mi.bookvillage.common.domain.admin.AdminVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ApiResponseBuilderFactory apiResponseBuilderFactory;


    @NoJwtAuthorization  // Jwtoken 인증 필요X
    @RequestMapping(value = "/admin/login" , method = RequestMethod.POST)
    public ApiResponse loginAdmin(@RequestBody AdminVO admin) {

        AdminVO authAdmin = adminService.loginAdmin(admin);
        // 토큰 발급
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("userId", authAdmin.getUserId());
        String authToken = JWTokenUtil.createJwToken(tokenMap);
        // store 에 저장할 정보 전달
        Map<String, Object> adminInfoMap = new HashMap<>();
        adminInfoMap.put("token" , authToken);
        adminInfoMap.put("userId" , authAdmin.getUserId());
        adminInfoMap.put("userName" , authAdmin.getUserName());

        log.info("LOGIN_USER_ID  >>>>>  " + admin.getUserId() );
        return apiResponseBuilderFactory.success().setData(adminInfoMap).build();
    }


    @RequestMapping(value= "/admin/detail" , method = RequestMethod.POST)
    public ApiResponse detailAdmin( HttpServletRequest request){
        // adminId GET
        String adminId = JWTokenUtil.getUserIdFromToken( request );
        AdminVO admin = adminService.getAdminInfo(adminId);
        return apiResponseBuilderFactory.success().setData(admin).build();

    }
}
