package com.mi.bookvillage.user.domain.user;

import com.mi.bookvillage.common.common.exceptions.ApiException;
import com.mi.bookvillage.common.common.exceptions.ApiServiceErrorCode;
import com.mi.bookvillage.common.domain.user.UserMapper;
import com.mi.bookvillage.common.domain.user.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;


    public UserVO loginUser(UserVO user) {

        UserVO authUser = userMapper.loginUser(user);

        if( authUser == null ){
            throw new ApiException(ApiServiceErrorCode.INVALID_USER, "헤당 아이디가 존재하지 않습니다.");
        }
        if( !encoder.matches( user.getUserPw(), authUser.getUserPw()) ){
            throw new ApiException(ApiServiceErrorCode.INVALID_PASSWORD, "비밀번호가 틀렸습니다.");
        }

        return authUser;
    }

    public UserVO getUserDetailById(String userId){

        UserVO user = userMapper.getUserDetailById(userId);
        if( user == null ){
            throw new ApiException(ApiServiceErrorCode.INVALID_USER, "헤당 아이디가 존재하지 않습니다.");
        }

        return user;
    }


}
