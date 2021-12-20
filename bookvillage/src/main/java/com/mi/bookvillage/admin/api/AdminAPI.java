package com.mi.bookvillage.admin.api;

import com.mi.bookvillage.admin.model.service.AdminService;
import com.mi.bookvillage.admin.model.vo.AdminVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminAPI {

    private AdminService adminService;
    public AdminAPI(AdminService adminService){
        this.adminService = adminService;
    }

    public ResponseEntity<?> loginAdmin(AdminVO adminVO) {
        AdminVO authAdmin = adminService.loginAdmin(adminVO);
        return ResponseEntity.ok();
    }
}
