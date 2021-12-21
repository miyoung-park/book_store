package com.mi.bookvillage.admin.api;

import com.mi.bookvillage.admin.model.service.AdminService;
import com.mi.bookvillage.admin.model.vo.AdminVO;
import com.mi.bookvillage.common.security.JWTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminAPI {

    private AdminService adminService;
    public AdminAPI(AdminService adminService){
        this.adminService = adminService;
    }


    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public ResponseEntity<?> loginAdmin(@RequestBody AdminVO adminVO) {
        AdminVO admin = adminService.loginAdmin(adminVO);
        if( admin == null ){
            // Exception 발생
        }
        // 토큰 발급 기능
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("userId", adminVO.getUserId());
        String authToken = JWTokenUtil.createJwToken(tokenMap);

        Map<String, Object> adminInfoMap = new HashMap<>();
        adminInfoMap.put("token" , authToken);
        adminInfoMap.put("role" , admin.getUserRole());

        return ResponseEntity.ok().body(adminInfoMap);
    }

    @RequestMapping(value= "/detail" , method = RequestMethod.POST)
    public ResponseEntity<?> detailAdmin(HttpServletRequest request){
        // --- header 토큰 GET
        String token = request.getHeader("Authorization");

        // --- 토큰 유효성 검사
        if( token == null || ! JWTokenUtil.checkToken(token)){
            return ResponseEntity.badRequest().build();
        }
        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String adminId = (String)adminObj.get("userId");

         // --- admin 정보 GET
        AdminVO admin = adminService.getAdminInfo(adminId);
        return ResponseEntity.ok().body(admin);

    }
}
