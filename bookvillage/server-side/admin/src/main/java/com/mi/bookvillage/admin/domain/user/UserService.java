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

    /**
     * 고객정보 추가
     */
    public void addUser(UserVO newUser){
        UserVO user = userMapper.getUserDetailById(newUser.getUserId() );
        if( user != null ){
            throw new ApiException(ApiServiceErrorCode.JOINED_USER, "이미 해당 아이디로 가입된 유저가 있습니다.");
        }
        String encodedPassword = encoder.encode(newUser.getUserPw());
        newUser.setUserPw(encodedPassword);
        userMapper.addUser(newUser);
    }


    /**
     * 고객정보 조회 By Seq
     */
    public UserVO getUserDetailBySeq(int userSeq){
        UserVO user = userMapper.getUserDetailBySeq(userSeq);
        if(user == null){
            throw new ApiException(ApiServiceErrorCode.DATA_NOT_FOUND, "해당 회원이 존재하지 않습니다.");
        }
        int totalPoint = pointService.getPreviousTotalPoint(userSeq);
        user.setUserPoint(totalPoint);
        return user;
    }


    /**
     * 고객 정보 업데이트
     */
    public void updateUser(UserVO userVO){
        // 고객이 비밀번호 변경한 경우
        if( userVO.getUserPw() != null ){
            String encodedPassword = encoder.encode(userVO.getUserPw());
            userVO.setUserPw(encodedPassword);
        }
        userMapper.updateUser(userVO);
    }
    public void deleteUser(int userSeq){
        userMapper.deleteUser(userSeq);
    }

}
