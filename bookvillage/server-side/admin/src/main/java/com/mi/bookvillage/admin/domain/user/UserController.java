package com.mi.bookvillage.admin.domain.user;

import com.mi.bookvillage.common.common.annotation.JwtAuthorization;
import com.mi.bookvillage.common.common.response.ApiResponse;
import com.mi.bookvillage.common.common.response.ApiResponseBuilderFactory;
import com.mi.bookvillage.common.domain.user.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * UserAPI
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ApiResponseBuilderFactory apiResponseBuilderFactory;

    /**
     * 고객 리스트
     * @return
     */
    @JwtAuthorization
    @RequestMapping(value = "/user/list" , method = RequestMethod.GET)
    public ApiResponse getUserList( ){
        List<UserVO> userList = userService.getUserList();
        return apiResponseBuilderFactory.success().setData(userList).build();
    }


    /**
     * 고객정보 추가
     */
    @JwtAuthorization
    @RequestMapping(value="/user/add" , method = RequestMethod.POST)
    public ApiResponse addUser(@RequestBody UserVO user,
                                HttpServletRequest request){
        userService.addUser(user);
        return apiResponseBuilderFactory.success().build();
    }


    /**
     * 고객정보 조회 By Seq
     */
    @JwtAuthorization
    @RequestMapping(value ="/user/detail/{userSeq}" , method = RequestMethod.GET)
    public ApiResponse getUserDetailBySeq(  @PathVariable("userSeq") int userSeq,
                                             HttpServletRequest request){

        // TODO: user point 도 service 안에 추가(완)
        UserVO user = userService.getUserDetailBySeq(userSeq);

        return apiResponseBuilderFactory.success().setData(user).build();
    }


    /**
     * 고객정보 업데이트
     */
    @JwtAuthorization
    @RequestMapping(value="/user/update/{userSeq}" , method = RequestMethod.PUT)
    public ApiResponse updateUser( @RequestBody UserVO user,
                                    HttpServletRequest request){
        userService.updateUser(user);
        return apiResponseBuilderFactory.success().build();

    }


    /**
     * 고객정보 삭제
     */
    @JwtAuthorization
    @RequestMapping(value="/user/delete/{userSeq}" , method = RequestMethod.DELETE)
    public ApiResponse deleteUser(@PathVariable("userSeq") int userSeq,
                                   HttpServletRequest request){
        userService.deleteUser(userSeq);
        return apiResponseBuilderFactory.success().build();
    }



}
