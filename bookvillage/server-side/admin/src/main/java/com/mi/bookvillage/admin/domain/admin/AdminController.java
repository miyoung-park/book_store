package com.mi.bookvillage.admin.domain.admin;


import com.mi.bookvillage.common.common.response.ApiResponse;
import com.mi.bookvillage.common.common.response.ApiResponseBuilderFactory;
import com.mi.bookvillage.common.common.security.JWTokenUtil;
import com.mi.bookvillage.common.domain.Admin.AdminVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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


    @RequestMapping(value = "/admin/login" , method = RequestMethod.POST)
    public ApiResponse loginAdmin(@RequestBody AdminVO adminVO) {
        AdminVO admin = adminService.loginAdmin(adminVO);
        // 토큰 발급
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("userId", admin.getUserId());
        String authToken = JWTokenUtil.createJwToken(tokenMap);
        // store 에 저장할 정보 전달
        Map<String, Object> adminInfoMap = new HashMap<>();
        adminInfoMap.put("token" , authToken);
        adminInfoMap.put("userId" , admin.getUserId());
        adminInfoMap.put("userName" , admin.getUserName());

        log.info("LOGIN_USER_ID  >>>>>  " + adminVO.getUserId() );
        return apiResponseBuilderFactory.success().setData(adminInfoMap).build();
    }

    @RequestMapping(value= "/admin/detail" , method = RequestMethod.POST)
    public ApiResponse detailAdmin(HttpServletRequest request){
        // adminId GET
        String adminId = JWTokenUtil.getUserIdFromToken( request );
        AdminVO admin = adminService.getAdminInfo(adminId);
        return apiResponseBuilderFactory.success().setData(admin).build();

    }
}
