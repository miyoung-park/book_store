package com.mi.bookvillage.model.dao;

import com.mi.bookvillage.model.vo.RentalVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RentalDAO {

    List<RentalVO> getRentalList(Integer userSeq);
    RentalVO getRentalDetail(int rentalSeq);
    RentalVO getRentalDetailByBookSeq(int bookSeq);
    void rentalBook(RentalVO rentalVO);
    void updateRentalStatus(RentalVO rentalVO);
}
