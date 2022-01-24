package com.mi.bookvillage.admin.domain.user;

import com.mi.bookvillage.common.common.response.ApiResponse;
import com.mi.bookvillage.common.common.response.ApiResponseBuilderFactory;
import com.mi.bookvillage.common.domain.User.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(value = "/user/list" , method = RequestMethod.GET)
    public ApiResponse getUserList(){
        List<UserVO> userList = userService.getUserList();
        return apiResponseBuilderFactory.success().setData(userList).build();
    }


    /**
     * 고객정보 추가
     */
    @RequestMapping(value="/user/add" , method = RequestMethod.POST)
    public ApiResponse addUser(@RequestBody UserVO user){
        userService.addUser(user);
        return apiResponseBuilderFactory.success().build();
    }


    /**
     * 고객정보 조회
     */
    @RequestMapping(value ="/user/detail/{userSeq}" , method = RequestMethod.GET)
    public ApiResponse getUserDetailBySeq(  @PathVariable("userSeq") int userSeq ){

        // TODO: customer point 도 service 안에 추가(완)
        UserVO user = userService.getUserDetailBySeq(userSeq);

        return apiResponseBuilderFactory.success().setData(user).build();
    }


    /**
     * 고객정보 업데이트
     */
    @RequestMapping(value="/customer/update/{userSeq}" , method = RequestMethod.PUT)
    public ApiResponse updateUser( @RequestBody UserVO user ){
        userService.updateUser(user);
        return apiResponseBuilderFactory.success().build();

    }


    /**
     * 고객정보 삭제
     */
    @RequestMapping(value="/user/delete/{userSeq}" , method = RequestMethod.DELETE)
    public ApiResponse deleteUser(@PathVariable("userSeq") int userSeq){
        userService.deleteUser(userSeq);
        return apiResponseBuilderFactory.success().build();
    }



}
