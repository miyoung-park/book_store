package com.mi.bookvillage.common.service;

import com.mi.bookvillage.common.common.exceptions.customException.InvalidPasswordException;
import com.mi.bookvillage.common.mapper.CustomerDAO;
import com.mi.bookvillage.common.vo.CustomerVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;
    private final BCryptPasswordEncoder encoder;


    public List<CustomerVO> getCustomerList(){
        return customerDAO.getCustomerList();
    }


    public CustomerVO loginCustomer(CustomerVO customerVO) {
        CustomerVO authCustomer = customerDAO.loginCustomer(customerVO);
        if(authCustomer == null || !encoder.matches( customerVO.getUserPw(), authCustomer.getUserPw()) ){
            // TODO: Exception 추가 null에 대해서도 ! 뭐 VAuthException이라던지... 혹은 Invalid와 user관련해서 ... 회사 Convention 참고 // Invalid -- 좀 더 자세하게 이유를 설명해주기
            throw new InvalidPasswordException();
        }
        return authCustomer;
    }


    public void addCustomer(CustomerVO customerVO){
        String encodedPassword = encoder.encode(customerVO.getUserPw());
        customerVO.setUserPw(encodedPassword);
        customerDAO.addCustomer(customerVO);
    }


    public CustomerVO getCustomerDetailById(String userId){
        CustomerVO customer = customerDAO.getCustomerDetailById(userId);
        if(customer == null){
            throw new NullPointerException("null");
        }
        return customer;
    }


    public CustomerVO getCustomerDetailBySeq(int userSeq){
        CustomerVO customer = customerDAO.getCustomerDetailBySeq(userSeq);
        if(customer == null){
            throw new NullPointerException();
        }
        return customer;
    }


    public void updateCustomer(CustomerVO customerVO){
        if( customerVO.getUserPw() != null ){
            String encodedPassword = encoder.encode(customerVO.getUserPw());
            customerVO.setUserPw(encodedPassword);
        }
        // 고객이 비밀번호 변경한 경우
        customerDAO.updateCustomer(customerVO);
    }
    public void deleteCustomer(int userSeq){
        customerDAO.deleteCustomer(userSeq);
    }

}
