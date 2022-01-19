package com.mi.bookvillage.admin.domain.admin;

import com.mi.bookvillage.common.common.exceptions.customException.InvalidPasswordException;
import com.mi.bookvillage.common.common.response.APIResponse;
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


    @RequestMapping(value = "/admin/login" , method = RequestMethod.POST)
    public ResponseEntity<?> loginAdmin(@RequestBody AdminVO adminVO) {
        AdminVO admin = adminService.loginAdmin(adminVO);
        if( admin == null ){
            throw new InvalidPasswordException();
        }
        // 토큰 발급 기능
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("userId", admin.getUserId());
        String authToken = JWTokenUtil.createJwToken(tokenMap);
        // store 에 저장할 정보 전달
        Map<String, Object> adminInfoMap = new HashMap<>();
        adminInfoMap.put("token" , authToken);

        log.info("Login Success  ::: " + adminVO.getUserId() + " Login Access");
        return APIResponse.builder().success(adminInfoMap).build();
    }

    @RequestMapping(value= "/admin/detail" , method = RequestMethod.POST)
    public ResponseEntity<?> detailAdmin(HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");

        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String adminId = (String)adminObj.get("userId");

        // --- admin 정보 GET
        AdminVO admin = adminService.getAdminInfo(adminId);
        if( admin == null ){
            throw new InvalidPasswordException();
        }
        log.info("Access Admin Information  ::: " + adminId);
        return APIResponse.builder().success(admin).build();

    }
}
