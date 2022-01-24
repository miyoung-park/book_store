package com.mi.bookvillage.admin.domain.user;

import com.mi.bookvillage.admin.domain.point.PointService;
import com.mi.bookvillage.common.common.exceptions.ApiException;
import com.mi.bookvillage.common.common.exceptions.ApiServiceErrorCode;
import com.mi.bookvillage.common.domain.User.UserMapper;
import com.mi.bookvillage.common.domain.User.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final PointService pointService;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;


    public List<UserVO> getUserList(){
        return userMapper.getUserList();
    }


    public UserVO loginUser(UserVO userVO) {
        UserVO authUser = userMapper.loginUser(userVO);
        if(authUser == null){
            throw new ApiException(ApiServiceErrorCode.INVALID_USER, "아이디 정보가 존재하지 않습니다.");
        }
        if( !encoder.matches( userVO.getUserPw(), authUser.getUserPw()) ){
            throw new ApiException(ApiServiceErrorCode.INVALID_PASSWORD, "비밀번호를 다시 확인해주세요.");
        }
        return authUser;
    }


    public void addUser(UserVO userVO){
        String encodedPassword = encoder.encode(userVO.getUserPw());
        userVO.setUserPw(encodedPassword);
        userMapper.addUser(userVO);
    }


    public UserVO getUserDetailById(String userId){
        UserVO customer = userMapper.getUserDetailById(userId);
        if(customer == null){
            throw new NullPointerException("null");
        }
        return customer;
    }


    public UserVO getUserDetailBySeq(int userSeq){
        UserVO user = userMapper.getUserDetailBySeq(userSeq);
        if(user == null){
            throw new ApiException(ApiServiceErrorCode.DATA_NOT_FOUND, "해당 회원이 존재하지 않습니다.");
        }
        int totalPoint = pointService.getPreviousTotalPoint(userSeq);
        user.setUserPoint(totalPoint);
        return user;
    }


    public void updateUser(UserVO userVO){
        if( userVO.getUserPw() != null ){
            String encodedPassword = encoder.encode(userVO.getUserPw());
            userVO.setUserPw(encodedPassword);
        }
        // 고객이 비밀번호 변경한 경우
        userMapper.updateUser(userVO);
    }
    public void deleteUser(int userSeq){
        userMapper.deleteUser(userSeq);
    }

}
