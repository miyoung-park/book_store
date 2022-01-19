package com.mi.bookvillage.user.domain.user;

import com.mi.bookvillage.common.common.exceptions.customException.InvalidPasswordException;
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

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;


    public List<UserVO> getCustomerList(){
        return userMapper.getCustomerList();
    }


    public UserVO loginCustomer(UserVO userVO) {
        UserVO authCustomer = userMapper.loginCustomer(userVO);
        if(authCustomer == null || !encoder.matches( userVO.getUserPw(), authCustomer.getUserPw()) ){
            // TODO: Exception 추가 null에 대해서도 ! 뭐 VAuthException이라던지... 혹은 Invalid와 user관련해서 ... 회사 Convention 참고 // Invalid -- 좀 더 자세하게 이유를 설명해주기
            throw new InvalidPasswordException();
        }
        return authCustomer;
    }


    public void addCustomer(UserVO userVO){
        String encodedPassword = encoder.encode(userVO.getUserPw());
        userVO.setUserPw(encodedPassword);
        userMapper.addCustomer(userVO);
    }


    public UserVO getCustomerDetailById(String userId){
        UserVO customer = userMapper.getCustomerDetailById(userId);
        if(customer == null){
            throw new NullPointerException("null");
        }
        return customer;
    }


    public UserVO getCustomerDetailBySeq(int userSeq){
        UserVO customer = userMapper.getCustomerDetailBySeq(userSeq);
        if(customer == null){
            throw new NullPointerException();
        }
        return customer;
    }


    public void updateCustomer(UserVO userVO){
        if( userVO.getUserPw() != null ){
            String encodedPassword = encoder.encode(userVO.getUserPw());
            userVO.setUserPw(encodedPassword);
        }
        // 고객이 비밀번호 변경한 경우
        userMapper.updateCustomer(userVO);
    }
    public void deleteCustomer(int userSeq){
        userMapper.deleteCustomer(userSeq);
    }

}
